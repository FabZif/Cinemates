/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemates.Controller;

import Model.Film;
import cinemates.DAO.DAOFactory;
import cinemates.DAO.FilmDAO;
import java.util.List;

/**
 *
 * @author Fabrizio
 */
public class GeneraListaPopolariController {
    


public List<Film> onButtonGeneraClicked(String genere,String anno){
            Integer idGenere=0;
            
            switch (genere){
               case "Horror":
                  idGenere=27;
                  break;
               case "Fantasy":
                   idGenere=14;
                   break;
               case "Azione":
                   idGenere=28;
                   break;
               case "Avventura":
                   idGenere=12;
                   break;
               case "Animazione":
                   idGenere=16;
                   break;
               case "Commedia":
                   idGenere=35;
                   break;
               case "Crime":
                   idGenere=80;
                   break;
               case "Documentario":
                   idGenere=99;
                   break;
               case "Drammatico":
                   idGenere=18;
                   break;
               case "Famiglia":
                   idGenere=10751;
                   break;
               case "Storici":
                   idGenere=36;
                   break;
               case "Musicale":
                   idGenere=10402;
                   break;
               case "Mistery":
                   idGenere=9648;
                   break;
               case "Romanzo":
                   idGenere=10749;
                   break;
               case "Fantascienza":
                   idGenere=878;
                   break;
               case "Serie TV":
                   idGenere=10770;
                   break;
               case "Thriller":
                   idGenere=53;
                   break;
               case "Guerra":
                   idGenere=10752;
                   break;
               case "Western":
                   idGenere=37;
                   break;
                   
               default : break;
    }
 
  FilmDAO filmDAO = DAOFactory.getFilmDAO();
  
  List<Film> listaFilm = filmDAO.getFilmPopolari(idGenere,anno,genere);
  
  return listaFilm;


}



}