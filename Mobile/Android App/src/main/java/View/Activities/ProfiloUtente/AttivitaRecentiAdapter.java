package com.example.cm.View.Activities.ProfiloUtente;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Models.AttivitaRecenti;
import com.example.cm.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AttivitaRecentiAdapter extends RecyclerView.Adapter<AttivitaRecentiAdapter.MyViewHolder> {


    private Context mContext;
    private List<AttivitaRecenti> listaAttivitaRecenti;


    public AttivitaRecentiAdapter(Context mContext, List<AttivitaRecenti> listaAttivitaRecenti){

        this.mContext = mContext;
            this.listaAttivitaRecenti = listaAttivitaRecenti;


    }

    @Override
    public AttivitaRecentiAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_attivitarecente, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AttivitaRecentiAdapter.MyViewHolder viewHolder, int i){
        Animation anim = AnimationUtils.loadAnimation(mContext,R.anim.slide_in_row);
        viewHolder.itemView.startAnimation(anim);
        Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+ listaAttivitaRecenti.get(i).getImmagine()).resize(150,150).into(viewHolder.Immagine);
        if(listaAttivitaRecenti.get(i).getTipoLista()==0) {
            switch (listaAttivitaRecenti.get(i).getTipo()) {
                case 0:  //PARERE RAPIDO
                    viewHolder.Descrizione.setText("Ha espresso un parere rapido alla lista di " + listaAttivitaRecenti.get(i).getDestinatario());
                    viewHolder.Data.setText( listaAttivitaRecenti.get(i).getData().toString());
                    break;
                case 1: //COMMENTO
                    viewHolder.Descrizione.setText("Ha lasciato un commento alla lista di " + listaAttivitaRecenti.get(i).getDestinatario());
                    viewHolder.Data.setText( listaAttivitaRecenti.get(i).getData().toString());

                    break;
                case 2: //VALUTAZIONE
                    viewHolder.Descrizione.setText("Ha valutato una lista di " + listaAttivitaRecenti.get(i).getDestinatario());
                    viewHolder.Data.setText( listaAttivitaRecenti.get(i).getData().toString());

                    break;
                case 3:
                    break;
            }
        }
         if(listaAttivitaRecenti.get(i).getTipoLista()==1){
           
             viewHolder.Descrizione.setText("Ha aggiunto "+listaAttivitaRecenti.get(i).getTitolo()+" alla sua lista da vedere");
             viewHolder.Data.setText( listaAttivitaRecenti.get(i).getData().toString());

         }

         if(listaAttivitaRecenti.get(i).getTipoLista()==2){
             viewHolder.Descrizione.setText("Ha aggiunto "+listaAttivitaRecenti.get(i).getTitolo()+" alla sua lista preferiti");
             viewHolder.Data.setText( listaAttivitaRecenti.get(i).getData().toString());


         }

        }



    @Override
    public int getItemCount(){
        return listaAttivitaRecenti.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView Descrizione;
        public ShapeableImageView Immagine;
        public TextView Data;

        public MyViewHolder(View view){
            super(view);
            Descrizione = view.findViewById(R.id.DescrizioneAttivita);
            Immagine = view.findViewById(R.id.FotoProfilo);
            Data = view.findViewById(R.id.Data);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                   /* if (pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = listaFilm.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("movies", clickedDataItem );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginalTitolo(), Toast.LENGTH_SHORT).show();
                    }*/
                }
            });
        }
    }

}

