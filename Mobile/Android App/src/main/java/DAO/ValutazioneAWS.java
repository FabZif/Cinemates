package com.example.cm.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.AggiungiValutazione;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class ValutazioneAWS implements ValutazioneDAO {


    Context context;

    public ValutazioneAWS(Context context) {
        this.context = context;
    }


    @Override
    public void postValutazionePreferiti(Integer idProfiloUtenteSelezionato, Integer idMittente, Float Valutazione) {
        String postUrl = "************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro", "idListaPreferiti");
            postData.put("Valutazione", Valutazione);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idValutatore", idMittente);
            postData.put("idListaPreferiti", idProfiloUtenteSelezionato);
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
    public void postValutazioneDaVedere(Integer idProfiloUtenteSelezionato, Integer idMittente, Float Valutazione) {
        String postUrl = "*********************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro", "idListaDaVedere");
            postData.put("Valutazione", Valutazione);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idValutatore", idMittente);
            postData.put("idListaDaVedere", idProfiloUtenteSelezionato);
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
    public void getValutazione(final Integer filtroTipoLista, final Integer idProfiloUtenteSelezionato, final Integer idMittente, final Float valutazione) {
        String URL;
        if (filtroTipoLista == 0) {
            String Base = "*************************************************************";
            String Filtro = idProfiloUtenteSelezionato.toString() + "&idValutatore=" + idMittente;
            URL = Base + Filtro;
        } else {
            String Base = "****************************************************";
            String Filtro = idProfiloUtenteSelezionato.toString() + "&idValutatore=" + idMittente;
            URL = Base + Filtro;
        }


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");
                    if (jArray.isNull(0)) {
                        AggiungiValutazione.aggiungiValutazione(idProfiloUtenteSelezionato, idMittente, valutazione, context, filtroTipoLista);
                    } else {
                        JSONObject jObject = jArray.getJSONObject(0);
                        Integer idValutazione = jObject.getInt("idValutazione");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String strDate = sdf.format(new Date());
                        AggiungiValutazione.updateValutazione(idValutazione, strDate, valutazione, context);
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
    public void updateValutazione(Integer idValutazione, String strDate, Float valutazione, Context context) {
        String URL = "**************************************************************";

        RequestQueue queue = Volley.newRequestQueue(context);

        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("Valutazione", valutazione.toString());
        postParam.put("Data", strDate);
        postParam.put("idValutazione", idValutazione.toString());


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

    @Override
    public void getESetRatingBar(final Integer filtroTipoLista, Integer idProfiloUtenteSelezionato, int idMittente, final RatingBar ratingBar) {
        String URL;
        if (filtroTipoLista == 0) {
            String Base = "******************************************************************";
            String Filtro = idProfiloUtenteSelezionato.toString() + "&idValutatore=" + idMittente;
            URL = Base + Filtro;
        } else {
            String Base = "**************************************************************";
            String Filtro = idProfiloUtenteSelezionato.toString() + "&idValutatore=" + idMittente;
            URL = Base + Filtro;
        }


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");
                    if (jArray.isNull(0)) {

                    } else {
                        JSONObject jObject = jArray.getJSONObject(0);
                        Float Valutazione = Float.valueOf(jObject.getString("Valutazione"));
                        ratingBar.setRating(Valutazione);

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
    public void getMediaValutazioni(Integer tipoLista, Integer idUtente, final TextView media) {
        String Base;
        String Filtro;


        if (tipoLista == 0) {

            Base = "********************************************************************";

            Filtro = idUtente.toString();
        } else {
            Base = "**********************************************************************";
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
                    String mediaValutazione = jObject.getString("media");
                    if(mediaValutazione.equals("null")){
                        media.setText("0.0");
                    }
                    else {
                        media.setText(mediaValutazione);
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


