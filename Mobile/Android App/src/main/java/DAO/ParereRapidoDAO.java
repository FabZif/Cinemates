package com.example.cm.DAO;

import android.widget.ImageButton;
import android.widget.TextView;

public interface ParereRapidoDAO {

    void postParereRapidoInListaPreferiti(Integer tipoParereRapido, Integer idLista, Integer idValutatore);  //tipoParereRapido=0 per il mi piace...1 per il non mi piace!

    void postParereRapidoInListaDaVedere(Integer tipoParereRapido, Integer idLista, Integer idValutatore);

    void controllaParereRapido(Integer TipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente, ImageButton miPiace, ImageButton nonMiPiace);

    void deleteParereRapidoInListaPreferiti(Integer tipoParereRapido, Integer idMittente, Integer idProfiloUtenteSelezionato);

    void deleteParereRapidoInListaDaVedere(Integer tipoParereRapido, Integer idMittente, Integer idProfiloUtenteSelezionato);

    void getEControllaPresenzaParereRapidoDaVedere(Integer TipoParereRapidoSelezionato, Integer idProfiloUtenteSelezionato, int idMittente, ImageButton miPiace, ImageButton nonMiPiace);

    void getNumeroMiPiace(Integer tipoLista, Integer idUtente, TextView miPiace);
}
