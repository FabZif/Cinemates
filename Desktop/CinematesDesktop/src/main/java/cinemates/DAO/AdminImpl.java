/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.DAO;

import Model.Admin;
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
public class AdminImpl implements AdminDAO {

    
    
    @Override
    public Boolean getAdminCredentials(String Username, String Password) {
      
        Boolean presente = false;
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
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                // print result
                
                 String jsonString = response.toString();
                 System.out.println(jsonString);
                
                 presente = checkEsistenzaAdmin(jsonString,Username,Password);
                //assign your JSON String here
              

            } else {
                System.out.println("GET NOT WORKED");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(UtenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(presente);
        return presente;
    }
    
    
    public Boolean checkEsistenzaAdmin(String jsonString,String Username,String Password){
          Boolean presente=false;
          JSONObject obj = new JSONObject(jsonString);
          JSONArray arr = obj.getJSONArray("body"); // notice that `"posts": [...]`
          for (int i = 0; i < arr.length(); i++) {
               JSONObject jObject = arr.getJSONObject(i);
               if (jObject.getString("Username").equals(Username) && jObject.getString("Password").equals(Password)) {
                        presente = true;
                        Admin.InizializzaAdmin(jObject.getInt("idAdmin"), jObject.getString("Username"), jObject.getString("Password"));
                }
                }
                return presente;
    }
}
