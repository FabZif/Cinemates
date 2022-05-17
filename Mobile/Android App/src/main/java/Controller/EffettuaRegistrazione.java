package com.example.cm.Controller;

import android.content.Context;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.UtenteDAO;

public class EffettuaRegistrazione {

    Context context;

    public EffettuaRegistrazione(Context context) {
        this.context = context;
    }


    public void onButtonRegistratiClicked(String Email, String Password, String Nickname, String Nome, String Cognome) {

        UtenteDAO utenteDAO = DAOFactory.getUtenteDAO(context);
        utenteDAO.postUtente(Email, Password, Nickname, Nome, Cognome);


    }
}
