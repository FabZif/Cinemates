package com.example.cm.DAO;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public interface CommentoDAO {
    public void getCommentoListaPreferiti(Integer idProfiloUtenteSelezionato, RecyclerView rvCommenti);

    public void getCommentoListaDaVedere(Integer idProfiloUtenteSelezionato, RecyclerView rvCommenti);

    void postCommentoListaPreferiti(Integer idProfiloUtenteSelezionato, Integer idMittente, String testoCommento);

    void postCommentoListaDaVedere(Integer idProfiloUtenteSelezionato, Integer idMittente, String testoCommento);

    void getNumeroCommenti(Integer tipoLista, Integer idUtente, TextView commenti);
}
