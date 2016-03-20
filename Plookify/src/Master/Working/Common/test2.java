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
        try {
            database data=new database();
            ResultSet pubID=data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM=1 and PUBLICITY='0.0'");
            ArrayList<String> namesList=new ArrayList<>();
            while (pubID.next())//for every matching record a username is gotten
            {
                namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='"+pubID.getInt(1)+"'").getString(1));
            }
            for (String a : namesList)
            {System.out.println(a);}
        } catch (SQLException ex) {Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);}
        
        
    }
            
}
