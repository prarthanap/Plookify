/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

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
    
    
    public checkPublic(int ID)
    {
        accountID = ID;
    }
    
    public double checkPublicity()
    {
        double stat = 0;
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:Scratch/db/data.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PUBLICITY FROM SUBSCRIPTION where USERID='"+accountID+"';");
            while(rs.next())
            {
                stat = rs.getDouble("PUBLICITY");
            }
            status = stat;
            rs.close();
            stmt.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return status;
    
    }
    
    
}
