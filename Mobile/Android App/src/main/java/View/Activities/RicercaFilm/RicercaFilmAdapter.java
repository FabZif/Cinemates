package com.example.cm.View.Activities.RicercaFilm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.AggiungiAListaPreferitiPersonale;
import com.example.cm.Controller.AggiungiAListaDaVederePersonale;
import com.example.cm.Models.Film;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RicercaFilmAdapter extends RecyclerView.Adapter<RicercaFilmAdapter.ViewHolder> {

    Context context;
    private List<Film> listaFilm;


    public RicercaFilmAdapter(List<Film> film,Context context) {
        listaFilm = film;
        this.context=context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView imageView;
        public TextView nameTextView;
        public TextView Desc;
        public ImageButton messageButton,messageButton2;
        public ImageView Riquadro;


        public ViewHolder(View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.Poster);
            nameTextView = itemView.findViewById(R.id.Titolo);
            Desc = itemView.findViewById(R.id.Desc);
            messageButton = itemView.findViewById(R.id.AggiungiPreferiti);
            messageButton2 = itemView.findViewById(R.id.AggiungiDaVedere);
            Riquadro = itemView.findViewById(R.id.imageView3);

        }
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RicercaFilmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_film_ricerca, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RicercaFilmAdapter.ViewHolder holder, int position) {
            Animation anim = AnimationUtils.loadAnimation(context,R.anim.slide_in_row);
            holder.itemView.startAnimation(anim);

            final Film film = listaFilm.get(position);

            RoundedImageView poster = holder.imageView;
            TextView textView = holder.nameTextView;
            TextView desc = holder.Desc;
            final ImageButton preferiti = holder.messageButton;
            final ImageButton davedere = holder.messageButton2;
            final ImageView Riquadro = holder.Riquadro;
            Picasso.get().load("https://image.tmdb.org/t/p/original" + film.getImmagine()).resize(107, 141).into(poster);
            textView.setText(film.getTitolo());
            desc.setText(film.getTrama());
             preferiti.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_down);
                     preferiti.startAnimation(animation);
                     preferiti.setImageResource(R.drawable.preferiti_clicked);
                     AggiungiAListaPreferitiPersonale aggiungiAListaPreferitiPersonale = new AggiungiAListaPreferitiPersonale(film,context);
                     aggiungiAListaPreferitiPersonale.onButtonPreferitiClicked(Utente.getIstanzaUtente().getIdUtente());

                 }
             });

             davedere.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_down);
                     davedere.startAnimation(animation);
                     davedere.setImageResource(R.drawable.davedere_clicked);
                     AggiungiAListaDaVederePersonale aggiungiAListaDaVederePersonale = new AggiungiAListaDaVederePersonale(film,context);
                     aggiungiAListaDaVederePersonale.onButtonDaVedereClicked(Utente.getIstanzaUtente().getIdUtente());
                 }
             });

        Riquadro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_down);
                Riquadro.startAnimation(animation);
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Trama");
                alertDialog.setMessage(film.getTrama());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return listaFilm.size();
    }
}
