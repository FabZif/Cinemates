package com.example.cm.DAO;

public interface CollegamentoDAO {
    void postCollegamento(Integer idUtente, Integer idAmico);

    void getCollegamento(Integer idAmico, Integer idUtente, String nickname, String immagine);
}
