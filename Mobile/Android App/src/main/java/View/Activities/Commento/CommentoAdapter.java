package com.example.cm.View.Activities.Commento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Models.Commento;
import com.example.cm.R;

import java.util.List;

public class CommentoAdapter extends RecyclerView.Adapter<CommentoAdapter.MyViewHolder>  {
    private Context mContext;
    private List<Commento> listaCommenti;


    public CommentoAdapter(Context mContext, List<Commento> listaCommenti) {

        this.mContext = mContext;
        this.listaCommenti = listaCommenti;
    }

    @Override
    public CommentoAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_commento, viewGroup, false);

        return new CommentoAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentoAdapter.MyViewHolder viewHolder, int i) {

        viewHolder.Nickname.setText(listaCommenti.get(i).getNickname());
        viewHolder.Data.setText(listaCommenti.get(i).getData());
        viewHolder.TestoCommento.setText(listaCommenti.get(i).getTesto());


    }


    @Override
    public int getItemCount() {
        return listaCommenti.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Nickname;
        public TextView Data;
        public TextView TestoCommento;

        public MyViewHolder(View view) {
            super(view);
            Nickname = view.findViewById(R.id.NicknameCommento);
            Data = view.findViewById(R.id.DataCommento);
            TestoCommento = view.findViewById(R.id.Commento);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
