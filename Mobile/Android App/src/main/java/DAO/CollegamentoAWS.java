package com.example.cm.DAO;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.ControllaEsistenzaCollegamento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class CollegamentoAWS implements CollegamentoDAO {
    Context context;

    public CollegamentoAWS(Context context) {
        this.context = context;
    }

    @Override
    public void postCollegamento(Integer idUtente, Integer idAmico) {
        String postUrl = "***********************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("idUtente", idUtente);
            postData.put("idAmico", idAmico);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Integer statusCode = response.getInt("statusCode");
                    if (statusCode == 200) {
                        Toast toast = Toast.makeText(context, "UTENTE AGGIUNTO AI TUOI COLLEGAMENTI", Toast.LENGTH_SHORT);
                        toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(context, "ERRORE", Toast.LENGTH_LONG);
                        toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        toast.show();

                    }
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(context, "ERRORE", Toast.LENGTH_LONG);
                    toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    toast.show();
                    e.printStackTrace();
                }
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
    public void getCollegamento(final Integer idAmico, final Integer idUtente, final String nickname, final String immagine) {

        String Base = "**********************************************";
        String Filtro = idAmico.toString();
        String Filtro2 = "&idUtente=" + idUtente;

        String URL = Base + Filtro + Filtro2;


        final RequestQueue requestQueue = newRequestQueue(context);
        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");
                    if (jArray.isNull(0)) {

                        ControllaEsistenzaCollegamento.nonCollegato(idAmico, context, nickname, immagine);
                    } else {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObject = jArray.getJSONObject(i);
                            ControllaEsistenzaCollegamento.collegato(idAmico, immagine, nickname, context);
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

