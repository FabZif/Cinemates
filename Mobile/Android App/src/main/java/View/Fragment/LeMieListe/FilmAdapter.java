package com.example.cm.View.Fragment.LeMieListe;

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

import com.example.cm.Controller.RimuoviFilmDaListaDaVederePersonale;
import com.example.cm.Controller.RimuoviFilmDaListaPreferitiPersonale;
import com.example.cm.Models.Film;
import com.example.cm.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    Context context;
    Integer idUtente;
    private List<Film> listaFilm;

    public FilmAdapter(List<Film> film, Context context, Integer idUtente) {
        listaFilm = film;
        this.context=context;
        this.idUtente=idUtente;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView imageView;
        public TextView nameTextView;
        public TextView shortDesc;
        public ImageView Riquadro;
        public ImageButton messageButton,messageButton2;


        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imageView = itemView.findViewById(R.id.FotoFilm);
            nameTextView = itemView.findViewById(R.id.TitoloFilm);
            shortDesc = itemView.findViewById(R.id.ShortDesc);
            messageButton = itemView.findViewById(R.id.Rimuovi);
            Riquadro = itemView.findViewById(R.id.imageView3);



        }
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_film, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(FilmAdapter.ViewHolder holder, final int position) {

        Animation anim = AnimationUtils.loadAnimation(context,R.anim.slide_in_row);
        holder.itemView.startAnimation(anim);
        // Get the data model based on position
        final Film film = listaFilm.get(position);

        // Set item views based on your views and data model
        RoundedImageView FotoFilm = holder.imageView;
        TextView Titolo = holder.nameTextView;
        ImageButton Rimuovi = holder.messageButton;
        final TextView Descrizione = holder.shortDesc;
        final ImageView Riquadro = holder.Riquadro;
        Picasso.get().load("https://image.tmdb.org/t/p/original"+film.getImmagine()).resize(107,146).into(FotoFilm);
        Titolo.setText(film.getTitolo());
        Descrizione.setText(film.getTrama());

        Rimuovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaFilm.get(position).getListaAppartenenza()==0) {   //PREFERITI
                    RimuoviFilmDaListaPreferitiPersonale rimuoviFilmDaListaPreferitiPersonale = new RimuoviFilmDaListaPreferitiPersonale(context);
                    rimuoviFilmDaListaPreferitiPersonale.onButtonRimuoviPreferitoClicked(listaFilm.get(position).getIdFilm(), idUtente, context);
                    listaFilm.remove(position);
                    notifyDataSetChanged();
                }
                else{
                    RimuoviFilmDaListaDaVederePersonale rimuoviFilmDaListaDaVederePersonale = new RimuoviFilmDaListaDaVederePersonale(context);  //DAVEDERE
                    rimuoviFilmDaListaDaVederePersonale.onButtonRimuoviDaVedereClicked(listaFilm.get(position).getIdFilm(),idUtente,context);
                    listaFilm.remove(position);
                    notifyDataSetChanged();

                }
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

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listaFilm.size();
    }
}
