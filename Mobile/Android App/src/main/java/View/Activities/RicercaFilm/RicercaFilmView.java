package com.example.cm.View.Activities.RicercaFilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.cm.Controller.RicercaFilm;
import com.example.cm.R;
import com.example.cm.View.Activities.Homepage;

public class RicercaFilmView extends AppCompatActivity {
    Context context = this;
    RecyclerView rvFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_film);

        rvFilm = findViewById(R.id.rvFilm);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setTitle("Ricerca Film");
        toolbar.setTitleTextColor(Color.WHITE);
        final EditText parametri = findViewById(R.id.RicercaFilm);
        ImageButton ricerca = findViewById(R.id.ButtonSearch);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
            }
        });

        ricerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RicercaFilm ricercaFilm = new RicercaFilm(parametri.getText().toString(), context);
                ricercaFilm.onButtonRicercaClicked(RicercaFilmView.this,rvFilm);

            }
        });
        parametri.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            RicercaFilm ricercaFilm = new RicercaFilm(parametri.getText().toString(), context);
                            ricercaFilm.onButtonRicercaClicked(RicercaFilmView.this,rvFilm);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }
}