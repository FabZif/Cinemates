package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.NotificaDAO;
import com.example.cm.DAO.ValutazioneDAO;

public class AggiungiValutazione {

    Context context;


    public AggiungiValutazione(Context context) {
        this.context = context;
    }


    public static void aggiungiValutazione(Integer idProfiloUtenteSelezionato, Integer idMittente, Float valutazione, Context context, Integer filtroTipoLista) {
        if (filtroTipoLista == 0) {
            ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(context);
            valutazioneDAO.postValutazionePreferiti(idProfiloUtenteSelezionato, idMittente, valutazione);

        } else {
            ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(context);
            valutazioneDAO.postValutazioneDaVedere(idProfiloUtenteSelezionato, idMittente, valutazione);
        }
        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO(context);
        notificaDAO.postNotifica(2, idProfiloUtenteSelezionato, idMittente);
    }

    public static void updateValutazione(Integer idValutazione, String strDate, Float valutazione, Context context) {
        ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(context);
        valutazioneDAO.updateValutazione(idValutazione, strDate, valutazione, context);
    }


    public void onButtonValutaPreferitiClicked(Integer idProfiloUtenteSelezionato, Integer idMittente, Float Valutazione) {
        ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(context);
        valutazioneDAO.getValutazione(0, idProfiloUtenteSelezionato, idMittente, Valutazione);

    }

    public void onButtonValutaDaVedereClicked(Integer idProfiloUtenteSelezionato, Integer idMittente, Float Valutazione) {
        ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(context);
        valutazioneDAO.getValutazione(1, idProfiloUtenteSelezionato, idMittente, Valutazione);
    }


}
