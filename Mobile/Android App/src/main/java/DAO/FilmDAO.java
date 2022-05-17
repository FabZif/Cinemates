package com.example.cm.DAO;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Models.Film;

public interface FilmDAO {
    void getFilmDaVedere(Integer parametro, RecyclerView rvFilm, int TipoVisualizzazione);

    void getFilmInComuneDaVedere(Integer idUtente, Integer idAmico, RecyclerView rvFilm);

    void postFilmDaVedere(Film film, Integer idUtente);

    void deleteFilmDaVedere(Integer idFilm, Integer idUtente);

    void getFilmPreferiti(Integer parametro, RecyclerView rvFilm, int TipoVisualizzazione);

    void getFilmInComunePreferiti(Integer idUtente, Integer idAmico, RecyclerView rvFilm);

    void postFilmPreferiti(Film film, Integer idUtente);

    void deleteFilmPreferiti(Integer idFilm,Integer idUtente);
}
