package Master.Working.account.logic;

import Master.Working.Common.database;

/**
 * @author jlleow
 */
public class logic
{
    private database data=new database();
    public logic()
    {
        
    }
    public void addAccount(String fname,String sname,String uname)
    {
        if(data.dupcheck(fname,"FIRSTNAME", "ACCOUNT"))
        {System.out.println(false);}
        else
        {
            System.out.println(true);
            //data.makeUpdate("INSERT INTO ACCOUNT VALUES('USERNAME,'PASSWRD','FIRSTNAME','LASTNAME','DOORNO','STREE','CITY','COUNTY','POSTCODE','CONTACTNO'))");
        }
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
