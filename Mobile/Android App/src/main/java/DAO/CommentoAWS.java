package com.example.cm.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.VisualizzaCommento;
import com.example.cm.Models.Commento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class CommentoAWS implements CommentoDAO {
    Context context;
    List<Commento> listaCommenti = new ArrayList<>();

    public CommentoAWS(Context context){
        this.context=context;
    }
    @Override
    public void getCommentoListaPreferiti(Integer idProfiloUtenteSelezionato, final RecyclerView rvCommenti) {
        String Base = "******************************************************";
        String Filtro = idProfiloUtenteSelezionato.toString();

        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        Commento commento = new Commento(jObject.getString("TestoCommento"), jObject.getString("Data"), jObject.getString("Nickname"));

                        listaCommenti.add(commento);


                    }
                    VisualizzaCommento.OnSuccessGetCommento(listaCommenti,rvCommenti,context);
                }
                catch (JSONException e) {
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
    public void getCommentoListaDaVedere(Integer idProfiloUtenteSelezionato, final RecyclerView rvCommenti) {
        String Base = "*****************************************************";
        String Filtro = idProfiloUtenteSelezionato.toString();

        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        Commento commento = new Commento(jObject.getString("TestoCommento"), jObject.getString("Data"), jObject.getString("Nickname"));

                        listaCommenti.add(commento);


                    }
                    VisualizzaCommento.OnSuccessGetCommento(listaCommenti,rvCommenti,context);
                }
                catch (JSONException e) {
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
    public void postCommentoListaPreferiti(Integer idProfiloUtenteSelezionato, Integer idMittente, String testoCommento) {
        String postUrl = "*****************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro","idListaPreferiti");
            postData.put("TestoCommento",testoCommento);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idListaPreferiti", idProfiloUtenteSelezionato);
            postData.put("idUtente", idMittente);
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
    public void postCommentoListaDaVedere(Integer idProfiloUtenteSelezionato, Integer idMittente, String testoCommento) {
        String postUrl = "*****************************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro","idListaDaVedere");
            postData.put("TestoCommento",testoCommento);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idListaDaVedere", idProfiloUtenteSelezionato);
            postData.put("idUtente", idMittente);
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
    public void getNumeroCommenti(Integer tipoLista,Integer idUtente, final TextView commenti) {
        String Base;
        String Filtro;
        if(tipoLista==0){
          Base = "***************************************************************";
          Filtro = idUtente.toString();
        }
        else{
            Base = "**********************************************************";
            Filtro = idUtente.toString();
        }


        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jArray = response.getJSONArray("body");

                    JSONObject jObject = jArray.getJSONObject(0);
                    String numeroCommenti = jObject.getString("nCommenti");

                    commenti.setText(numeroCommenti);



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

