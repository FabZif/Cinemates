package com.example.cm.View.Activities.ProfiloUtente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm.Controller.VisualizzaAttivitaRecenti;
import com.example.cm.R;

public class AttivitaRecentiFragment extends Fragment {
    Integer idUtente;
    RecyclerView rvAttivitaRecenti;
    public AttivitaRecentiFragment(Integer idUtente) {
        this.idUtente=idUtente;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_attivitarecenti, viewGroup, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvAttivitaRecenti=view.findViewById(R.id.rvAttivitaRecenti);
        VisualizzaAttivitaRecenti visualizzaAttivitaRecenti = new VisualizzaAttivitaRecenti(idUtente,this);
        visualizzaAttivitaRecenti.onTabAttivitaRecentiClicked(this,rvAttivitaRecenti);
    }
}
