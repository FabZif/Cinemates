package com.example.cm.View.Activities.ProfiloUtente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cm.Controller.ControllaRichiestaCollegamento;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

public class ProfiloUtenteNonCollegatoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_utente_non_collegato);

        ShapeableImageView ImmagineProfilo = findViewById(R.id.ImmagineProfiloUtenteNonCollegato);


        TextView Nickname = findViewById(R.id.NicknameProfiloNonCollegato);
        final ImageButton aggiungi = findViewById(R.id.AggiungiAgliAmici);

        Toolbar toolbar = findViewById(R.id.toolbar4);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        final Integer id = intent.getIntExtra("idAmico",-1);
        String image = intent.getStringExtra("Immagine");
        String nick = intent.getStringExtra("Nickname");
        Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+image).resize(150,150).into(ImmagineProfilo);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggiungi.setImageResource(R.drawable.aggiungiclicked);

                ControllaRichiestaCollegamento controllaRichiestaCollegamento = new ControllaRichiestaCollegamento(getApplicationContext());
                controllaRichiestaCollegamento.onButtonAggiungiClicked(3,id, Utente.getIstanzaUtente().getIdUtente(),aggiungi);
            }
        });

        Nickname.setText(nick);
        //AGGIUNGERE IMMAGINE CON PICASSO





    }
}