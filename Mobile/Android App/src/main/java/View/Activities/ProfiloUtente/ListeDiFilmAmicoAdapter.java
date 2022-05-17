package com.example.cm.View.Activities.ProfiloUtente;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Models.Film;
import com.example.cm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListeDiFilmAmicoAdapter extends RecyclerView.Adapter<ListeDiFilmAmicoAdapter.MyViewHolder> {


        private Context mContext;
        private List<Film> listaFilm;


        public ListeDiFilmAmicoAdapter(Context mContext, List<Film> listaFilm){
            this.mContext = mContext;
            this.listaFilm = listaFilm;

        }

        @Override
        public ListeDiFilmAmicoAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_listefilmamico, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListeDiFilmAmicoAdapter.MyViewHolder viewHolder, final int i){
            viewHolder.Titolo.setText(listaFilm.get(i).getTitolo());
            viewHolder.Descrizione.setText(listaFilm.get(i).getTrama());
            viewHolder.Riquadro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                    viewHolder.Riquadro.startAnimation(animation);

                            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                            alertDialog.setTitle("Trama");
                            alertDialog.setMessage(listaFilm.get(i).getTrama());
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    });




            Picasso.get().load("https://image.tmdb.org/t/p/original" + listaFilm.get(i).getImmagine()).resize(120  , 150).into(viewHolder.Immagine);


        }

        @Override
        public int getItemCount(){
            return listaFilm.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView Titolo, Descrizione;
            public ImageView Immagine;
            public CardView Riquadro;


            public MyViewHolder(View view){
                super(view);
                Titolo = view.findViewById(R.id.TitoloFilmCard);
                Descrizione = view.findViewById(R.id.DescrizioneFilmCard);
                Immagine = view.findViewById(R.id.ImmagineFilmCard);
                Riquadro = view.findViewById(R.id.card_view);


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

