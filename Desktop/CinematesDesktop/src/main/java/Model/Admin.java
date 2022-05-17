/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Fabrizio
 */
public class Admin {
    private static Admin admin;
    private Integer idAdmin;
    private String Username;
    private String Password;
    
     public static void InizializzaAdmin(Integer idAdmin,String Username,String Password) {
            admin=new Admin(idAdmin,Username,Password);
           

    }

    public Admin(Integer idAdmin, String Username, String Password) {
        this.idAdmin = idAdmin;
        this.Username = Username;
        this.Password = Password;
    }
    
    public static Admin getIstanzaAdmin() {
        return admin;
    }

    /**
     * @return the idAdmin
     */
    public Integer getIdAdmin() {
        return idAdmin;
    }
    
    
}
