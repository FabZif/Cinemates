package com.example.cm.Controller;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;
import com.example.cm.Models.Film;

public class AggiungiAListaDaVederePersonale {
    Film film;
    Context context;

    public AggiungiAListaDaVederePersonale(Film film, Context context) {
        this.film = film;
        this.context = context;
    }


    public void onButtonDaVedereClicked(Integer idUtente) {
        FilmDAO filmDAO = DAOFactory.getFilmDAO((FragmentActivity) context);
        filmDAO.postFilmDaVedere(film, idUtente);

    }
}
