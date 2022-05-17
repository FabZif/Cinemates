package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmAPIDAO;
import com.example.cm.Models.Film;
import com.example.cm.View.Activities.RicercaFilm.RicercaFilmAdapter;
import com.example.cm.View.Activities.RicercaFilm.RicercaFilmView;

import java.util.List;

public class RicercaFilm {
    Context context;
    String parametro;

    public RicercaFilm(String parametro, Context context) {
        this.context = context;
        this.parametro = parametro;
    }

    public static void onSuccess(List<Film> listaFilm, RecyclerView rvFilm, Context context) {

        RicercaFilmAdapter adapter = new RicercaFilmAdapter(listaFilm, context);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(context));

    }


    public void onButtonRicercaClicked(RicercaFilmView ricercaFilmView, RecyclerView rvFilm) {

        FilmAPIDAO filmAPIDAO = DAOFactory.getFilmAPIDAO(this.context);
        filmAPIDAO.getFilm(parametro, rvFilm);


    }


}
