package com.example.cm.View.Activities.Notifica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cm.Controller.VisualizzaNotifiche;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.example.cm.View.Activities.Homepage;

public class NotificaView extends AppCompatActivity {
   
    Context context = this;




    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifica);
        RecyclerView rvNotifiche = findViewById(R.id.rvNotifiche);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Notifiche");
        toolbar.setTitleTextColor(Color.WHITE);
        SwipeRefreshLayout refreshLayout;
        refreshLayout = findViewById(R.id.RefreshNotifiche2);





        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
            }
        });

        final VisualizzaNotifiche visualizzaNotifiche = new VisualizzaNotifiche(Utente.getIstanzaUtente().getIdUtente(), context);
        visualizzaNotifiche.onButtonNotificheClicked(this,rvNotifiche);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

    }


    public void refresh() {
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }

}