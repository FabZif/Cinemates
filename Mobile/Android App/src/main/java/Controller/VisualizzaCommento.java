package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.CommentoDAO;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.Models.Commento;
import com.example.cm.View.Activities.Commento.CommentoAdapter;

import java.util.List;

public class VisualizzaCommento {

    Context context;

    public VisualizzaCommento(Context context) {
        this.context = context;
    }

    public static void OnSuccessGetCommento(List<Commento> listaCommenti, RecyclerView rvCommenti, Context context) {
        CommentoAdapter adapter = new CommentoAdapter(context, listaCommenti);
        rvCommenti.setAdapter(adapter);
        rvCommenti.setLayoutManager(new LinearLayoutManager(context));
    }


    public void onButtonCommentiClicked(Integer idProfiloUtenteSelezionato, Integer tipoLista, RecyclerView rvCommenti) {
        if (tipoLista == 0) {
            CommentoDAO commentoDAO = DAOFactory.getCommentoDAO(context);
            commentoDAO.getCommentoListaPreferiti(idProfiloUtenteSelezionato, rvCommenti);
        } else {
            CommentoDAO commentoDAO = DAOFactory.getCommentoDAO(context);
            commentoDAO.getCommentoListaDaVedere(idProfiloUtenteSelezionato, rvCommenti);
        }

    }
}
