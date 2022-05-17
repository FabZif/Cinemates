package com.example.cm.Controller;

import android.content.Context;
import android.content.Intent;

import com.example.cm.DAO.CollegamentoDAO;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.View.Activities.ProfiloUtente.ProfiloUtenteNonCollegatoView;
import com.example.cm.View.Activities.ProfiloUtente.ProfiloUtenteView;

public class ControllaEsistenzaCollegamento {

    Context context;

    public ControllaEsistenzaCollegamento(Context context) {
        this.context = context;
    }

    public static void nonCollegato(Integer idAmico, Context context, String Nickname, String Immagine) {
        Intent intent = new Intent(context, ProfiloUtenteNonCollegatoView.class);
        intent.putExtra("idAmico", idAmico);
        intent.putExtra("Immagine", Immagine);
        intent.putExtra("Nickname", Nickname);
        context.startActivity(intent);
    }

    public static void collegato(Integer idAmico, String immagine, String nickname, Context context) {
        Intent intent = new Intent(context, ProfiloUtenteView.class);
        intent.putExtra("idUtente", idAmico);
        intent.putExtra("Immagine", immagine);
        intent.putExtra("Nickname", nickname);
        context.startActivity(intent);
    }

    public void onButtonProfiloClicked(Integer idAmico, String nickname, String immagine, Integer idUtente) {
        CollegamentoDAO collegamentoDAO = DAOFactory.getCollegamentoDAO(context);
        collegamentoDAO.getCollegamento(idAmico, idUtente, nickname, immagine);
    }
}
