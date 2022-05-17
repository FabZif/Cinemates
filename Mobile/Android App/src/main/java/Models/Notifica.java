package com.example.cm.Models;

public class Notifica implements Comparable<Notifica> {
    private Integer idNotifica;
    private Integer idUtente;
    private String Nickname;
    private String Data;
    private Integer Tipo;
    private String Immagine;
    private String TestoNotifica;

    public Notifica(Integer idNotifica, Integer idUtente,String nickname, String data, Integer tipo,String immagine,String TestoNotifica) {
        this.idNotifica = idNotifica;
        this.idUtente=idUtente;
        Nickname=nickname;
        Data = data;
        Tipo = tipo;
        Immagine = immagine;
        this.TestoNotifica = TestoNotifica;
    }

    public Notifica(int idNotifica, String data, Integer tipo, String immagine, String testo) {
        this.idNotifica=idNotifica;
        Data = data;
        Tipo = tipo;
        Immagine = immagine;
        TestoNotifica = testo;
    }

    public Integer getIdNotifica() {
        return idNotifica;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public String getNickname() {
        return Nickname;
    }

    public String getData() {
        return Data;
    }

    public Integer getTipo() {
        return Tipo;
    }

    public String getImmagine() {
        return Immagine;
    }
    public String getTestoNotifica() {
        return TestoNotifica;
    }

    @Override
    public int compareTo(Notifica o) {
        return getData().compareTo(o.getData());
    }
}
