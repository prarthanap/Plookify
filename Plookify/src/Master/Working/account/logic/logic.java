package Master.Working.account.logic;

import Master.Working.Common.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jlleow
 */
public class logic extends database
{
    public logic()
    {
        database data=new database();
    }
    public void addAccount(String fname,String sname,String uname)
    {
        dupcheck(uname);
    }
    
    public void deleteAccount()
    {
        
    }
    
    public void authCheck()
    {
        
    }
    
    public void subscribe()
    {
        
    }
}
