
package com.example.cm.View.Activities.Commento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.Controller.InserisciCommento;
import com.example.cm.Controller.VisualizzaCommento;
import com.example.cm.R;

public class CommentoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_commento);
        RecyclerView rvCommenti = findViewById(R.id.rvCommenti);
        Toolbar toolbarCommento =  findViewById(R.id.toolbar2);
        ImageButton inviaCommento = findViewById(R.id.InviaCommento);
        final EditText TestoCommento = findViewById(R.id.TestoCommento);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        toolbarCommento.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        final Integer idUtenteSelezionato =  getIntent().getIntExtra("idProfiloUtenteSelezionato",-1);
        final Integer idMittente = getIntent().getIntExtra("idMittente",-1);
        final Integer tipoLista = getIntent().getIntExtra("tipoLista",-1);

        VisualizzaCommento visualizzaCommento = new VisualizzaCommento(this);
        visualizzaCommento.onButtonCommentiClicked(idUtenteSelezionato,tipoLista,rvCommenti);
        inviaCommento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TestoCommento.getText().toString().length() > 1000) {
                    Toast.makeText(v.getContext(), "Hai superato il limite di 1000 caratteri", Toast.LENGTH_SHORT).show();
                } else {
                    if (TestoCommento.getText().toString().isEmpty()) {
                        Toast.makeText(v.getContext(), "Scrivi qualcosa prima di inviare", Toast.LENGTH_SHORT).show();
                    } else {
                        InserisciCommento inserisciCommento = new InserisciCommento(v.getContext());
                        inserisciCommento.onButtonInviaClicked(idUtenteSelezionato, idMittente, tipoLista, TestoCommento.getText().toString());

                        TestoCommento.setText("");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        finish();
                        overridePendingTransition( 0, 0);
                        progressBar.setVisibility(View.VISIBLE);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);

                    }
                }
            }
        });

    }



}