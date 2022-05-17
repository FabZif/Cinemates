package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;
import com.example.cm.Models.Film;
import com.example.cm.View.Fragment.LeMieListe.FilmAdapter;
import com.example.cm.View.Fragment.LeMieListe.ListaPreferitiFragment;

import java.util.List;

public class VisualizzaListaFilmPreferitiPersonale {
    Integer parametro;
    ListaPreferitiFragment listaPreferitiFragment;

    public VisualizzaListaFilmPreferitiPersonale(Integer idUtente, ListaPreferitiFragment listaPreferitiFragment) {
        this.parametro = idUtente;
        this.listaPreferitiFragment = listaPreferitiFragment;
    }

    public static void onSuccessPreferiti(List<Film> listaFilm, RecyclerView rvFilm, Context context, Integer idUtente) {
        FilmAdapter adapter = new FilmAdapter(listaFilm, context, idUtente);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context));

    }

    public void onButtonFilmPreferitiClicked(ListaPreferitiFragment listaPreferitiFragment, RecyclerView rvFilm) {

        FilmDAO filmDAO = DAOFactory.getFilmDAO(this.listaPreferitiFragment.getActivity());
        filmDAO.getFilmPreferiti(parametro, rvFilm, 1);
    }


}
