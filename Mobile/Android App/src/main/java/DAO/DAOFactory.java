package com.example.cm.DAO;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

public abstract class DAOFactory {

    public static NotificaDAO getNotificaDAO(Context context) {
        return new NotificaAWS(context);
    }

    public static FilmAPIDAO getFilmAPIDAO(Context context) {
        return new FilmAPI(context);
    }

    public static FilmDAO getFilmDAO(FragmentActivity activity) {
        return new FilmAWS(activity);
    }


    public static UtenteDAO getUtenteDAO(Context context) {
        return new UtenteAWS(context);
    }

    public static AttivitaRecentiDAO getAttivitaRecentiDAO(FragmentActivity activity) {
        return new AttivitaRecentiAWS(activity);
    }

    public static CollegamentoDAO getCollegamentoDAO(Context context) {
        return new CollegamentoAWS(context);
    }

    public static ParereRapidoDAO getParereRapidoDAO(Context context) {
        return new ParereRapidoAWS(context);
    }

    public static ValutazioneDAO getValutazioneDAO(Context context) {
        return new ValutazioneAWS(context);
    }

    public static CommentoDAO getCommentoDAO(Context context) {
        return new CommentoAWS(context);
    }


}
