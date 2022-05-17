package com.example.cm.Controller;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.ParereRapidoDAO;
import com.example.cm.DAO.ValutazioneDAO;
import com.example.cm.Models.Utente;
import com.example.cm.R;

public class RecuperaInterazioni {
    Context context;

    public RecuperaInterazioni(Context context) {
        this.context = context;
    }

    public static void azioneSulClickParereRapidoPreferiti(Integer TipoParereRapidoSelezionato, final ImageButton miPiace, ImageButton nonMiPiace, final int idMittente, final Integer idProfiloUtenteSelezionato, final Boolean presenteMiPiace, final Boolean presenteNonMiPiace, Context context) {

        if (TipoParereRapidoSelezionato == 0) {


            if (presenteMiPiace == false && presenteNonMiPiace == false) {
                AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                aggiungiParereRapido.onButtonParereRapidoPreferitiClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());//tipoParereRapido=0 per il mi piace...1 per il non mi piace!
                miPiace.setImageResource(R.drawable.thumb_up_clicked);
                AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


            } else {
                if (presenteMiPiace == true) {  //CASO IN CUI IL MI PIACE E' GIA CLICCATO
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParerePreferitoClicked(0, idMittente, idProfiloUtenteSelezionato);
                    miPiace.setImageResource(R.drawable.ic_baseline_thumb_up_24);

                } else {
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParerePreferitoClicked(1, idMittente, idProfiloUtenteSelezionato);
                    nonMiPiace.setImageResource(R.drawable.ic_baseline_thumb_down_24);

                    AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                    aggiungiParereRapido.onButtonParereRapidoPreferitiClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());
                    miPiace.setImageResource(R.drawable.thumb_up_clicked);//tipoParereRapido=0 per il mi piace...1 per il non mi piace!

                    AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                    aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


                }
            }

        } else {

            if (presenteMiPiace == false && presenteNonMiPiace == false) {
                AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                aggiungiParereRapido.onButtonParereRapidoPreferitiClicked(1, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente()); //tipoParereRapido=0 per il mi piace...1 per il non mi piace!
                nonMiPiace.setImageResource(R.drawable.thumbs_down_clicked);
                AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


            } else {
                if (presenteNonMiPiace == true) {  //CASO IN CUI IL MI PIACE E' GIA CLICCATO
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParerePreferitoClicked(1, idMittente, idProfiloUtenteSelezionato);
                    nonMiPiace.setImageResource(R.drawable.ic_baseline_thumb_down_24);
                } else {
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParerePreferitoClicked(0, idMittente, idProfiloUtenteSelezionato);
                    miPiace.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                    AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                    aggiungiParereRapido.onButtonParereRapidoPreferitiClicked(1, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente()); //tipoParereRapido=0 per il mi piace...1 per il non mi piace!
                    nonMiPiace.setImageResource(R.drawable.thumbs_down_clicked);

                    AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                    aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


                }
            }


        }


    }

    public static void azioneSulClickParereRapidoDaVedere(Integer TipoParereRapidoSelezionato, final ImageButton miPiace, ImageButton nonMiPiace, final int idMittente, final Integer idProfiloUtenteSelezionato, final Boolean presenteMiPiace, final Boolean presenteNonMiPiace, Context context) {

        if (TipoParereRapidoSelezionato == 0) {


            if (presenteMiPiace == false && presenteNonMiPiace == false) {
                AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                aggiungiParereRapido.onButtonParereRapidoDaVedereClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());//tipoParereRapido=0 per il mi piace...1 per il non mi piace!
                miPiace.setImageResource(R.drawable.thumb_up_clicked);
                AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


            } else {
                if (presenteMiPiace == true) {  //CASO IN CUI IL MI PIACE E' GIA CLICCATO
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParereDaVedereClicked(0, idMittente, idProfiloUtenteSelezionato);
                    miPiace.setImageResource(R.drawable.ic_baseline_thumb_up_24);

                } else {
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParereDaVedereClicked(1, idMittente, idProfiloUtenteSelezionato);
                    nonMiPiace.setImageResource(R.drawable.ic_baseline_thumb_down_24);

                    AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                    aggiungiParereRapido.onButtonParereRapidoDaVedereClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());
                    miPiace.setImageResource(R.drawable.thumb_up_clicked);//tipoParereRapido=0 per il mi piace...1 per il non mi piace!

                    AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                    aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


                }
            }

        } else {

            if (presenteMiPiace == false && presenteNonMiPiace == false) {
                AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                aggiungiParereRapido.onButtonParereRapidoDaVedereClicked(1, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente()); //tipoParereRapido=0 per il mi piace...1 per il non mi piace!
                nonMiPiace.setImageResource(R.drawable.thumbs_down_clicked);
                AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


            } else {
                if (presenteNonMiPiace == true) {  //CASO IN CUI IL MI PIACE E' GIA CLICCATO
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParereDaVedereClicked(1, idMittente, idProfiloUtenteSelezionato);
                    nonMiPiace.setImageResource(R.drawable.ic_baseline_thumb_down_24);
                } else {
                    RimuoviParereRapido rimuoviParereRapido = new RimuoviParereRapido(context);
                    rimuoviParereRapido.onButtonRimuoviParereDaVedereClicked(0, idMittente, idProfiloUtenteSelezionato);
                    miPiace.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                    AggiungiParereRapido aggiungiParereRapido = new AggiungiParereRapido(context);
                    aggiungiParereRapido.onButtonParereRapidoDaVedereClicked(1, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente()); //tipoParereRapido=0 per il mi piace...1 per il non mi piace!
                    nonMiPiace.setImageResource(R.drawable.thumbs_down_clicked);

                    AggiungiNotifica aggiungiNotifica = new AggiungiNotifica(context);
                    aggiungiNotifica.onButtonAzioneClicked(0, idProfiloUtenteSelezionato, Utente.getIstanzaUtente().getIdUtente());


                }
            }


        }


    }

    public static void onSuccessGetParereRapidoSelezionato(Boolean miPiaceSelezionato, Boolean nonMiPiaceSelezionato, ImageButton miPiace, ImageButton nonMiPiace) {
        if (miPiaceSelezionato == true) {
            miPiace.setImageResource(R.drawable.thumb_up_clicked);
        } else if (nonMiPiaceSelezionato == true) {
            nonMiPiace.setImageResource(R.drawable.thumbs_down_clicked);
        }
    }

    public void onButtonMiPiaceClicked(int tipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente, ImageButton miPiace, ImageButton nonMiPiace, Integer filtroLista) {
        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        if (filtroLista == 0) {
            parereRapidoDAO.controllaParereRapido(tipoParereRapido, idProfiloUtenteSelezionato, idMittente, miPiace, nonMiPiace);
        } else {
            parereRapidoDAO.getEControllaPresenzaParereRapidoDaVedere(tipoParereRapido, idProfiloUtenteSelezionato, idMittente, miPiace, nonMiPiace);
        }
    }

    public void onButtonNonMiPiaceClicked(int tipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente, ImageButton miPiace, ImageButton nonMiPiace, Integer filtroLista) {
        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        if (filtroLista == 0) {
            parereRapidoDAO.controllaParereRapido(tipoParereRapido, idProfiloUtenteSelezionato, idMittente, miPiace, nonMiPiace);
        } else {
            parereRapidoDAO.getEControllaPresenzaParereRapidoDaVedere(tipoParereRapido, idProfiloUtenteSelezionato, idMittente, miPiace, nonMiPiace);
        }
    }

    public void getParereRapidoSelezionatoPreferiti(Integer TipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente, ImageButton miPiace, ImageButton nonMiPiace) {
        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        parereRapidoDAO.controllaParereRapido(TipoParereRapido, idProfiloUtenteSelezionato, idMittente, miPiace, nonMiPiace);
    }

    public void getParereRapidoSelezionatoDaVedere(Integer TipoParereRapido, Integer idProfiloUtenteSelezionato, int idMittente, ImageButton miPiace, ImageButton nonMiPiace) {
        ParereRapidoDAO parereRapidoDAO = DAOFactory.getParereRapidoDAO(context);
        parereRapidoDAO.getEControllaPresenzaParereRapidoDaVedere(TipoParereRapido, idProfiloUtenteSelezionato, idMittente, miPiace, nonMiPiace);
    }

    public void setRating(Integer idProfiloUtenteSelezionato, int idMittente, RatingBar valutaPreferiti, RatingBar valutaDaVedere) {
        ValutazioneDAO valutazioneDAO = DAOFactory.getValutazioneDAO(context);
        valutazioneDAO.getESetRatingBar(0, idProfiloUtenteSelezionato, idMittente, valutaPreferiti);
        valutazioneDAO.getESetRatingBar(1, idProfiloUtenteSelezionato, idMittente, valutaDaVedere);
    }
}
