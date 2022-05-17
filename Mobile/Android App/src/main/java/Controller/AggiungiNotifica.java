package com.example.cm.Controller;

import android.content.Context;
import android.util.Log;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.NotificaDAO;

public class AggiungiNotifica {

    Context context;

    public AggiungiNotifica(Context context) {
        this.context = context;
    }

    public void onButtonAzioneClicked(int TipoNotifica, Integer idProfiloUtenteSelezionato, int idMittente) {
        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO(context);

        notificaDAO.postNotifica(TipoNotifica, idProfiloUtenteSelezionato, idMittente);

    }
}
