package com.example.cm.DAO;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.RicercaUtente;
import com.example.cm.Models.Utente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class UtenteAWS implements UtenteDAO {

    public List<Utente> listaUtenti = new ArrayList<>();
    private Context context;

    public UtenteAWS(Context context) {
        this.context = context;
    }


    public void getUtente(final String testo, final RecyclerView rvUtente) {
        String Base = "**********************************************";
        String Filtro = testo.toString();

        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        if (!jObject.getString("Nickname").equals(Utente.getIstanzaUtente().getNickname())) {
                            Utente utente = new Utente(jObject.getInt("idUtente"), jObject.getString("Nome"), jObject.getString("Cognome"), jObject.getString("Nickname"), jObject.getString("Immagine"));

                            listaUtenti.add(utente);

                        }
                    }
                    if (!listaUtenti.isEmpty()) {
                        RicercaUtente.onSuccess(listaUtenti, rvUtente, context);
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("ERRORE");
                        alertDialog.setMessage("NESSUN UTENTE TROVATO!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(objectRequest);

    }

    @Override
    public void postUtente(String email, String password, String nickname, String nome, String cognome) {
        String postUrl = "****************************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro", "notificheApp");
            postData.put("Nome", nome);
            postData.put("Cognome", cognome);
            postData.put("Nickname", nickname);
            postData.put("Email", email);
            postData.put("Password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void getUtente2(String idUtente) {
        String Base = "*******************************************************";
        String Filtro = idUtente.toString();

        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        Utente.InizializzaUtente(jObject.getInt("idUtente"), jObject.getString("Nome"), jObject.getString("Cognome"), jObject.getString("Nickname"), jObject.getString("Immagine"), jObject.getString("Email"));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);
    }

    @Override
    public void updateUtente(String nome, Integer idUtente) {
        String URL = "****************************************************";

        RequestQueue queue = Volley.newRequestQueue(context);

        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("Immagine", nome);
        postParam.put("idUtente", idUtente.toString());


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, URL, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };


        queue.add(jsonObjReq);
    }


}

