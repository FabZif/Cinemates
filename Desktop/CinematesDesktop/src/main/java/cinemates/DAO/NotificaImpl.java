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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Fabrizio
 */
public class NotificaImpl implements NotificaDAO {

    @Override
    public void postNotifica(String TestoNotifica, List<Utente> listaUtenti) {
        for(int i = 0; i < listaUtenti.size() ; i++){
            if(listaUtenti.get(i).getIdUtente()!=30){
            JSONObject postData = new JSONObject();
            
            postData.put("filtro","notificheAdmin");
            postData.put("Tipo", 4);
            postData.put("idDestinatario",listaUtenti.get(i).getIdUtente());
            
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());
            postData.put("Data",strDate );
            
            postData.put("idAdmin",Admin.getIstanzaAdmin().getIdAdmin());
            
            postData.put("Testo",TestoNotifica);
            
            String POST_PARAMS = postData.toString();
            System.out.println(POST_PARAMS);
            
            try {
                
               
                URL obj = new URL("*****************************************************");
                HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
                postConnection.setRequestMethod("POST");
                postConnection.setRequestProperty("Content-Type", "application/json");
                
                
                postConnection.setDoOutput(true);
                OutputStream os = postConnection.getOutputStream();
                os.write(POST_PARAMS.getBytes());
                os.flush();
                os.close();
                
                
                int responseCode = postConnection.getResponseCode();
                System.out.println("POST Response Code :  " + responseCode);
                System.out.println("POST Response Message : " + postConnection.getResponseMessage());
                
                if (responseCode == 200) { //success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            postConnection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    
                    while ((inputLine = in .readLine()) != null) {
                        response.append(inputLine);
                    } in .close();
                    
                    // print result
                    
                    System.out.println(response.toString());
                } else {
                    System.out.println("POST NOT WORKED");
                }       } catch (MalformedURLException ex) {
                Logger.getLogger(NotificaImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NotificaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    }
    
}
