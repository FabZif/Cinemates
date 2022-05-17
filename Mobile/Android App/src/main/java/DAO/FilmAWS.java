package com.example.cm.DAO;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.Controller.VisualizzaListaFilmDaVederePersonale;
import com.example.cm.Controller.VisualizzaFilmInComuneConUnAmico;
import com.example.cm.Controller.VisualizzaListaFilmPreferitiPersonale;
import com.example.cm.Controller.VisualizzaListeDiFilmDiUnAmico;
import com.example.cm.Models.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class FilmAWS implements FilmDAO {

    public List<Film> listaFilm = new ArrayList<>();
    private Context context;

    public FilmAWS(Context context) {
        this.context = context;
    }



    public void getFilmDaVedere(final Integer parametro, final RecyclerView rvFilm, final int TipoVisualizzazione) {
        String Base = "***************************************************";
        String Filtro = parametro.toString();

        String URL = Base + Filtro;


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");
                    if (jArray.isNull(0)) {
                        if(TipoVisualizzazione==1) {
                            Toast toast = Toast.makeText(context, "NON HAI FILM NELLA TUA LISTA DI FILM DA VEDERE!", Toast.LENGTH_SHORT);
                            toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            toast.show();
                        }
                    } else {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObject = jArray.getJSONObject(i);
                            Film film = new Film(jObject.getInt("idFilm"), jObject.getString("Titolo"), jObject.getString("Immagine"), jObject.getString("Descrizione"), 1);

                            listaFilm.add(film);


                        }
                        if (TipoVisualizzazione == 0) {
                            VisualizzaListeDiFilmDiUnAmico.onSuccessDaVedere(listaFilm, rvFilm, context);
                        } else
                            VisualizzaListaFilmDaVederePersonale.onSuccessDaVedere(listaFilm, rvFilm, context, parametro);
                    }
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
    public void getFilmInComuneDaVedere(final Integer idUtente, Integer idAmico, final RecyclerView rvFilm) {

        String Base = "***************************************************************";
        String Filtro1 = idUtente.toString()+"&idAmico=";
        String Filtro2 = idAmico.toString();

        String URL = Base + Filtro1+Filtro2;


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        Film film = new Film(jObject.getInt("idFilm"), jObject.getString("Titolo"), jObject.getString("Immagine"), jObject.getString("Descrizione"),1);

                        listaFilm.add(film);


                    }
                    VisualizzaFilmInComuneConUnAmico.OnSuccessDaVedere(listaFilm,rvFilm,context);
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
    public void postFilmDaVedere(Film film, Integer idUtente) {
        String postUrl = "*******************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro","idListaDaVedere");
            postData.put("idListaDaVedere", idUtente);
            postData.put("idFilm", film.getIdFilm());
            postData.put("Titolo",film.getTitolo());
            postData.put("Descrizione",film.getTrama());
            postData.put("Immagine",film.getImmagine());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("DataAggiunta", strDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Integer statusCode = response.getInt("statusCode");
                    if(statusCode==200) {
                        String Body = response.getString("body");
                        Toast toast =  Toast.makeText(context, Body+" NELLA TUA LISTA DI FILM DA VEDERE", Toast.LENGTH_SHORT);
                        toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(context,"HAI GIA' QUESTO FILM NELLA TUA LISTA DI FILM DA VEDERE",Toast.LENGTH_SHORT);
                        toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        toast.show();

                    }
                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(),Toast.LENGTH_LONG).show();
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
    public void deleteFilmDaVedere(final Integer idFilm, final Integer idUtente) {
        String postUrl = "****************************************************";

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

            @Override public Map<String,String> getHeaders()throws AuthFailureError {
                Map<String,String>headers=new HashMap<>();
                headers.put("Content-Type","application/json");
                headers.put("filtro","idListaDaVedere");
                headers.put("idFilm",idFilm.toString());
                headers.put("idListaDaVedere",idUtente.toString());
                return headers;
            }
        };

        requestQueue.add(objectRequest);

    }

    public void getFilmPreferiti(final Integer parametro, final RecyclerView rvFilm, final int TipoVisualizzazione) {
        String Base = "**********************************************************";
        String Filtro = parametro.toString();

        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");
                    if (jArray.isNull(0)) {
                        if(TipoVisualizzazione==1) {
                            Toast toast = Toast.makeText(context, "NON HAI FILM NELLA TUA LISTA DI FILM PREFERITI!", Toast.LENGTH_SHORT);
                            toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            toast.show();
                        }
                    } else {

                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObject = jArray.getJSONObject(i);
                            Film film = new Film(jObject.getInt("idFilm"), jObject.getString("Titolo"), jObject.getString("Immagine"), jObject.getString("Descrizione"), 0);

                            listaFilm.add(film);


                        }
                        if (TipoVisualizzazione == 0) {
                            VisualizzaListeDiFilmDiUnAmico.onSuccessPreferiti(listaFilm, rvFilm, context);
                        } else {
                            VisualizzaListaFilmPreferitiPersonale.onSuccessPreferiti(listaFilm, rvFilm, context, parametro);
                        }
                    }
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
    public void getFilmInComunePreferiti(final Integer idUtente, Integer idAmico, final RecyclerView rvFilm) {
        String Base = "************************************************************";
        String Filtro1 = idUtente.toString()+"&idAmico=";
        String Filtro2 = idAmico.toString();

        String URL = Base + Filtro1+Filtro2;


        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("body");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        Film film = new Film(jObject.getInt("idFilm"), jObject.getString("Titolo"), jObject.getString("Immagine"), jObject.getString("Descrizione"),0);

                        listaFilm.add(film);


                    }
                    VisualizzaFilmInComuneConUnAmico.OnSuccessPreferiti(listaFilm,rvFilm,context);
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
    public void postFilmPreferiti(Film film, Integer idUtente) {

        String postUrl = "*****************************************************";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("filtro","idListaPreferiti");
            postData.put("idListaPreferiti", idUtente);
            postData.put("idFilm", film.getIdFilm());
            postData.put("Titolo",film.getTitolo());
            postData.put("Descrizione",film.getTrama());
            postData.put("Immagine",film.getImmagine());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("DataAggiunta", strDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Integer statusCode = response.getInt("statusCode");
                    if(statusCode==200) {
                        String Body = response.getString("body");
                        Toast toast =Toast.makeText(context, Body+" NELLA TUA LISTA DI FILM PREFERITI", Toast.LENGTH_SHORT);
                        toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        toast.show();
                    }
                    else{
                        Toast toast =  Toast.makeText(context,"HAI GIA' QUESTO FILM NELLA TUA LISTA DI FILM PREFERITI!",Toast.LENGTH_SHORT);
                        toast.getView().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        toast.show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "ERRORE!",Toast.LENGTH_LONG).show();
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
    public void deleteFilmPreferiti(final Integer idFilm, final Integer idUtente) {
        String postUrl = "*******************************************************";

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

            @Override public Map<String,String> getHeaders()throws AuthFailureError{
                Map<String,String>headers=new HashMap<>();
                headers.put("Content-Type","application/json");
                headers.put("filtro","idListaPreferiti");
                headers.put("idFilm",idFilm.toString());
                headers.put("idListaPreferiti",idUtente.toString());
                return headers;
            }
        };

        requestQueue.add(objectRequest);


    }


}

