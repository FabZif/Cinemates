/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.Controller;

import cinemates.DAO.DAOFactory;
import cinemates.DAO.AdminDAO;

/**
 *
 * @author Fabrizio
 */
public class ControllaLoginController {
    
    
    
    public Boolean onButtonLoginClicked(String Username,String Password){
        AdminDAO adminDAO = DAOFactory.getAdminDAO();
        Boolean presente = adminDAO.getAdminCredentials(Username,Password);
        return presente;
    }
    
}
