package Master.Working.Common;

/**
 *
 * @author jlleow AKA ec14002
 * 
 * Code for making queries or updates to database
 * 
 * 16/02/2016
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database
{
 
    public void Database(){}
     /*public static void main(String[] args) throws SQLException//just testing code (DO NOT RUN)
        {
            System.out.println("starto!");
            database data1=new database();
            ResultSet result=data1.makeQuery("SELECT * FROM ACCOUNT");
            while(result.next())
            {
                String name = result.getString(4);
                System.out.println(name);
                name=result.getString(10);
                System.out.println(name);
            }
          
            //System.out.println();
        }*/
	
	public ResultSet makeQuery(String query)//method to take a string as a query for database, returns resultset
        {
            
            try {
                    Statement statement;
                    ResultSet red=null;
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                    statement = connection.createStatement();
                    statement.setQueryTimeout(10);
                    ResultSet res=statement.executeQuery(query);
                    System.out.println("Query made");
                    return res;
                }
            catch (SQLException ex) {System.err.println(ex.getMessage());}


            return null;
	}
        
        public void makeUpdate(ArrayList<String> list1)//method for running multiple statements from an arraylist
        {                
		
		try {
                        Statement statement;
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                        statement = connection.createStatement();
			statement.setQueryTimeout(10);
                        for(int i=0;i<list1.size();i++)
                        {
                            statement.executeUpdate(list1.get(i));
                        }
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
            
        }
        
        public void makeUpdate(String query)//method running only one statement(not worth creating an arraylist for 1 update using the prev method
        {
		
		try {
                        Statement statement;
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                        statement = connection.createStatement();
                        statement.setQueryTimeout(10);
                        statement.executeUpdate(query);
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
        }
        
        
        public boolean dupCheck(String search,String column,String table)//checks if there is a row match using what u searching for, the table and which column
        {
        try {
            ResultSet check=makeQuery("SELECT "+column+" FROM "+table+" WHERE "+column+"='"+search+"'");
            int count=0;
            while(check.next())
                {
                    count++;
                }
                return count != 0;
            } 
        catch (SQLException ex) {System.out.println("dupcheck error!");}
        return false;
        }
        
        public boolean dupCheck(String query)//checks if there is a row match using custom query
        {
            
        try {
            ResultSet check=makeQuery(query);
            int count=0;
            while(check.next())
                {
                    count++;
                }
                return count != 0;
            } 
        catch (SQLException ex) {System.out.println("dupcheck error!");}
        return false;
        }
        
        public int authCheckD(String uname,String pass1)
        {
            String authtest="SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"' and PASSWORD='"+pass1+"'";
            if(dupCheck(authtest))
            {
                try {
                    return makeQuery(authtest).getInt(1);
                    }
                catch (SQLException ex) {
                    Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return 9999;//no one should have this

        }

}