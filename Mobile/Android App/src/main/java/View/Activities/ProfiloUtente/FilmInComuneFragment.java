package com.example.cm.View.Activities.ProfiloUtente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.cm.Controller.VisualizzaFilmInComuneConUnAmico;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

public class FilmInComuneFragment extends Fragment {
    Integer idAmico;
    MultiSnapRecyclerView msrvPreferiti;
    MultiSnapRecyclerView mrsvDaVedere;
    public FilmInComuneFragment(Integer idAmico) {
        this.idAmico=idAmico;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_filmincomune, viewGroup, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msrvPreferiti = view.findViewById(R.id.first_recycler_view);
        mrsvDaVedere = view.findViewById(R.id.second_recycler_view);

        VisualizzaFilmInComuneConUnAmico visualizzaFilmInComuneConUnAmico = new VisualizzaFilmInComuneConUnAmico(Utente.getIstanzaUtente().getIdUtente(),idAmico,this);
        visualizzaFilmInComuneConUnAmico.onTabFilmInComuneClicked(this,msrvPreferiti,mrsvDaVedere);
    }
}
