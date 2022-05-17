/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cinemates.DAO.FilmImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fabrizio
 */
public class generateURLCorrettoTest {
    
    public generateURLCorrettoTest() {
    }
    
    
    @Test
    public void testPathCFG1_2_3_4_5(){
        FilmImpl filmImpl = new FilmImpl();
        
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            filmImpl.generateURLCorretto("");
        });
        
    }
    
    @Test
    public void testPathCFG1_2_3_4_6_11_13(){
                FilmImpl filmImpl = new FilmImpl();
                String URL = "https://api.themoviedb.org/3/search/movie?api_key=fa204481956a9c33c385f12bf1481037&language=it-IT&query=Avengers";
                assertEquals(URL,filmImpl.generateURLCorretto("Avengers"));

    }
    
    @Test
    public void testPathCFG1_2_3_4_6_7_8_13(){
        FilmImpl filmImpl = new FilmImpl();
                String URL = "https://api.themoviedb.org/3/search/movie?api_key=fa204481956a9c33c385f12bf1481037&language=it-IT&query=Avengers%20Endgame";
                assertEquals(URL,filmImpl.generateURLCorretto("Avengers Endgame"));
    }
    
}
