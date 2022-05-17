package com.example.cm.Controller;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.NotificaDAO;

public class ControllaRichiestaCollegamento {

    Context context;

    public ControllaRichiestaCollegamento(Context context) {

        this.context = context;

    }

    public static void richiestaCollegamentoPresente(ImageButton aggiungi, Context context) {
        Toast.makeText(context, "HAI GIA' INVIATO UNA RICHIESTA DI COLLEGAMENTO", Toast.LENGTH_SHORT).show();

    }

    public static void richiestaCollegamentoNonPresente(Integer idProfiloUtenteSelezionato, Integer idMittente, Context context) {
        AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
        aggiungiNotifica.onButtonAzioneClicked(3, idProfiloUtenteSelezionato, idMittente);
    }

    public void onButtonAggiungiClicked(Integer tipoNotifica, Integer idProfiloUtenteSelezionato, Integer idMittente, ImageButton aggiungi) {
        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO(context);
        notificaDAO.controllaRichiestaCollegamento(tipoNotifica, idProfiloUtenteSelezionato, idMittente, aggiungi);
    }
}
