/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cinemates.DAO.FilmImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fabrizio
 */
@SuppressWarnings("empty-statement")
public class createListTest {
    
    public createListTest() {
    }
    

    @Test
    public void testCFGPath1_2_3_4_5_6_7_9(){
    
    List<String> film = new ArrayList<>();
    FilmImpl filmImpl = new FilmImpl();
    film.add("Avengers: Endgame");
    String jsonString = "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg\",\"genre_ids\":[12,878,28],\"id\":299534,\"original_language\":\"en\",\"original_title\":\"Avengers: Endgame\",\"overview\":\"In seguito alle azioni di Thanos nel precedente Avengers: Infinity War la popolazione dell'intero universo è stata dimezzata e tra i caduti c'è stato anche Nick Fury. Ma prima di morire questi è riuscito a lanciare un messaggio nello spazio alla potentissima Capitan Marvel, che tornata sulla Terra e di fronte a un gruppo di Avengers afflitto dalla sconfitta e dal lutto, vuole prendere le cose in mano. Quello che ha fatto Thanos però non si può risolvere con la semplice superforza e i colpi di energia...\",\"popularity\":289.161,\"poster_path\":\"/gj0fKa4jjwxZLmVq7I8tv13V45.jpg\",\"release_date\":\"2019-04-24\",\"title\":\"Avengers - Endgame\",\"video\":false,\"vote_average\":8.3,\"vote_count\":17445}],\"total_pages\":1,\"total_results\":1}";
    assertEquals(film,filmImpl.createList(jsonString));
    
}
    
    public void testCFGPath1_2_3_4_9(){
  
        List<String> film = new ArrayList<>();
        FilmImpl filmImpl = new FilmImpl();
        String jsonString = "{\"page\":1,\"results\":[],\"total_pages\":0,\"total_results\":0}";
        assertEquals(film,filmImpl.createList(jsonString));
    }
    
}
    

