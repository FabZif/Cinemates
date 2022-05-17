package com.example.cm.View.Fragment.Collegamenti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.ControllaEsistenzaCollegamento;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UtenteAdapter extends RecyclerView.Adapter<UtenteAdapter.ViewHolder> {


    private List<Utente> listaUtenti;
    Context context;

    public UtenteAdapter(List<Utente> utenti, Context context) {
        listaUtenti = utenti;
        this.context=context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ShapeableImageView imageView;
        public TextView nameTextView;
        public ImageView item;



        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imageView = itemView.findViewById(R.id.ImmagineUtente);
            nameTextView = itemView.findViewById(R.id.NomeUtente);
            item = itemView.findViewById(R.id.imageView3);



        }
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public UtenteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_utente, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(UtenteAdapter.ViewHolder holder, int position) {
        // Get the data model based on position

        Animation anim = AnimationUtils.loadAnimation(context,R.anim.slide_in_row);
        holder.itemView.startAnimation(anim);
        final Utente utente = listaUtenti.get(position);

        // Set item views based on your views and data model
        ShapeableImageView FotoUtente = holder.imageView;
        final TextView NomeUtente = holder.nameTextView;
        final ImageView item = holder.item;
        if(!utente.getImmagine().equals("null")){
        Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+utente.getImmagine()).resize(80,80).into(FotoUtente);}else{
        }
        NomeUtente.setText(utente.getNickname());
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_down);
                item.startAnimation(animation);
                          ControllaEsistenzaCollegamento controllaEsistenzaCollegamento = new ControllaEsistenzaCollegamento(context);
                          controllaEsistenzaCollegamento.onButtonProfiloClicked(utente.getIdUtente(),utente.getNickname(),utente.getImmagine(),Utente.getIstanzaUtente().getIdUtente());


                    }
                });
            }








    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listaUtenti.size();
    }
}
