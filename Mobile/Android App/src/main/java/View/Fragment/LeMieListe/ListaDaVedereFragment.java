package com.example.cm.View.Fragment.LeMieListe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.VisualizzaListaFilmDaVederePersonale;
import com.example.cm.Models.Utente;
import com.example.cm.R;

public class ListaDaVedereFragment extends Fragment {
    RecyclerView rvFilm3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_listadavedere, viewGroup, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFilm3 = view.findViewById(R.id.rvFilm3);

        VisualizzaListaFilmDaVederePersonale visualizzaListaFilmDaVederePersonale = new VisualizzaListaFilmDaVederePersonale(Utente.getIstanzaUtente().getIdUtente(),this);
        visualizzaListaFilmDaVederePersonale.onTabListaDaVedereClicked(this,rvFilm3);
    }
}
