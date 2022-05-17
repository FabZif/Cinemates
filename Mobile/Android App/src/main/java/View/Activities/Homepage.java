package com.example.cm.View.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.ParereRapidoDAO;
import com.example.cm.Models.Utente;
import com.example.cm.R;
import com.example.cm.View.Activities.Login.LoginActivity;
import com.example.cm.View.Activities.Notifica.NotificaView;
import com.example.cm.View.Activities.RicercaFilm.RicercaFilmView;
import com.example.cm.data.CognitoSetting;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Homepage extends AppCompatActivity {

    public static Context getNavigationActivity;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_mylist, R.id.nav_Collegamenti)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.Nickname1);
        navUsername.setText(Utente.getIstanzaUtente().getNickname());

        TextView navName = headerView.findViewById(R.id.Email1);
        navName.setText(Utente.getIstanzaUtente().getNome()+" "+Utente.getIstanzaUtente().getCognome());

        ImageView navImage = headerView.findViewById(R.id.FotoHeader);
        Picasso.get().load("https://mypiccinemates.s3.amazonaws.com//"+Utente.getIstanzaUtente().getImmagine()).resize(150,150).into(navImage);

        MenuItem item =  navigationView.getMenu().getItem(3);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                SharedPreferences.Editor pref = getApplicationContext().getSharedPreferences("CINEMATES", Context.MODE_PRIVATE).edit();
                pref.clear();
                pref.commit();

                CognitoSetting cognitoSetting = new CognitoSetting(getApplicationContext());
                CognitoUser thisUser = cognitoSetting.getUserPool().getCurrentUser();
                thisUser.signOut();

                Intent intent = new Intent(Homepage.this, LoginActivity.class);
                startActivity(intent);
                return false;
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_Notify:
                Intent IN = new Intent(Homepage.this, NotificaView.class);
                startActivity(IN);
                return true;
            case R.id.action_Search:
                Intent IS = new Intent(Homepage.this, RicercaFilmView.class);
                startActivity(IS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}