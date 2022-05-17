package com.example.cm.View.Activities.Registrazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.services.cognitoidentityprovider.model.SignUpResult;
import com.example.cm.R;
import com.example.cm.data.CognitoSetting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrazioneView extends AppCompatActivity {
    EditText Nome;
    EditText Cognome;
    EditText Nickname;
    EditText Email;
    EditText Password;
    EditText ConfermaPassword;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        Nome = findViewById(R.id.NomeReg);
        Cognome = findViewById(R.id.CognomeReg);
        Nickname = findViewById(R.id.Nickname);
        Password = findViewById(R.id.PassReg);
        ConfermaPassword = findViewById(R.id.ConfermaPassword);
        Email = findViewById(R.id.EmailReg);
        register = findViewById(R.id.Registrati);



        final CognitoUserAttributes userAttributes = new CognitoUserAttributes();

        final SignUpHandler signupCallBack = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, SignUpResult signUpResult) {
                Intent intent = new Intent(RegistrazioneView.this,VerificaView.class);
                intent.putExtra("Nome",Nome.getText().toString());
                intent.putExtra("Cognome",Cognome.getText().toString());
                intent.putExtra("Password",Password.getText().toString());
                intent.putExtra("Nickname",Nickname.getText().toString());
                intent.putExtra("Email",Email.getText().toString());
                startActivity(intent);
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(getApplicationContext(),"EMAIL GIA' ESISTENTE!",Toast.LENGTH_SHORT).show();
            }
        };
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
                userAttributes.addAttribute("name",Nome.getText().toString());
                userAttributes.addAttribute("family_name",Cognome.getText().toString());
                userAttributes.addAttribute("nickname",Nickname.getText().toString());

                CognitoSetting cognitoSetting = new CognitoSetting(RegistrazioneView.this);

                cognitoSetting.getUserPool().signUpInBackground(Email.getText().toString(),Password.getText().toString(),userAttributes,null,signupCallBack);
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private boolean isPasswordValid(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();


    }

    boolean isValid(EditText text){
        CharSequence str = text.getText().toString();
        return str.length() <= 30;
    }


    void checkDataEntered() {
        if (isEmpty(Nome)) {
            Nome.setError("DEVI INSERIRE IL TUO NOME PER REGISTRARTI!");

        }
        if (isEmpty(Cognome)) {
            Cognome.setError("DEVI INSERIRE IL TUO COGNOME PER REGISTRARTI!");

        }

        if (isEmpty(Nickname)) {
            Nickname.setError("DEVI INSERIRE IL TUO NICKNAME PER REGISTRARTI!");
        }
        if (isEmpty(Email)) {
            Email.setError("DEVI INSERIRE UN EMAIL PER REGISTRARTI!");
        }


        if (isEmail(Email) == false) {
            Email.setError("INSERISCI UNA MAIL VALIDA!");
        }

        if(isPasswordValid(Password.getText().toString())== false){
            Password.setError("LA PASSWORD DEVE CONTENERE ALMENO 8 CARATTERI TRA CUI UNA LETTERA MAIUSCOLA E UN NUMERO");
        }
        if (isValid(Nome)==false) {
            Nome.setError("LUNGHEZZA MASSIMA DI 30 CARATTERI!");
        }
        if (isValid(Cognome)==false) {
            Cognome.setError("LUNGHEZZA MASSIMA DI 30 CARATTERI!");
        }
        if (isValid(Nickname)==false) {
            Nickname.setError("LUNGHEZZA MASSIMA DI 30 CARATTERI!");
        }
        if (isValid(Email)==false) {
            Email.setError("LUNGHEZZA MASSIMA DI 30 CARATTERI!");
        }
        if (isValid(Password)==false) {
            Password.setError("LUNGHEZZA MASSIMA DI 30 CARATTERI!");
        }
        if(!Password.getText().toString().equals(ConfermaPassword.getText().toString())){
            ConfermaPassword.setError("LE PASSWORD NON COINCIDONO");
        }






    }
    }
