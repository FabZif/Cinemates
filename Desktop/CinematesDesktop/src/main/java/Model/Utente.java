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
public class Utente {
    
   private Integer idUtente;
   private String Nome;
   private String Cognome;
   private String Nickname;
   private String Email;
   
   public Utente(Integer idUtente,String Nome, String Cognome, String Nickname,String Email){
       this.idUtente=idUtente;
       this.Nome = Nome;
       this.Cognome = Cognome;
       this.Nickname = Nickname;
       this.Email = Email;
   }

    /**
     * @return the idUtente
     */
    public Integer getIdUtente() {
        return idUtente;
    }

    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @return the Cognome
     */
    public String getCognome() {
        return Cognome;
    }

    /**
     * @return the Nickname
     */
    public String getNickname() {
        return Nickname;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }
    
}
