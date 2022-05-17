package com.example.cm.Models;

import android.util.Log;

public class Utente {
    private static Utente utente;
    private Integer idUtente;
    private String Nome;
    private String Cognome;
    private String Nickname;
    private String Immagine;
    private String Email;

    public Utente(Integer idUtente, String nome, String cognome, String nickname, String immagine) {
        this.idUtente = idUtente;
        Nome = nome;
        Cognome = cognome;
        Nickname = nickname;
        Immagine = immagine;
    }

    public Utente(Integer idUtente, String nome, String cognome, String nickname, String immagine,String email) {
        this.idUtente = idUtente;
        Nome = nome;
        Cognome = cognome;
        Nickname = nickname;
        Immagine = immagine;
        Email = email;
    }





    public static void InizializzaUtente(int idUtente, String nome, String cognome, String nickname, String immagine,String email) {
            utente=new Utente(idUtente,nome,cognome,nickname,immagine,email);

    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public String getNome() {
        return Nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public String getNickname() {
        return Nickname;
    }

    public String getImmagine() {
        return Immagine;
    }

    public static Utente getIstanzaUtente() {
        return utente;
    }

    public void setImmagine(String immagine) {
        Immagine = immagine;
    }


}
