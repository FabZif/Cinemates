package com.example.cm.DAO;

import androidx.recyclerview.widget.RecyclerView;

public interface UtenteDAO {
    void getUtente(String testo, RecyclerView rvUtente);

    void postUtente(String email, String password, String nickname, String nome, String cognome);

    void getUtente2(String toString);

    void updateUtente(String nome, Integer idUtente);
}
