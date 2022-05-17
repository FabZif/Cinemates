/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.DAO;

import Model.Film;
import java.util.List;

/**
 *
 * @author Fabrizio
 */
public interface FilmDAO {

public List<String> getFilm(String titoloFilm);    

public List<Film> getFilmPopolari(Integer idGenere, String anno,String genere);
    
    
}
