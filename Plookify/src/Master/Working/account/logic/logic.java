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
    public void addAccount(String uname,String fname,String lname)
    {
        if(data.dupcheck(fname,"FIRSTNAME", "ACCOUNT"))
        {System.out.println(false);}
        else
        {
            System.out.println(true);
            data.makeUpdate("INSERT INTO ACCOUNT (USERNAME,PASSWORD,FIRSTNAME,LASTNAME,DOORNO,STREET,CITY,COUNTY,POSTCODE,CONTACTNO) VALUES('USERNAME','pass1','bacon','LASTNAME','DOORNO','STREE','CITY','COUNTY','POSTCODE','CONTACTNO')");
            System.out.println("added");
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
