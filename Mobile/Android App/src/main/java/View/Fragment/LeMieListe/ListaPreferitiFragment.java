package com.example.cm.View.Fragment.LeMieListe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.VisualizzaListaFilmPreferitiPersonale;
import com.example.cm.Models.Utente;
import com.example.cm.R;

public class ListaPreferitiFragment extends Fragment {
     RecyclerView rvFilm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_listapreferiti, viewGroup, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFilm = view.findViewById(R.id.rvFilm2);

        VisualizzaListaFilmPreferitiPersonale visualizzaListaFilmPreferitiPersonale = new VisualizzaListaFilmPreferitiPersonale(Utente.getIstanzaUtente().getIdUtente(),this);
        visualizzaListaFilmPreferitiPersonale.onButtonFilmPreferitiClicked(this,rvFilm);
    }
}
