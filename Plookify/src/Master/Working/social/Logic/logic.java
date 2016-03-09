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
    
    public database data=new database();
    
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
        try {
            int premValue=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'").getInt(2);
            String dateDue=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'").getString(4);
            data.conClose();
            if(premValue==1)
            {
                DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                 //System.out.println(dateDue);
                Date dateB=dFormat.parse(dateDue);
                //System.out.println(date.after(dateB));
                if(date.after(dateB))//if current date is past due date(so not paid)
                {
                    
                    return -1;//expired premium
                }
                else{
                    
                    return 2;
                    }//premium 
            }
            else{
                
                return 1;
                }//free
            } catch (SQLException | ParseException ex)
            {
            Logger.getLogger(Master.Working.account.logic.logic.class.getName()).log(Level.SEVERE, null, ex);
            }
        return 0;//if error in checking
    }
        
    public void add(String uname,String fname)//checks if username exists then inserts record
    {
        if(data.dupCheck(uname,"USERNAME","ACCOUNT"))
        {System.out.println(true);}
        else
        {
            System.out.println(false);
            String update="INSERT INTO FRIENDLIST (OWNERID,FRIENDID)VALUES('"+uname+"','"+fname+"')";
            //System.out.println(update);
            data.makeUpdate(update);
            System.out.println("added");            
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
    
    public void deleteFriend(String uname)
    {
        String update = "DELETE FROM FRIENDLIST WHERE FRIENDID='"+uname+"';";
        data.makeUpdate(update);
    }
    
    public void publicity(int id)
    {
        String change = "UPDATE SUBSCRIPTION SET PUBLICITY='PRIVATE' WHERE USERID='4';";
        data.makeUpdate(change);
        System.out.println("confirmed");
    }
    
    
    public void friendPlaylist()
    {
    
    
    }
}

