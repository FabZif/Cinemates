package com.example.cm.DAO;

import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

public interface NotificaDAO {

    void getNotifica(Integer idUtente, RecyclerView rvNotifiche);

    void deleteNotifica(Integer idUtente);

    void postNotifica(int tipoNotifica, Integer idProfiloUtenteSelezionato, int idMittente);

    void controllaRichiestaCollegamento(Integer tipoNotifica, Integer idProfiloUtenteSelezionato, Integer idMittente, ImageButton aggiungi);
}
