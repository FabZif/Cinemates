package com.example.cm.View.Fragment.Collegamenti;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.RicercaFilm;
import com.example.cm.Controller.RicercaUtente;
import com.example.cm.R;
import com.example.cm.View.Activities.RicercaFilm.RicercaFilmView;
import com.example.cm.View.Fragment.LeMieListe.LeMieListeFragment;


public class CollegamentiFragment extends Fragment {
    EditText testo;
    CollegamentiFragment context = this;
    RecyclerView rvUtente;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_collegamenti, viewGroup, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        testo = view.findViewById(R.id.RicercaUtente);
        ImageButton ricerca = view.findViewById(R.id.ButtonRicercaUtente);

        rvUtente = view.findViewById(R.id.rvUtente);
        ricerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RicercaUtente ricercaUtente = new RicercaUtente(testo.getText().toString(),context);
                ricercaUtente.onButtonRicercaUtenteClicked(context,rvUtente);

            }
        });

        testo.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            RicercaUtente ricercaUtente = new RicercaUtente(testo.getText().toString(),context);
                            ricercaUtente.onButtonRicercaUtenteClicked(context,rvUtente);
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