package com.example.cm.Controller;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.AttivitaRecentiDAO;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.Models.AttivitaRecenti;
import com.example.cm.View.Activities.ProfiloUtente.AttivitaRecentiAdapter;
import com.example.cm.View.Activities.ProfiloUtente.AttivitaRecentiFragment;

import java.util.List;

public class VisualizzaAttivitaRecenti {

    private final int idUtente;
    AttivitaRecentiFragment attivitàRecenti;

    public VisualizzaAttivitaRecenti(int idUtente, AttivitaRecentiFragment attivitaRecentiFragment) {
        this.idUtente = idUtente;
        this.attivitàRecenti = attivitaRecentiFragment;
    }

    public static void onSuccess(List<AttivitaRecenti> listaAttivita, RecyclerView rvAttivitaRecenti, Context context) {
        if (listaAttivita.isEmpty()) {
            Toast toast = Toast.makeText(context, "L'UTENTE NON HA ATTIVITA' RECENTI!", Toast.LENGTH_SHORT);
            toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            toast.show();
        } else {
            AttivitaRecentiAdapter adapter = new AttivitaRecentiAdapter(context, listaAttivita);
            rvAttivitaRecenti.setAdapter(adapter);
            rvAttivitaRecenti.setLayoutManager(new LinearLayoutManager(context));
        }
    }


    public void onTabAttivitaRecentiClicked(AttivitaRecentiFragment attivitaRecentiFragment, RecyclerView rvAttivitaRecenti) {
        AttivitaRecentiDAO attivitaRecentiDAO = DAOFactory.getAttivitaRecentiDAO(this.attivitàRecenti.getActivity());
        attivitaRecentiDAO.getAttivitaRecenti(idUtente, rvAttivitaRecenti);
    }
}
