package com.example.cm.Controller;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;
import com.example.cm.Models.Film;

public class AggiungiAListaPreferitiPersonale {

    Film film;
    Context context;

    public AggiungiAListaPreferitiPersonale(Film film, Context context) {
        this.film = film;
        this.context = context;
    }


    public void onButtonPreferitiClicked(Integer idUtente) {
        FilmDAO filmDAO = DAOFactory.getFilmDAO((FragmentActivity) context);
        filmDAO.postFilmPreferiti(film, idUtente);

    }
}
