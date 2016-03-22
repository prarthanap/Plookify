/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.sql.ResultSet;

/**
 *
 * @author Hamza
 */
public class NewClass {
    
   
    
    public static void main(String[] args)
    {
        database data = new database();
        String privateUpdate = "UPDATE SUBSCRIPTION set PUBLICITY = '100.0' where USERID='3'";
            data.makeUpdate(privateUpdate);
            data.conClose();
        
    }
    
}
