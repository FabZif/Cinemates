package com.example.cm.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.NotificaDAO;
import com.example.cm.Models.Notifica;
import com.example.cm.View.Activities.Notifica.NotificaAdapter;
import com.example.cm.View.Activities.Notifica.NotificaView;

import java.util.List;

public class VisualizzaNotifiche {
    List<Notifica> listaNotifiche;
    private Integer idUtente;
    private Context context;

    public VisualizzaNotifiche(Integer idUtente, Context context) {
        this.idUtente = idUtente;
        this.context = context;
    }

    public static void onSuccess(List<Notifica> listaNotifica, RecyclerView rvNotifiche, Context context) {

        NotificaAdapter adapter = new NotificaAdapter(listaNotifica, context);
        rvNotifiche.setAdapter(adapter);
        rvNotifiche.setLayoutManager(new LinearLayoutManager(context));

    }


    public void onButtonNotificheClicked(NotificaView notificaView, RecyclerView rvNotifiche) {
        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO(this.context);
        notificaDAO.getNotifica(idUtente, rvNotifiche);


    }

}
