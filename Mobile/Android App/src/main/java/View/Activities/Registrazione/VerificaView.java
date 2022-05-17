package com.example.cm.View.Activities.Registrazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.example.cm.Controller.EffettuaRegistrazione;
import com.example.cm.R;
import com.example.cm.View.Activities.Login.LoginActivity;
import com.example.cm.data.CognitoSetting;

public class VerificaView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifica_email);

        final EditText codice = findViewById(R.id.CodiceVerifica);
        Button verifica = findViewById(R.id.buttonVerifica);
        Intent intent = getIntent();
        final String Email = intent.getStringExtra("Email");
        final String Password = intent.getStringExtra("Password");
        final String Nickname = intent.getStringExtra("Nickname");
        final String Nome = intent.getStringExtra("Nome");
        final String Cognome = intent.getStringExtra("Cognome");

        verifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmTask().execute(codice.getText().toString(),Email,Password,Nickname,Nome,Cognome);
            }
        });
    }

    private class ConfirmTask extends AsyncTask<String, Void ,String>{


        @Override
        protected String doInBackground(final String... strings) {
            final String[] result = new String[1];

            final GenericHandler confirmationCallBack = new GenericHandler() {
                @Override
                public void onSuccess() {
                    result[0]="Succeded";
                    EffettuaRegistrazione effettuaRegistrazione = new EffettuaRegistrazione(getApplicationContext());
                    effettuaRegistrazione.onButtonRegistratiClicked(strings[1],strings[2],strings[3],strings[4],strings[5]);
                    Intent intent = new Intent(VerificaView.this, LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Exception exception) {
                    result[0]="Failed";
                }
            };

            CognitoSetting cognitoSetting = new CognitoSetting(VerificaView.this);

            CognitoUser thisUser = cognitoSetting.getUserPool().getUser(strings[1]);


            thisUser.confirmSignUp(strings[0],false,confirmationCallBack);

            return result[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}