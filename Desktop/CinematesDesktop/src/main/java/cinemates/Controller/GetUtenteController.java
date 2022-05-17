/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.Controller;

import Model.Utente;
import cinemates.DAO.DAOFactory;
import cinemates.DAO.UtenteDAO;
import java.util.List;



/**
 *
 * @author Fabrizio
 */
public class GetUtenteController {
    
    public GetUtenteController(){}
    
    public List<Utente> RitornaUtenti(){
        UtenteDAO utenteDAO = DAOFactory.getUtenteDao();
        List<Utente> listaUtenti = utenteDAO.getUtente();
        
        return listaUtenti;
    }
    
}
