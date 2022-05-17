package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.ParereRapidoDAO;

public class AggiungiParereRapido {
    Context context;

    public AggiungiParereRapido(Context mContext) {
        context = mContext;
    }

    public void onButtonParereRapidoPreferitiClicked(int tipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente) {   //tipoParereRapido=0 per il mi piace...1 per il non mi piace!

        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        parereRapidoDAO.postParereRapidoInListaPreferiti(tipoParereRapido, idProfiloUtenteSelezionato, idMittente);

    }

    public void onButtonParereRapidoDaVedereClicked(int tipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente) {    //tipoParereRapido=0 per il mi piace...1 per il non mi piace!

        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        parereRapidoDAO.postParereRapidoInListaDaVedere(tipoParereRapido, idProfiloUtenteSelezionato, idMittente);
    }
}
