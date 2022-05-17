package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.NotificaDAO;

public class RifiutaRichiestaCollegamento {
    Context context;

    public RifiutaRichiestaCollegamento(Context context) {
        this.context = context;
    }

    public void onButtonRimuoviClicked(Integer idNotifica) {
        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO(context);
        notificaDAO.deleteNotifica(idNotifica);
    }
}
