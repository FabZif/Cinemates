package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;
import com.example.cm.Models.Film;
import com.example.cm.View.Activities.ProfiloUtente.FilmInComuneFragment;
import com.example.cm.View.Activities.ProfiloUtente.ListeDiFilmAmicoAdapter;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

public class VisualizzaFilmInComuneConUnAmico {

    Integer idUtente;
    Integer idAmico;
    FilmInComuneFragment filmInComuneFragment;

    public VisualizzaFilmInComuneConUnAmico(int idUtente, int idAmico, FilmInComuneFragment filmInComuneFragment) {
        this.idUtente = idUtente;
        this.idAmico = idAmico;
        this.filmInComuneFragment = filmInComuneFragment;
    }

    public static void OnSuccessDaVedere(List<Film> listaFilm, RecyclerView rvFilm, Context context) {
        ListeDiFilmAmicoAdapter adapter = new ListeDiFilmAmicoAdapter(context, listaFilm);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    public static void OnSuccessPreferiti(List<Film> listaFilm, RecyclerView rvFilm, Context context) {
        ListeDiFilmAmicoAdapter adapter = new ListeDiFilmAmicoAdapter(context, listaFilm);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }


    public void onTabFilmInComuneClicked(FilmInComuneFragment filmInComuneFragment, MultiSnapRecyclerView msrvPreferiti, MultiSnapRecyclerView msrvDaVedere) {

        FilmDAO filmDAO = DAOFactory.getFilmDAO(this.filmInComuneFragment.getActivity());

        filmDAO.getFilmInComuneDaVedere(idUtente, idAmico, msrvDaVedere);


        filmDAO.getFilmInComunePreferiti(idUtente, idAmico, msrvPreferiti);
    }
}
