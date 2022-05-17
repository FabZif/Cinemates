/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.Controller;

import cinemates.DAO.DAOFactory;
import cinemates.DAO.FilmDAO;
import java.util.List;

/**
 *
 * @author Fabrizio
 */
public class RicercaFilmController {
    
    private String titoloFilm;

    public RicercaFilmController(String titoloFilm) {
        this.titoloFilm = titoloFilm;
    }

  

    public List<String> onButtonRicercaClicked() {
        FilmDAO filmDAO = DAOFactory.getFilmDAO();
        List<String> listaTitoli = filmDAO.getFilm(titoloFilm);
                       
        
        return listaTitoli;
    }
    
    
    
}
