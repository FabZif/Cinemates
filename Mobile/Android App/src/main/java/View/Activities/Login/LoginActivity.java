package com.example.cm.View.Activities.Login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.example.cm.DAO.DAOFactory;
import com.example.cm.DAO.UtenteDAO;
import com.example.cm.R;
import com.example.cm.View.Activities.Homepage;
import com.example.cm.View.Activities.Registrazione.RegistrazioneView;
import com.example.cm.data.CognitoSetting;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences preferences = getSharedPreferences("CINEMATES", Context.MODE_PRIVATE);
        String accessToken = preferences.getString("Token","0");
        if(accessToken.equals("0")) {
           setContentView(R.layout.activity_login);

            loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                    .get(LoginViewModel.class);

            final EditText usernameEditText = findViewById(R.id.username);
            final EditText passwordEditText = findViewById(R.id.password);
            final TextView registratiTextView = findViewById(R.id.btn_registrati);
            final Button loginButton = findViewById(R.id.login);






            final GetDetailsHandler getDetailsHandler = new GetDetailsHandler() {
                @Override
                public void onSuccess(CognitoUserDetails cognitoUserDetails) {


                    UtenteDAO utenteDAO = DAOFactory.getUtenteDAO(getApplicationContext());
                    utenteDAO.getUtente2(cognitoUserDetails.getAttributes().getAttributes().get("email"));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(LoginActivity.this, Homepage.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Exception exception) {
                }
            };


            final AuthenticationHandler authenticationHandler = new AuthenticationHandler() {
                @Override
                public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                    setContentView(R.layout.autologinloading);
                    if (userSession.isValid()) {
                        SharedPreferences.Editor pref = getApplicationContext().getSharedPreferences("CINEMATES", Context.MODE_PRIVATE).edit();
                        pref.putString("Token", userSession.getAccessToken().toString());
                        pref.commit();

                        CognitoSetting cognitoSetting = new CognitoSetting(LoginActivity.this);
                        cognitoSetting.getUserPool().getCurrentUser().getDetailsInBackground(getDetailsHandler);
                    }


                }

                @Override
                public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {

                    AuthenticationDetails authenticationDetails = new AuthenticationDetails(userId, passwordEditText.getText().toString(), null);
                    authenticationContinuation.setAuthenticationDetails(authenticationDetails);


                    authenticationContinuation.continueTask();
                }

                @Override
                public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

                }

                @Override
                public void authenticationChallenge(ChallengeContinuation continuation) {

                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(getApplicationContext(), "DATI ERRATI...RIPROVA!", Toast.LENGTH_LONG).show();
                    startActivity(Intent.makeRestartActivityTask(getIntent().getComponent()));
                }
            };



            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                       if(!(usernameEditText.getText().toString().isEmpty() && passwordEditText.getText().toString().isEmpty())) {

                           CognitoSetting cognitoSetting = new CognitoSetting(LoginActivity.this);

                           CognitoUser thisUser = cognitoSetting.getUserPool().getUser(usernameEditText.getText().toString());

                           thisUser.getSessionInBackground(authenticationHandler);
                       }



                }
            });


            loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
                @Override
                public void onChanged(@Nullable LoginFormState loginFormState) {
                    if (loginFormState == null) {
                        return;
                    }

                    loginButton.setEnabled(loginFormState.isDataValid());
                    if (loginFormState.getUsernameError() != null) {
                        usernameEditText.setError(getString(loginFormState.getUsernameError()));

                    }
                    if (loginFormState.getPasswordError() != null) {
                        passwordEditText.setError(getString(loginFormState.getPasswordError()));
                    }
                }
            });

            loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
                @Override
                public void onChanged(@Nullable LoginResult loginResult) {
                    if (loginResult == null) {
                        return;
                    }
                    if (loginResult.getError() != null) {
                        showLoginFailed(loginResult.getError());

                    }
                    if (loginResult.getSuccess() != null) {
                        updateUiWithUser(loginResult.getSuccess());
                    }
                    setResult(Activity.RESULT_OK);

                    //Complete and destroy login activity once successful
                    finish();
                }
            });

            TextWatcher afterTextChangedListener = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // ignore
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // ignore
                }

                @Override
                public void afterTextChanged(Editable s) {
                    loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
            };
            usernameEditText.addTextChangedListener(afterTextChangedListener);
            passwordEditText.addTextChangedListener(afterTextChangedListener);
            passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        loginViewModel.login(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                    }
                    return false;
                }
            });


            registratiTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Next = new Intent(LoginActivity.this, RegistrazioneView.class);
                    startActivity(Next);
                }
            });
        }
        else{
            //TOKEN PRESENTE

            setContentView(R.layout.autologinloading);
            final GetDetailsHandler getDetailsHandler = new GetDetailsHandler() {
                @Override
                public void onSuccess(CognitoUserDetails cognitoUserDetails) {


                    UtenteDAO utenteDAO = DAOFactory.getUtenteDAO(getApplicationContext());
                    utenteDAO.getUtente2(cognitoUserDetails.getAttributes().getAttributes().get("email"));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(LoginActivity.this, Homepage.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Exception exception) {
                    // Fetch user details failed, check exception for the cause
                }
            };


            final AuthenticationHandler authenticationHandler = new AuthenticationHandler() {
                @Override
                public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {

                    if (userSession.isValid()) {
                        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
                        editor.putString("Token", userSession.getAccessToken().toString());
                        editor.commit();

                        CognitoSetting cognitoSetting = new CognitoSetting(LoginActivity.this);
                        cognitoSetting.getUserPool().getCurrentUser().getDetailsInBackground(getDetailsHandler);
                    }


                }

                @Override
                public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {

                }

                @Override
                public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

                }

                @Override
                public void authenticationChallenge(ChallengeContinuation continuation) {

                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(getApplicationContext(), "JAJAJ", Toast.LENGTH_SHORT).show();
                }
            };

            CognitoSetting cognitoSetting = new CognitoSetting(LoginActivity.this);
            final CognitoUser user = cognitoSetting.getUserPool().getCurrentUser();
            user.getSessionInBackground(authenticationHandler);
        }
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }


}