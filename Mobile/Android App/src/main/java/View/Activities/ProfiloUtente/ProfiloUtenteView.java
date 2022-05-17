package com.example.cm.View.Activities.ProfiloUtente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.cm.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class ProfiloUtenteView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_utente);
        Intent intent = getIntent();
        TabLayout tabLayout = findViewById(R.id.tabLayout2);
        Toolbar tb = findViewById(R.id.toolbar3);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ShapeableImageView ImmagineUtente = findViewById(R.id.ImmagineAmico);
        TextView nomeUtente = findViewById(R.id.textView2);

        nomeUtente.setText(intent.getStringExtra("Nickname"));


        Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+intent.getStringExtra("Immagine")).resize(117,117).into(ImmagineUtente);



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager2);
        TabsProfiloUtenteAdapter tabsAdapter = new TabsProfiloUtenteAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),intent.getIntExtra("idUtente",-1));
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}