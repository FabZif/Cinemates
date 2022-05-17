package com.example.cm.View.Activities.Notifica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.AccettaRichiestaCollegamento;
import com.example.cm.Controller.RifiutaRichiestaCollegamento;
import com.example.cm.Models.Notifica;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;


public class NotificaAdapter extends RecyclerView.Adapter<NotificaAdapter.ViewHolder> {


    private List<Notifica> listaNotifiche;
    private Context context;

    public NotificaAdapter(List<Notifica> notifiche, Context context) {
        this.context = context;
        Collections.reverse(notifiche);
        listaNotifiche = notifiche;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nameTextView;
        public ImageButton messageButton, messageButton2;
        public ShapeableImageView Foto;


        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.textView5);
            messageButton = itemView.findViewById(R.id.imageButton8);
            messageButton2 = itemView.findViewById(R.id.imageButton7);
            Foto = itemView.findViewById(R.id.Immagine1);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public NotificaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_notifica, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NotificaAdapter.ViewHolder holder, final int position) {
        // Get the data model based on position

        Animation anim = AnimationUtils.loadAnimation(context,R.anim.slide_in_row);
        holder.itemView.startAnimation(anim);
        final Notifica notifica = listaNotifiche.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        final ImageButton delete = holder.messageButton;
        final ImageButton confirm = holder.messageButton2;
        final ShapeableImageView Foto = holder.Foto;
        switch (notifica.getTipo()) {
            case 0:  //PARERE RAPIDO
                textView.setText(notifica.getNickname() + " ha espresso un parere rapido ad una tua lista");
                Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+notifica.getImmagine()).resize(150,150).into(Foto);

                confirm.setVisibility(View.INVISIBLE);
                break;
            case 1: //COMMENTO
                textView.setText(notifica.getNickname() + " ha commentato una tua lista");
                Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+notifica.getImmagine()).resize(150,150).into(Foto);
                confirm.setVisibility(View.INVISIBLE);
                break;
            case 2: //VALUTAZIONE
                textView.setText(notifica.getNickname() + " ha valutato una tua lista");
                Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+notifica.getImmagine()).resize(150,150).into(Foto);
                confirm.setVisibility(View.INVISIBLE);
                break;
            case 3:
                textView.setText(notifica.getNickname() + " vuole aggiungerti ai collegamenti");
                Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+notifica.getImmagine()).resize(150,150).into(Foto);
                break;
            case 4:
                textView.setText(notifica.getTestoNotifica());
                confirm.setVisibility(View.INVISIBLE);
                Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+notifica.getImmagine()).resize(150,150).into(Foto);
                break;



        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccettaRichiestaCollegamento accettaRichiestaCollegamento = new AccettaRichiestaCollegamento(context);
                accettaRichiestaCollegamento.onButtonAccettaClicked(Utente.getIstanzaUtente().getIdUtente(), notifica.getIdUtente());
                RifiutaRichiestaCollegamento rifiutaRichiestaCollegamento = new RifiutaRichiestaCollegamento(context);
                rifiutaRichiestaCollegamento.onButtonRimuoviClicked(notifica.getIdNotifica());
                listaNotifiche.remove(position);
                notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RifiutaRichiestaCollegamento rifiutaRichiestaCollegamento = new RifiutaRichiestaCollegamento(context);
                rifiutaRichiestaCollegamento.onButtonRimuoviClicked(notifica.getIdNotifica());
                listaNotifiche.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listaNotifiche.size();
    }


}