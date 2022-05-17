package com.example.cm.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttivitaRecenti implements Comparable<AttivitaRecenti>  {
    private String Nickname;
    private Integer Tipo;
    private String Destinatario;
    private String Titolo;
    private   Date dateTime;
    private Integer TipoLista;
    private String Immagine;

    public AttivitaRecenti(String nickname, Integer tipo, String destinatario, String titolo, String data,int TipoLista,String immagine) throws ParseException {
        dateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(data);
        Nickname = nickname;
        Tipo = tipo;
        Destinatario = destinatario;
        Titolo = titolo;
        this.TipoLista=TipoLista;
        Immagine = immagine;

    }



    public String getNickname() {
        return Nickname;
    }

    public Integer getTipo() {
        return Tipo;
    }

    public Integer getTipoLista() {
        return TipoLista;
    }

    public String getDestinatario() {
        return Destinatario;
    }

    public String getTitolo() {
        return Titolo;
    }

    public Date getData() {
        return dateTime;
    }

    public String getImmagine() {
        return Immagine;
    }

    @Override
    public int compareTo(AttivitaRecenti o) {
        return getData().compareTo(o.getData());
    }


}
