/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.DAO;

import Model.Film;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.*;

/**
 *
 * @author Fabrizio
 */
public class FilmImpl implements FilmDAO {

    @Override
    public List<String> getFilm(String titoloFilm) {
        
        List<String> listaTitoli = new ArrayList<>();
        String URL = generateURLCorretto(titoloFilm);
        try {
            URL urlForGetRequest = new URL(URL);
            System.out.println(urlForGetRequest);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                // print result
                
                
                String jsonString = response.toString() ; //assign your JSON String here
                System.out.println(jsonString);
                listaTitoli=createList(jsonString);
                
            } 
            
            else {
                 System.out.println("NOT WORKED!");
            }   } catch (MalformedURLException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaTitoli;
    }

    @Override
    public List<Film> getFilmPopolari(Integer idGenere, String anno,String genere) {
        
        
        List<Film> listaFilm = new ArrayList<>();
        
        try {
            URL urlForGetRequest = new URL("https://api.themoviedb.org/3/discover/movie?api_key=fa204481956a9c33c385f12bf1481037&language=it-IT&sort_by=popularity.desc&with_genres="+idGenere+"&year="+anno);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                // print result
                
                
                String jsonString = response.toString() ; //assign your JSON String here
                JSONObject obj = new JSONObject(jsonString);

               
                JSONArray arr = obj.getJSONArray("results"); // notice that `"posts": [...]`
                for (int i = 0; i < arr.length(); i++)
                   {
                      JSONObject jObject = arr.getJSONObject(i);
                      
                      Film film  = new Film( jObject.getString("original_title"),anno,genere);
                      
                      listaFilm.add(film);
                      
                      
                      
                    }
                
            } 
            
            else {
                System.out.println("NOT WORKED!");
            }   } catch (MalformedURLException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return listaFilm;
    }
    
    
    
    public List<String> createList(String jsonString){
          List<String> listaTitoli = new ArrayList<>();
          JSONObject obj = new JSONObject(jsonString);
          JSONArray arr = obj.getJSONArray("results"); // notice that `"posts": [...]`
          for (int i = 0; i < arr.length(); i++)    {
                 JSONObject jObject = arr.getJSONObject(i);
                 String titolo = jObject.getString("original_title");
                 listaTitoli.add(titolo);
                      }
          return listaTitoli;
    }  

    public String generateURLCorretto(String titoloFilm) throws IllegalArgumentException {
        String divider = "%20";
        String apiURL = "https://api.themoviedb.org/3/search/movie?api_key=fa204481956a9c33c385f12bf1481037&language=it-IT&query=";
        String URL;
        
        if(titoloFilm.isEmpty()) throw new IllegalArgumentException(); 
        
        if(titoloFilm.contains(" ")){
            titoloFilm = titoloFilm.replace(" ",divider);
             URL = apiURL+titoloFilm;
        }
        else{
             URL = apiURL+titoloFilm;
        }
        return URL;
    
   
}
}
