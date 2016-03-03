package Master.Working.account.logic;

import Master.Working.Common.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jlleow
 */
public class logic
{
    private database data=new database();
    public logic()
    {
        
    }
    public void addAccount(String uname,String fname,String lname,String pass,String[] address)//checks if username exists then inserts record
    {
        if(data.dupCheck(uname,"USERNAME","ACCOUNT"))
        {System.out.println(true);}
        else
        {
            System.out.println(false);
            String update="INSERT INTO ACCOUNT (USERNAME,PASSWORD,FIRSTNAME,LASTNAME,DOORNO,STREET,CITY,COUNTY,POSTCODE,CONTACTNO) VALUES('"+uname+"','"+pass+"','"+fname+"','"+lname+"','"+address[0]+"','"+address[1]+"','"+address[2]+"','"+address[3]+"','"+address[4]+"','"+address[5]+"')";
            //System.out.println(update);
            data.makeUpdate(update);
            System.out.println("added");
        }
    }
    
    public void deleteAccount()
    {
        
    }
    
    public int authCheck(String uname,String pass1)
    {
        String authtest="SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"' and PASSWORD='"+pass1+"'";
        if(data.dupCheck(authtest))
        {
            try {
                authtest="SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"' and PASSWORD='"+pass1+"'";
                return data.makeQuery(authtest).getInt(1);
                }
            catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 9999;//no one should have this
        
    }
            
    public void subscribe()
    {
        
    }
    public boolean duplicateCheck(String search,String column,String table)
    {
        return data.dupCheck( search, column, table);
    }
    public int premCheck(int iD)
    {
        ResultSet rsPremCheck=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'");
        try {
            if(rsPremCheck.getInt(2)==1)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                try {
                    String dateDue = rsPremCheck.getString(4);
                    System.out.println(dateDue);
                    Date dateB=dateFormat.parse(dateDue);
                    //System.out.println(date.after(dateB));
                    if(date.after(dateB))//if current date is past due date(so not paid)
                    {
                        return -1;//expired premium
                    }
                    else{return 2;}//premium
                } catch (SQLException ex) {
                    Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{return 1;}//free
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;//if error in checking
    }
    public String stringGet(int ident,String identColumn,String table,String column)
    {
        ResultSet result1=null;
        String statementA="SELECT "+column+" from "+table+" where "+identColumn+"='"+ident+"'";
        result1=data.makeQuery(statementA);
        try {
            return result1.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("null");
        return null;
    }
}
