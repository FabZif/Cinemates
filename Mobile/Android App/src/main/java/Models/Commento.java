package com.example.cm.Models;

public class Commento {

    private String Testo;
    private String Data;
    private String Nickname;

    public Commento(String testo, String data, String nickname) {
        Testo = testo;
        Data = data;
        Nickname = nickname;
    }

    public String getTesto() {
        return Testo;
    }

    public String getData() {
        return Data;
    }

    public String getNickname() {
        return Nickname;
    }
}
