/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

import Master.Working.Common.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Hamza
 */
public class checkPublic {
    
    private double status; 
    private int accountID;
    public database data = new database();
    
    public checkPublic(int ID)
    {
        accountID = ID;
    }
    
    public double checkPublicity()
    {
        double stat = 0;
        try{
            
            ResultSet rs = data.makeQuery("SELECT PUBLICITY FROM ACCOUNT where ID='"+accountID+"';");
            while(rs.next())
            {
                stat = rs.getDouble("PUBLICITY");
            }
            status = stat;
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return status;
    
    }
    
    
}
