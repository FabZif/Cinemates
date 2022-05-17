/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cinemates.DAO.AdminImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fabrizio
 */
public class checkEsistenzaAdminTest {
    
    public checkEsistenzaAdminTest() {
    }
    
    
    @Test
    public void testCFGPath1_2_3_4_11(){
        AdminImpl adminImpl = new AdminImpl();
        String jsonString = "{\"statusCode\":200,\"body\":[],\"headers\":{\"Content-Type\":\"application/json\"}}";
        assertFalse(adminImpl.checkEsistenzaAdmin(jsonString, "TEST", "TEST"));
    }
    
    @Test
    public void testCFGPath1_2_3_4_5_6_11(){
        AdminImpl adminImpl = new AdminImpl();
        String jsonString = "{\"statusCode\":200,\"body\":[{\"idAdmin\":0,\"Username\":\"ADMIN\",\"Password\":\"ADMIN\",\"Immagine\":null}],\"headers\":{\"Content-Type\":\"application/json\"}}";
        assertFalse(adminImpl.checkEsistenzaAdmin(jsonString, "TEST", "TEST"));
    }
    
    @Test
    public void testCFGPath1_2_3_4_5_6_7_8_11(){
        AdminImpl adminImpl = new AdminImpl();
        String jsonString = "{\"statusCode\":200,\"body\":[{\"idAdmin\":0,\"Username\":\"ADMIN\",\"Password\":\"ADMIN\",\"Immagine\":null}],\"headers\":{\"Content-Type\":\"application/json\"}}";
        assertTrue(adminImpl.checkEsistenzaAdmin(jsonString, "ADMIN", "ADMIN"));
    }
    
    
    
}
