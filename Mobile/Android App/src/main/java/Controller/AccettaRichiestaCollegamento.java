package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.CollegamentoDAO;
import com.example.cm.DAO.DAOFactory;

public class AccettaRichiestaCollegamento {

    Context context;

    public AccettaRichiestaCollegamento(Context context) {
        this.context = context;
    }

    public void onButtonAccettaClicked(Integer idUtente, Integer idAmico) {
        CollegamentoDAO collegamentoDAO = DAOFactory.getCollegamentoDAO(context);
        collegamentoDAO.postCollegamento(idUtente, idAmico);
    }
}
