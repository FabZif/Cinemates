package com.example.cm.View.Activities.ProfiloUtente;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsProfiloUtenteAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Integer idUtente;
    public TabsProfiloUtenteAdapter(FragmentManager fm, int NoofTabs, int idUtente){
        super(fm);
        this.mNumOfTabs = NoofTabs;
        this.idUtente= idUtente;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                AttivitaRecentiFragment attivitaRecentiFragment = new AttivitaRecentiFragment(idUtente);
                return attivitaRecentiFragment;
            case 1:
                ListeDiFilmFragment listeDiFilmFragment = new ListeDiFilmFragment(idUtente);
                return listeDiFilmFragment;

            case 2:
                FilmInComuneFragment filmInComuneFragment = new FilmInComuneFragment(idUtente);
                return filmInComuneFragment;
            default:
                return null;
        }
    }


}