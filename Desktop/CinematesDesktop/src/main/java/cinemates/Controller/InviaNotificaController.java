/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.Controller;

import Model.Utente;
import cinemates.DAO.DAOFactory;
import cinemates.DAO.NotificaDAO;
import java.util.List;
import javax.swing.JOptionPane;





/**
 *
 * @author Fabrizio
 */
public class InviaNotificaController {


    public void onButtonRaccomandaClicked(String TestoNotifica, List<Utente> listaUtenti) {
        NotificaDAO notificaDAO = DAOFactory.getNotificaDAO();
        notificaDAO.postNotifica(TestoNotifica,listaUtenti);
        JOptionPane.showMessageDialog(null, "Raccomandato correttamente!");
    }
    
}
