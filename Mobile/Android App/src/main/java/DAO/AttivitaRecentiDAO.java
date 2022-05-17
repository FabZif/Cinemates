package com.example.cm.DAO;

import androidx.recyclerview.widget.RecyclerView;

public interface AttivitaRecentiDAO {
    void getAttivitaRecenti(Integer idUtente, RecyclerView rvAttivitaRecenti);
}
