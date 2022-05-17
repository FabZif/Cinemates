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
public class Film {
    
    private String Titolo;
    private String Anno;
    private String Genere;
    
    public Film(String Titolo,String anno,String genere){
        this.Titolo=Titolo;
        Anno = anno;
        Genere = genere;
    }

    /**
     * @return the Titolo
     */
    public String getTitolo() {
        return Titolo;
    }

    /**
     * @return the Anno
     */
    public String getAnno() {
        return Anno;
    }

    /**
     * @return the Genere
     */
    public String getGenere() {
        return Genere;
    }
    
    
    
}
