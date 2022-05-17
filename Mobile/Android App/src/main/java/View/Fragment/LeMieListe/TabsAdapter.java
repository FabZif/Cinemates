package com.example.cm.View.Fragment.LeMieListe;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                ListaPreferitiFragment listaPreferitiFragment = new ListaPreferitiFragment();
                return listaPreferitiFragment;
            case 1:
                ListaDaVedereFragment listaDaVedereFragment = new ListaDaVedereFragment();
            return listaDaVedereFragment;
            default:
                return null;
        }
    }


}