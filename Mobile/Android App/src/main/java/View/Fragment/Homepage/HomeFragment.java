package com.example.cm.View.Fragment.Homepage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.cm.DAO.CommentoDAO;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.ParereRapidoDAO;
import com.example.cm.DAO.UtenteDAO;
import com.example.cm.DAO.ValutazioneDAO;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.example.cm.View.Activities.Commento.CommentoView;
import com.example.cm.data.CognitoSetting;
import com.example.cm.data.FileUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static android.provider.MediaStore.Images.Media.getContentUri;


public class HomeFragment extends Fragment {
    ImageView imageView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);



    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);







        LinearLayout linearLayout = view.findViewById(R.id.CommentiPreferitiLinear);
        LinearLayout linearLayout2 = view.findViewById(R.id.CommentiDaVedereLayout);

        imageView = view.findViewById(R.id.ImmagineProfiloHome);

        final Animation scaleUp,scaleDown;

        scaleUp = AnimationUtils.loadAnimation(getContext(),R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);



        if(!Utente.getIstanzaUtente().getImmagine().equals("null")){
        Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+Utente.getIstanzaUtente().getImmagine()).resize(150,150).into(imageView);
        }






        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CommentoView.class);
                intent.putExtra("idProfiloUtenteSelezionato", Utente.getIstanzaUtente().getIdUtente());
                intent.putExtra("tipoLista", 0);
                intent.putExtra("idMittente", Utente.getIstanzaUtente().getIdUtente());
                startActivity(intent);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CommentoView.class);
                intent.putExtra("idProfiloUtenteSelezionato", Utente.getIstanzaUtente().getIdUtente());
                intent.putExtra("tipoLista", 1);
                intent.putExtra("idMittente", Utente.getIstanzaUtente().getIdUtente());
                startActivity(intent);
            }
        });


        TextView miPiacePreferiti = view.findViewById(R.id.nMiPiacePreferiti);
        TextView commentiPreferiti = view.findViewById(R.id.nCommentiPreferiti);
        TextView mediaPreferiti = view.findViewById(R.id.mediaValutazionePreferiti);
        TextView miPiaceDaVedere = view.findViewById(R.id.nMiPiaceDaVedere);
        TextView commentiDaVedere = view.findViewById(R.id.nCommentiDaVedere);
        TextView mediaDaVedere = view.findViewById(R.id.mediaValutazioneDaVedere);
        TextView Nickname = view.findViewById(R.id.NicknameHome);

        Nickname.setText(Utente.getIstanzaUtente().getNickname());

        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(getContext());
        CommentoDAO commentoDAO = DAOFactory.getCommentoDAO(getContext());
        ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(getContext());


        parereRapidoDAO.getNumeroMiPiace(0, Utente.getIstanzaUtente().getIdUtente(), miPiacePreferiti);
        commentoDAO.getNumeroCommenti(0, Utente.getIstanzaUtente().getIdUtente(), commentiPreferiti);
        valutazioneDAO.getMediaValutazioni(0, Utente.getIstanzaUtente().getIdUtente(), mediaPreferiti);


        parereRapidoDAO.getNumeroMiPiace(1, Utente.getIstanzaUtente().getIdUtente(), miPiaceDaVedere);
        commentoDAO.getNumeroCommenti(1, Utente.getIstanzaUtente().getIdUtente(), commentiDaVedere);
        valutazioneDAO.getMediaValutazioni(1, Utente.getIstanzaUtente().getIdUtente(), mediaDaVedere);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(scaleDown);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);intent.setType("image/*");startActivityForResult(intent, 10);
            }
        });



    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, final Intent data) {



        if (resultCode == RESULT_OK) {
            new Thread(new Runnable() {
                @Override
                public void run() {
            /*
            // run operation here
            */
                    Uri uri = data.getData();


                    File file = null;
                    try {
                        file = FileUtil.from(getContext(),uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    AmazonS3 s3;
                        TransferUtility transferUtility;



                        // Create an S3 client
                        s3 = new AmazonS3Client(new BasicAWSCredentials( "********************", "*********************************" ));

                        // Set the region of your S3 bucket



                         final String nome = data.getData().getLastPathSegment()+".jpeg";

                        PutObjectRequest putObjReq = new PutObjectRequest("mypiccinemates", "/"+nome, file );
                        s3.putObject(putObjReq);
                         Utente.getIstanzaUtente().setImmagine(nome);
                        UtenteDAO utenteDAO = DAOFactory.getUtenteDAO(getContext());
                        utenteDAO.updateUtente(nome,Utente.getIstanzaUtente().getIdUtente());


                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.detach(HomeFragment.this);
                    fragmentTransaction.attach(HomeFragment.this);
                    fragmentTransaction.commit();







                    //after getting result
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }

                private void runOnUiThread(Runnable runnable) {

                }
            }).start();








        }else {
            Toast.makeText(getContext(), "Seleziona un immagine!",Toast.LENGTH_LONG).show();
        }
    }







}
