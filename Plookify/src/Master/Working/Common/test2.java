/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jll30
 */
public class test2
{
    public static void main(String[] args) throws SQLException{
    database test=new database();
    ResultSet result=test.makeQuery("SELECT * FROM ACCOUNT");
    while(result.next()){
            String name = result.getString(4);
            String name2=result.getString(5);
             System.out.println(name+" "+name2);}
    ResultSet result2=test.makeQuery("SELECT USERID from SUBSCRIPTION where DUEDATE!=null or date(DUEDATE)> date('2016-02-29')");
    while(result2.next())
    {
    System.out.println(result2.getInt(1));
    }
    
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    System.out.println(dateFormat.format(date));
    
    String date2="2016-03-03";
    Date dateB;
        try {
            dateB = dateFormat.parse(date2);
            System.out.println(date.before(dateB));
        } catch (ParseException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ResultSet rs=test.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+4+"'");

        Date dateN = new Date();
        String dateToday = dateFormat.format(dateN);
        
            
        try {
            String dateDue = rs.getString(4);
            System.out.println(dateDue);
            Date dateC = dateFormat.parse(dateDue);
            System.out.println(date.after(dateC));
        } catch (ParseException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        

    }
            
}
