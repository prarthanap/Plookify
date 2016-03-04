/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.logic.logic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author jll30
 */
public class test2
{
    public static void main(String[] args) throws SQLException, ParseException{
    database test=new database();
    ResultSet result=test.makeQuery("SELECT DEVICEID FROM DEVICE");
    while(result.next()){
            String name = result.getString(1);
            String name2=result.getString(1);
             System.out.println(name+" "+name2);}
    //ResultSet result2=test.makeQuery("SELECT USERID from SUBSCRIPTION where DUEDATE!=null or date(DUEDATE)> date('2016-02-29')");
    //while(result2.next())
    //{
    //System.out.println(result2.getInt(1));
    //}
    
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /*Date date = new Date();
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
        }*/
        //logic log1=new logic();
        //System.out.println(log1.daysBeforeNow("2016-02-03"));
        test.makeUpdate("UPDATE DEVICE SET DEVICENAME='CHANGED' WHERE DEVICEID='4'");
        
        
        
    }
            
}
