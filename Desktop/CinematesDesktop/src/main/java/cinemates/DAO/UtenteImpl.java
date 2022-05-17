/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.DAO;

import Model.Utente;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Fabrizio
 */
public class UtenteImpl implements UtenteDAO {
    

    @Override
    public List<Utente> getUtente() {
        List<Utente> listaUtenti = new ArrayList<>();
        try {
            
            
            URL urlForGetRequest = new URL("*************************************");
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
                
                System.out.println(obj.toString());
                JSONArray arr = obj.getJSONArray("body"); // notice that `"posts": [...]`
                for (int i = 0; i < arr.length(); i++)
                {
                    JSONObject jObject = arr.getJSONObject(i);
                    
                    Utente utente = new Utente(jObject.getInt("idUtente"), jObject.getString("Nome"), jObject.getString("Cognome"),jObject.getString("Nickname"),jObject.getString("Email"));
                    
                    listaUtenti.add(utente);
                    
                    
                    
                }
                
            } 
            
            else {
                System.out.println("GET NOT WORKED");
            }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(UtenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUtenti;
    }

    
    }

