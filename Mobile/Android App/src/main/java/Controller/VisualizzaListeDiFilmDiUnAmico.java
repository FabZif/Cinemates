package com.example.cm.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;
import com.example.cm.Models.Film;
import com.example.cm.View.Activities.ProfiloUtente.ListeDiFilmAmicoAdapter;
import com.example.cm.View.Activities.ProfiloUtente.ListeDiFilmFragment;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

public class VisualizzaListeDiFilmDiUnAmico {
    Integer idUtente;
    ListeDiFilmFragment listeDiFilmFragment;

    public VisualizzaListeDiFilmDiUnAmico(Integer idUtente, ListeDiFilmFragment listeDiFilmFragment) {
        this.idUtente = idUtente;
        this.listeDiFilmFragment = listeDiFilmFragment;
    }

    public static void onSuccessDaVedere(List<Film> listaFilm, RecyclerView rvFilm, Context context) {


        ListeDiFilmAmicoAdapter adapter = new ListeDiFilmAmicoAdapter(context, listaFilm);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    public static void onSuccessPreferiti(List<Film> listaFilm, RecyclerView rvFilm, Context context) {
        ListeDiFilmAmicoAdapter adapter = new ListeDiFilmAmicoAdapter(context, listaFilm);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

    }


    public void onTabListeDiFilmClicked(ListeDiFilmFragment listeDiFilmFragment, MultiSnapRecyclerView msrvPreferiti, MultiSnapRecyclerView msrvDaVedere) {

        FilmDAO filmDAO = DAOFactory.getFilmDAO(this.listeDiFilmFragment.getActivity());

        filmDAO.getFilmDaVedere(idUtente, msrvDaVedere, 0);
        filmDAO.getFilmPreferiti(idUtente, msrvPreferiti, 0);
    }
}
