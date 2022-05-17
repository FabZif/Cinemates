package com.example.cm.View.Activities.ProfiloUtente;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cm.Controller.AggiungiValutazione;
import com.example.cm.Controller.RecuperaInterazioni;
import com.example.cm.Controller.VisualizzaListeDiFilmDiUnAmico;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.example.cm.View.Activities.Commento.CommentoView;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

public class ListeDiFilmFragment extends Fragment {

    Integer idProfiloUtenteSelezionato;
    MultiSnapRecyclerView msrvPreferiti;
    MultiSnapRecyclerView mrsvDaVedere;
    Boolean like;
    public ListeDiFilmFragment(Integer idProfiloUtenteSelezionato) {
        this.idProfiloUtenteSelezionato = idProfiloUtenteSelezionato;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_listedifilmamico, viewGroup, false);

    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            final ImageButton miPiacePreferiti,miPiaceDaVedere,nonMiPiacePreferiti,nonMiPiaceDaVedere,commentaPreferiti,commentaDaVedere;
            RatingBar valutaPreferiti,valutaDaVedere;



            miPiacePreferiti = view.findViewById(R.id.MiPiacePreferiti);
            miPiaceDaVedere = view.findViewById(R.id.MiPiaceDaVedere);
            nonMiPiaceDaVedere = view.findViewById(R.id.NonMiPiaceDaVedere);
            nonMiPiacePreferiti = view.findViewById(R.id.NonMiPiacePreferiti);
            commentaDaVedere = view.findViewById(R.id.CommentoDaVedere);
            commentaPreferiti = view.findViewById(R.id.CommentaPreferiti);
            valutaPreferiti = view.findViewById(R.id.ValutaPreferiti);
            valutaDaVedere = view.findViewById(R.id.ValutaDaVedere);
            msrvPreferiti = view.findViewById(R.id.first_recycler_view);
            mrsvDaVedere = view.findViewById(R.id.second_recycler_view);



        VisualizzaListeDiFilmDiUnAmico visualizzaListeDiFilmDiUnAmico = new VisualizzaListeDiFilmDiUnAmico(idProfiloUtenteSelezionato,this);
        visualizzaListeDiFilmDiUnAmico.onTabListeDiFilmClicked(this,msrvPreferiti,mrsvDaVedere);

        final RecuperaInterazioni recuperaInterazioni = new RecuperaInterazioni(getContext());
        recuperaInterazioni.getParereRapidoSelezionatoPreferiti(null,idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente(),miPiacePreferiti,nonMiPiacePreferiti);
        recuperaInterazioni.getParereRapidoSelezionatoDaVedere(null,idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),miPiaceDaVedere,nonMiPiaceDaVedere);
        recuperaInterazioni.setRating(idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),valutaPreferiti,valutaDaVedere);



        miPiacePreferiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
                miPiacePreferiti.startAnimation(animation);

                recuperaInterazioni.onButtonMiPiaceClicked(0,idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),miPiacePreferiti,nonMiPiacePreferiti,0);
            }
        });



        nonMiPiacePreferiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
                nonMiPiacePreferiti.startAnimation(animation);
                recuperaInterazioni.onButtonNonMiPiaceClicked(1,idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),miPiacePreferiti,nonMiPiacePreferiti,0);
            }
        });


        miPiaceDaVedere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
                miPiaceDaVedere.startAnimation(animation);
                recuperaInterazioni.onButtonMiPiaceClicked(0,idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),miPiaceDaVedere,nonMiPiaceDaVedere,1);
            }
        });

        nonMiPiaceDaVedere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
                nonMiPiaceDaVedere.startAnimation(animation);
                recuperaInterazioni.onButtonNonMiPiaceClicked(1,idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),miPiaceDaVedere,nonMiPiaceDaVedere,1);
            }
        });

        commentaPreferiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
                commentaPreferiti.startAnimation(animation);
                Intent intent = new Intent(view.getContext(), CommentoView.class);
                intent.putExtra("idProfiloUtenteSelezionato",idProfiloUtenteSelezionato);
                intent.putExtra("tipoLista",0);
                intent.putExtra("idMittente",Utente.getIstanzaUtente().getIdUtente());
                startActivity(intent);
            }
        });

        commentaDaVedere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
                commentaDaVedere.startAnimation(animation);
                Intent intent = new Intent(view.getContext(), CommentoView.class);
                intent.putExtra("idProfiloUtenteSelezionato",idProfiloUtenteSelezionato);
                intent.putExtra("tipoLista",1);

                intent.putExtra("idMittente",Utente.getIstanzaUtente().getIdUtente());
                startActivity(intent);
            }
        });

        valutaPreferiti.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                AggiungiValutazione aggiungiValutazione = new AggiungiValutazione(getContext());
                aggiungiValutazione.onButtonValutaPreferitiClicked(idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),rating);
            }
        });

        valutaDaVedere.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                AggiungiValutazione aggiungiValutazione = new AggiungiValutazione(getContext());
                aggiungiValutazione.onButtonValutaDaVedereClicked(idProfiloUtenteSelezionato,Utente.getIstanzaUtente().getIdUtente(),rating);
            }
        });

    }
}
