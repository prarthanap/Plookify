package Master.Working.account.logic;

import Master.Working.Common.database;
import java.sql.SQLException;
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
        return 9999;
        
    }
    public int getDetailInt(int identifier,String table,String search)
    {
        try {
            return data.makeQuery("SELECT "+search+" from "+table+" where ID='"+identifier+"'").getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 9999;
    }
    public String getDetailString(int identifier,String table,String search)
    {
        try {
            return data.makeQuery("SELECT "+search+" from "+table+" where ID='"+identifier+"'").getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }
            
    public void subscribe()
    {
        
    }
    public boolean duplicateCheck(String search,String column,String table)
    {
        return data.dupCheck( search, column, table);
    }
}
