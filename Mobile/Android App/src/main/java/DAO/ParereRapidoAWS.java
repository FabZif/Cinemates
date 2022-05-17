package com.example.cm.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.RecuperaInterazioni;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class ParereRapidoAWS implements ParereRapidoDAO {

    Boolean miPiaceSelezionato = false;
    Boolean nonMiPiaceSelezionato = false;
    Context context;


    public ParereRapidoAWS(Context context) {
        this.context = context;
    }

    @Override
    public void postParereRapidoInListaPreferiti(Integer tipoParereRapido, Integer idProfiloUtenteSelezionato, Integer idMittente) {
        //tipoParereRapido=0 per il mi piace...1 per il non mi piace!
        String postUrl = "****************************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro", "idListaPreferiti");
            postData.put("Tipo", tipoParereRapido);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idListaPreferiti", idProfiloUtenteSelezionato);
            postData.put("idValutatore", idMittente);
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
    public void postParereRapidoInListaDaVedere(Integer tipoParereRapido, Integer idProfiloUtenteSelezionato, Integer idMittente) {
        String postUrl = "*************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro", "idListaDaVedere");
            postData.put("Tipo", tipoParereRapido);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data", strDate);
            postData.put("idListaDaVedere", idProfiloUtenteSelezionato);
            postData.put("idValutatore", idMittente);
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
    public void controllaParereRapido(final Integer TipoParereRapidoSelezionato, final Integer idProfiloUtenteSelezionato, final int idMittente, final ImageButton miPiace, final ImageButton nonMiPiace) {
        String Base = "*******************************************************";
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
                        Integer Valutatore = jObject.getInt("idValutatore");
                        Integer idListaPreferiti = jObject.getInt("idListaPreferiti");
                        Integer TipoParereRapido = jObject.getInt("Tipo");

                        if (Valutatore.equals(idMittente) && idListaPreferiti.equals(idProfiloUtenteSelezionato) && TipoParereRapido == 0) {

                            miPiaceSelezionato = true;
                            nonMiPiaceSelezionato = false;

                        }
                        if (Valutatore.equals(idMittente) && idListaPreferiti.equals(idProfiloUtenteSelezionato) && TipoParereRapido == 1) {

                            miPiaceSelezionato = false;
                            nonMiPiaceSelezionato = true;

                        }


                    }
                    if (TipoParereRapidoSelezionato == null) {
                        RecuperaInterazioni.onSuccessGetParereRapidoSelezionato(miPiaceSelezionato, nonMiPiaceSelezionato, miPiace, nonMiPiace);
                    } else {
                        RecuperaInterazioni.azioneSulClickParereRapidoPreferiti(TipoParereRapidoSelezionato, miPiace, nonMiPiace, idMittente, idProfiloUtenteSelezionato, miPiaceSelezionato, nonMiPiaceSelezionato, context);
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
    public void deleteParereRapidoInListaPreferiti(final Integer tipoParereRapido, final Integer idMittente, final Integer idProfiloUtenteSelezionato) {
        //tipoParereRapido=0 per il mi piace...1 per il non mi piace!

        String postUrl = "*******************************************";


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
                headers.put("filtro", "idListaPreferiti");
                headers.put("Tipo", String.valueOf(tipoParereRapido));
                headers.put("idValutatore", String.valueOf(idMittente));
                headers.put("idListaPreferiti", String.valueOf(idProfiloUtenteSelezionato));

                return headers;
            }
        };

        requestQueue.add(objectRequest);
    }

    public void deleteParereRapidoInListaDaVedere(final Integer tipoParereRapido, final Integer idMittente, final Integer idProfiloUtenteSelezionato) {
        //tipoParereRapido=0 per il mi piace...1 per il non mi piace!

        String postUrl = "********************************************************";


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
                headers.put("filtro", "idListaDaVedere");
                headers.put("Tipo", String.valueOf(tipoParereRapido));
                headers.put("idValutatore", String.valueOf(idMittente));
                headers.put("idListaDaVedere", String.valueOf(idProfiloUtenteSelezionato));

                return headers;
            }
        };

        requestQueue.add(objectRequest);
    }

    @Override
    public void getEControllaPresenzaParereRapidoDaVedere(final Integer TipoParereRapidoSelezionato, final Integer idProfiloUtenteSelezionato, final int idMittente, final ImageButton miPiace, final ImageButton nonMiPiace) {
        String Base = "********************************************************";
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
                        Integer Valutatore = jObject.getInt("idValutatore");
                        Integer idListaDaVedere = jObject.getInt("idListaDaVedere");
                        Integer TipoParereRapido = jObject.getInt("Tipo");

                        if (Valutatore.equals(idMittente) && idListaDaVedere.equals(idProfiloUtenteSelezionato) && TipoParereRapido == 0) {

                            miPiaceSelezionato = true;
                            nonMiPiaceSelezionato = false;

                        }
                        if (Valutatore.equals(idMittente) && idListaDaVedere.equals(idProfiloUtenteSelezionato) && TipoParereRapido == 1) {

                            miPiaceSelezionato = false;
                            nonMiPiaceSelezionato = true;

                        }


                    }
                    if (TipoParereRapidoSelezionato == null) {
                        RecuperaInterazioni.onSuccessGetParereRapidoSelezionato(miPiaceSelezionato, nonMiPiaceSelezionato, miPiace, nonMiPiace);
                    } else {
                        RecuperaInterazioni.azioneSulClickParereRapidoDaVedere(TipoParereRapidoSelezionato, miPiace, nonMiPiace, idMittente, idProfiloUtenteSelezionato, miPiaceSelezionato, nonMiPiaceSelezionato, context);
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
    public void getNumeroMiPiace(Integer tipoLista, Integer idUtente, final TextView miPiace) {
        String Base;
        String Filtro;
        if (tipoLista == 0) {

            Base = "************************************************************************";

            Filtro = idUtente.toString();
        } else {
            Base = "*********************************************************";
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
                    String numeroMiPiace = jObject.getString("NMIPIACE");
                    miPiace.setText(numeroMiPiace);


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
