package com.example.cm.DAO;


import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.ControllaRichiestaCollegamento;
import com.example.cm.Controller.VisualizzaNotifiche;
import com.example.cm.Models.Notifica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class NotificaAWS implements NotificaDAO {
    public List<Notifica> listaNotifica = new ArrayList<>();
    private Context context;

    public NotificaAWS(Context context) {
        this.context = context;
    }


    public void getNotifica(Integer idUtente, final RecyclerView rvNotifiche) {
        String Base = "***********************************************************";
        String URL = Base + idUtente.toString();

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jArray = response.getJSONArray("body");
                    for (int i = 0; i < jArray.length(); i++) {


                        if (i == 0) {
                            JSONArray jArray2 = jArray.getJSONArray(0);
                            for (int j = 0; j < jArray2.length(); j++) {
                                JSONObject jObject = jArray2.getJSONObject(j);
                                Notifica notifica1 = new Notifica(jObject.getInt("idNotifica"), jObject.getInt("idUtente"), jObject.getString("Nickname"), jObject.getString("Data"), jObject.getInt("Tipo"), jObject.getString("Immagine"), jObject.getString("Testo"));

                                listaNotifica.add(notifica1);
                            }
                        }

                        if (i == 1) {
                            JSONArray jArray3 = jArray.getJSONArray(1);
                            for (int h = 0; h < jArray3.length(); h++) {
                                JSONObject jObject2 = jArray3.getJSONObject(h);

                                Notifica notifica1 = new Notifica(jObject2.getInt("idNotifica"), jObject2.getString("Data"), jObject2.getInt("Tipo"), jObject2.getString("Immagine"), jObject2.getString("Testo"));

                                listaNotifica.add(notifica1);


                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Collections.sort(listaNotifica);
                VisualizzaNotifiche.onSuccess(listaNotifica, rvNotifiche, context);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(objectRequest);

    }


    @Override
    public void deleteNotifica(final Integer idNotifica) {
        String postUrl = "***************************************************";


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.DELETE, postUrl, null, new Response.Listener<JSONObject>() {
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
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("idNotifica", idNotifica.toString());
                return headers;
            }
        };

        requestQueue.add(objectRequest);

    }

    @Override
    public void postNotifica(int tipoNotifica, Integer idProfiloUtenteSelezionato, int idMittente) {
        String postUrl = "***********************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro", "notificheApp");
            postData.put("Tipo", tipoNotifica);
            postData.put("idDestinatario", idProfiloUtenteSelezionato);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idMittente", idMittente);
        } catch (JSONException e) {
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
    public void controllaRichiestaCollegamento(final Integer tipoNotifica, final Integer idProfiloUtenteSelezionato, final Integer idMittente, final ImageButton aggiungi) {
        String Base = "********************************************************";
        String URL = Base + idMittente.toString();


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");


                    if (jArray.isNull(0)) {
                        ControllaRichiestaCollegamento.richiestaCollegamentoNonPresente(idProfiloUtenteSelezionato, idMittente, context);
                    } else {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObject = jArray.getJSONObject(i);
                            Integer tipo = jObject.getInt("Tipo");
                            Integer idD = jObject.getInt("idDestinatario");
                            Integer idM = jObject.getInt("idUtente");
                            if (tipo.equals(tipoNotifica) && idD.equals(idProfiloUtenteSelezionato) && idM.equals(idMittente)) {
                                ControllaRichiestaCollegamento.richiestaCollegamentoPresente(aggiungi, context);

                            } else {
                                ControllaRichiestaCollegamento.richiestaCollegamentoNonPresente(idProfiloUtenteSelezionato, idMittente, context);
                            }

                        }
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
}


