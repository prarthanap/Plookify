/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

/**** @author Hamza */

import Master.Working.Common.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    public int premCheck(int iD)
    {
        ResultSet rs=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'");
        try {
            if(rs.getString(3).equals("1"))
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                try {
                    String dateDue = rs.getString(4);
                    Date dateB=dateFormat.parse(dateDue);
                    //System.out.println(date.after(dateB));
                    if(date.after(dateB))//if current date is past due date(so not paid)
                    {
                        return -1;//expired premium
                    }
                    else{return 2;}//premium
                } catch (SQLException ex) {
                    Logger.getLogger(Master.Working.account.logic.logic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Master.Working.account.logic.logic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{return 1;}//free
        } catch (SQLException ex) {
            Logger.getLogger(Master.Working.account.logic.logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;//if error in checking
    }
    
    public void add()
    {
        
    
    }
    
    public void discover(String uname, String premium)
    {
        boolean check = accTypeCheck(uname, premium);
        if(check==true)
        {
        
        
        }
        
    }
    
    public void friendRequest(String uname, String friendname)
    {
        if(data.dupCheck(uname,"USERNAME","ACCOUNT"))
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

