package com.example.cm.DAO;

import android.content.Context;
import android.widget.RatingBar;
import android.widget.TextView;

public interface ValutazioneDAO {
    void postValutazionePreferiti(Integer idProfiloUtenteSelezionato, Integer idMittente, Float Valutazione);

    void postValutazioneDaVedere(Integer idProfiloUtenteSelezionato, Integer idMittente, Float Valutazione);

    void getValutazione(Integer filtroTipoLista, Integer idProfiloUtenteSelezionato, Integer idMittente, Float valutazione);

    void updateValutazione(Integer idValutazione, String strDate, Float valutazione, Context context);

    void getESetRatingBar(Integer filtroTipoLista, Integer idProfiloUtenteSelezionato, int idMittente, RatingBar ratingBar);

    void getMediaValutazioni(Integer tipoLista, Integer idUtente, TextView media);
}
