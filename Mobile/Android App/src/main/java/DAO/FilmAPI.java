package com.example.cm.DAO;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cm.Controller.RicercaFilm;
import com.example.cm.Models.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class FilmAPI implements FilmAPIDAO {
    public List<Film> listaFilm = new ArrayList<>();
    private Context context;

    public FilmAPI(Context context) {
        this.context = context;
    }


    public void getFilm(final String parametro, final RecyclerView rvFilm) {
        String Base = "https://api.themoviedb.org/3/search/movie?api_key=fa204481956a9c33c385f12bf1481037&language=it-IT&query=";
        String Filtro = parametro;
        String URL = Base + Filtro;

        RequestQueue requestQueue = newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response.getJSONArray("results");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);

                        Film film = new Film(jObject.getInt("id"), jObject.getString("original_title"), jObject.getString("poster_path"), jObject.getString("overview"));
                        listaFilm.add(film);

                    }
                    if (!listaFilm.isEmpty()) {
                        RicercaFilm.onSuccess(listaFilm, rvFilm, context);
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("ERRORE");
                        alertDialog.setMessage("NESSUN FILM TROVATO!");
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
}

