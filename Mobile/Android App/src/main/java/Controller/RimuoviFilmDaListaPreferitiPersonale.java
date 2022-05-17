package com.example.cm.Controller;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.FilmDAO;

public class RimuoviFilmDaListaPreferitiPersonale {
    Context context;


    public RimuoviFilmDaListaPreferitiPersonale(Context context) {
        this.context = context;
    }


    public void onButtonRimuoviPreferitoClicked(Integer idFilm, Integer idUtente, Context context) {
        FilmDAO filmDAO = DAOFactory.getFilmDAO((FragmentActivity) context);
        filmDAO.deleteFilmPreferiti(idFilm, idUtente);
    }
}
