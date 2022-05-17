package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;
import com.example.cm.Models.Film;
import com.example.cm.View.Fragment.LeMieListe.FilmAdapter;
import com.example.cm.View.Fragment.LeMieListe.ListaDaVedereFragment;

import java.util.List;

public class VisualizzaListaFilmDaVederePersonale {
    Integer parametro;
    ListaDaVedereFragment listaDaVedereFragment;

    public VisualizzaListaFilmDaVederePersonale(int idUtente, ListaDaVedereFragment listaDaVedereFragment) {
        this.parametro = idUtente;
        this.listaDaVedereFragment = listaDaVedereFragment;
    }

    public static void onSuccessDaVedere(List<Film> listaFilm, RecyclerView rvFilm, Context context, Integer idUtente) {
        FilmAdapter adapter = new FilmAdapter(listaFilm, context, idUtente);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context));
    }


    public void onTabListaDaVedereClicked(ListaDaVedereFragment listaDaVedereFragment, RecyclerView rvFilm) {

        FilmDAO filmDAO = DAOFactory.getFilmDAO(this.listaDaVedereFragment.getActivity());
        filmDAO.getFilmDaVedere(parametro, rvFilm, 1);
    }
}
