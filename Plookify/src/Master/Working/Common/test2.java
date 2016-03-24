/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jll30
 */
public class test2
{
    public static void main(String[] args)
    {
        database data=new database();
        
        //data.makeUpdate("DELETE FROM PLAYLISTTRACK WHERE PLAYLIST=1 AND TRACK=22");
        //data.conClose();
        try {
            ResultSet rs=data.makeQuery("Select * from PLAYLISTTRACK");
            while(rs.next())
            {
            System.out.println(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
            
}
