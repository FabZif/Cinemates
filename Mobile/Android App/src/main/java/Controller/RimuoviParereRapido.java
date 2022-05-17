package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.ParereRapidoDAO;

public class RimuoviParereRapido {
    Context context;

    public RimuoviParereRapido(Context context) {
        this.context = context;

    }

    public void onButtonRimuoviParerePreferitoClicked(Integer tipoParereRapido, Integer idMittente, Integer idProfiloUtenteSelezionato) { //tipoParereRapido=0 per il mi piace...1 per il non mi piace!

        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        parereRapidoDAO.deleteParereRapidoInListaPreferiti(tipoParereRapido, idMittente, idProfiloUtenteSelezionato);

    }

    public void onButtonRimuoviParereDaVedereClicked(Integer tipoParereRapido, Integer idMittente, Integer idProfiloUtenteSelezionato) {
        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        parereRapidoDAO.deleteParereRapidoInListaDaVedere(tipoParereRapido, idMittente, idProfiloUtenteSelezionato);
    }
}
