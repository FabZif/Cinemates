/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.DAO;

/**
 *
 * @author Fabrizio
 */
public abstract class DAOFactory {
    


    public static FilmDAO getFilmDAO() {
      return new FilmImpl();      
    }
    

    public static NotificaDAO getNotificaDAO() {
       return new NotificaImpl(); 
    }
    

    public static UtenteDAO getUtenteDao() {
        return new UtenteImpl();
    }
    
     public static InvioEmailDAO getInvioEmailDAO() {
        return  new InvioEmailImpl2();
    }
    
     public static AdminDAO getAdminDAO() {
        return new AdminImpl();
    }
}
