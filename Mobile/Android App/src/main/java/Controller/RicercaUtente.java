package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.UtenteDAO;
import com.example.cm.Models.Utente;
import com.example.cm.View.Fragment.Collegamenti.CollegamentiFragment;
import com.example.cm.View.Fragment.Collegamenti.UtenteAdapter;

import java.util.List;

public class RicercaUtente {

    private final String testo;
    private final CollegamentiFragment collegamenti;

    public RicercaUtente(String testo, CollegamentiFragment collegamenti) {
        this.testo = testo;
        this.collegamenti = collegamenti;
    }

    public static void onSuccess(List<Utente> listaUtenti, RecyclerView rvUtente, Context context) {
        if (!listaUtenti.isEmpty()) {
            UtenteAdapter adapter = new UtenteAdapter(listaUtenti, context);
            rvUtente.setAdapter(adapter);
            rvUtente.setLayoutManager(new LinearLayoutManager(context));
        }
    }

    public void onButtonRicercaUtenteClicked(CollegamentiFragment collegamenti, RecyclerView rvUtente) {

        UtenteDAO utenteDAO = DAOFactory.getUtenteDAO(this.collegamenti.getActivity());
        utenteDAO.getUtente(testo, rvUtente);
    }


}
