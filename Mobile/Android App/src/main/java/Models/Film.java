package com.example.cm.Models;

public class Film {
    private  Integer idFilm;
    private String Titolo;
    private String Immagine;
    private String Trama;
    private Integer ListaAppartenenza;  //0 PER LISTA FILM PREFERITI
                                        //1 PER LISTA FILM DA VEDERE

    public Integer getIdFilm() {
        return idFilm;
    }

    public String getTitolo() {
        return Titolo;
    }

    public String getImmagine() {
        return Immagine;
    }

    public String getTrama() {
        return Trama;
    }

    public Integer getListaAppartenenza() {
        return ListaAppartenenza;
    }

    public Film(Integer idFilm, String titolo, String immagine, String trama, Integer ListaAppartenenza) {
        this.idFilm = idFilm;
        Titolo = titolo;
        Immagine = immagine;
        Trama = trama;
        this.ListaAppartenenza=ListaAppartenenza;
    }

    public  Film(Integer idFilm, String titolo, String immagine, String trama) {
        this.idFilm = idFilm;
        Titolo = titolo;
        Immagine = immagine;
        Trama = trama;
    }
}
