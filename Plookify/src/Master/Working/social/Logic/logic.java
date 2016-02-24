/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

/**** @author Hamza */

import Master.Working.Common.database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class logic {
    
    private database data=new database();
    
    public logic()
    {
    
    }
    
    public boolean accTypeCheck(String premium, String uname)
    {
        String id = "SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"'";
        String paid = "SELECT USERID AND SUBSCRIPTION TYPE FROM SUBCRIPTION WHERE USERID='"+id+"'";
        if(paid.equals("PREMIUM"))
        {
            return true;       
        }
        return false;
    }
    
    public int authCheck(String uname, String pass1)
    {
        String authtest="SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"' and PASSWORD='"+pass1+"'";
        if(data.dupcheck(authtest))
        {
            try {
                authtest="SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"' and PASSWORD='"+pass1+"'";
                return data.makeQuery(authtest).getInt(1);
                }
            catch (SQLException ex) {
                Logger.getLogger(Master.Working.account.logic.logic.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        return 9999;
    }
    
    public void findFriend()
    {
    
    
    }
    
    public void discover(String uname, String premium)
    {
        boolean check = accTypeCheck(uname, premium);
        if(check==true)
        {
        
        
        }
        
    }
    
    public void friendRequest(String uname, String friendname)//checks if username exists then inserts record
    {
        if(data.dupcheck(uname,"USERNAME","ACCOUNT"))
        {System.out.println(true);}
        else
        {
            System.out.println(false);
            String update="INSERT INTO FRIENDLIST (OWNERID,FRIENDID) VALUES('"+uname+"','"+friendname+"')";
            //System.out.println(update);
            data.makeUpdate(update);
            System.out.println("added");
        }
    }
    
    public int friendList()
    {
     int followers = 0;
     
     return followers;
    }
    
    public void friendPlaylist()
    {
    
    
    }
}
