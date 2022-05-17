package com.example.cm.DAO;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cm.Controller.VisualizzaAttivitaRecenti;
import com.example.cm.Models.AttivitaRecenti;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class AttivitaRecentiAWS implements AttivitaRecentiDAO {
    public List<AttivitaRecenti> listaAttivita = new ArrayList<>();
    private Context context;

    public AttivitaRecentiAWS(Context context) {
        this.context = context;
    }


    public void getAttivitaRecenti(final Integer parametro, final RecyclerView rvAttivitaRecenti) {
        String Base = "**************************************************************";
        String Filtro = parametro.toString();

        String URL = Base + Filtro;


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
                                if (jObject.getInt("Tipo") != 3) {

                                    AttivitaRecenti attivitaRecenti = new AttivitaRecenti(parametro.toString(), jObject.getInt("Tipo"), jObject.getString("Nickname"), null, jObject.getString("Data"), 0, jObject.getString("Immagine"));

                                    listaAttivita.add(attivitaRecenti);
                                }
                            }
                        }
                        if (i == 1) {
                            JSONArray jArray3 = jArray.getJSONArray(1);
                            for (int h = 0; h < jArray3.length(); h++) {
                                JSONObject jObject2 = jArray3.getJSONObject(h);

                                AttivitaRecenti attivitaRecenti = new AttivitaRecenti(parametro.toString(), null, jObject2.getString("Nickname"), jObject2.getString("Titolo"), jObject2.getString("DataAggiunta"), 1, jObject2.getString("Immagine"));
                                listaAttivita.add(attivitaRecenti);
                            }

                        }
                        if (i == 2) {

                            JSONArray jArray4 = jArray.getJSONArray(2);
                            for (int k = 0; k < jArray4.length(); k++) {
                                JSONObject jObject3 = jArray4.getJSONObject(k);

                                AttivitaRecenti attivitaRecenti = new AttivitaRecenti(parametro.toString(), null, jObject3.getString("Nickname"), jObject3.getString("Titolo"), jObject3.getString("DataAggiunta"), 2, jObject3.getString("Immagine"));

                                listaAttivita.add(attivitaRecenti);
                            }
                        }


                    }
                    Collections.sort(listaAttivita);
                    Collections.reverse(listaAttivita);

                    VisualizzaAttivitaRecenti.onSuccess(listaAttivita, rvAttivitaRecenti, context);
                } catch (JSONException | ParseException e) {
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
