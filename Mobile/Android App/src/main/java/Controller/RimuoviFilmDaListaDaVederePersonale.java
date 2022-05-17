package com.example.cm.Controller;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;

public class RimuoviFilmDaListaDaVederePersonale {

    Context context;

    public RimuoviFilmDaListaDaVederePersonale(Context context) {
        this.context = context;
    }


    public void onButtonRimuoviDaVedereClicked(Integer idFilm, Integer idUtente, Context context) {
        FilmDAO filmDAO = DAOFactory.getFilmDAO((FragmentActivity) context);
        filmDAO.deleteFilmDaVedere(idFilm, idUtente);

    }
}
