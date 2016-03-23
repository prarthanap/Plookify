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
        String update="INSERT INTO ACCOUNT (USERNAME,PASSWORD,FIRSTNAME,LASTNAME,DOORNO,STREET,CITY,COUNTY,POSTCODE,CONTACTNO) VALUES('jll301','pass','j','l','55','asd','asds','asd','asdasd','555')";
            //System.out.println(update);
            
            data.makeUpdate(update);
            System.out.println("added acc");
            data.conClose();
        try {
            ResultSet rs=data.makeQuery("Select username from ACCOUNT");
            while(rs.next())
            {
            System.out.println(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
            
}
