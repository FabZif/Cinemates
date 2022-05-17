/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.DAO;

import Model.Utente;
import java.util.List;

/**
 *
 * @author Fabrizio
 */
public interface InvioEmailDAO {
    
    public void inviaEmail(String testoEmail,List<Utente> listaUtenti);
    
}
