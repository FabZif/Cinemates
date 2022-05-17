package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.CommentoDAO;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.NotificaDAO;

public class InserisciCommento {
    Context context;

    public InserisciCommento(Context context) {
        this.context = context;
    }

    public void onButtonInviaClicked(Integer idProfiloUtenteSelezionato, Integer idMittente, Integer tipoLista, String testoCommento) {
        CommentoDAO commentoDAO = DAOFactory.getCommentoDAO(context);
        if (tipoLista == 0) {
            commentoDAO.postCommentoListaPreferiti(idProfiloUtenteSelezionato, idMittente, testoCommento);
        } else {
            commentoDAO.postCommentoListaDaVedere(idProfiloUtenteSelezionato, idMittente, testoCommento);
        }

        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO(context);
        notificaDAO.postNotifica(1, idProfiloUtenteSelezionato, idMittente);
    }
}
