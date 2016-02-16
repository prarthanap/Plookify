package Master.Working.account.logic;

/**
 *
 * @author jlleow AKA ec14002
 * 
 * Code for making queries or updates to database
 */
import Master.Working.Common.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class database
{
 
    public void Database(){}
    public static void main(String[] args) throws SQLException//just testing code (DO NOT RUN)
        {
            System.out.println("starto!");
            database data1=new database();
            ResultSet result=data1.makeQuery("SELECT * FROM TRACKS");
            while(result.next())
            {
                String name = result.getString(2);
                System.out.println(name);
            }
            boolean check=dupcheck("Lithium","TRACKNAME","TRACKS");
            System.out.println(check);
          
            //System.out.println();
        }
	
	public static ResultSet makeQuery(String query)//method to take a string as a query for database, returns resultset
        {
            Statement statement;
            ResultSet red=null;
            try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                    statement = connection.createStatement();
                    statement.setQueryTimeout(10);
                    ResultSet res=statement.executeQuery(query);
                    System.out.println("Query made");
                    return res;
                }
            catch (SQLException ex) {System.err.println(ex.getMessage());}


            return red;
	}
        
        public void makeUpdate(ArrayList<String> list1)//method for running multiple statements from an arraylist
        {                
		Statement statement;
		try {
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
		Statement statement;
		try {
                            Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                            statement = connection.createStatement();
                            statement.setQueryTimeout(10);
                            statement.executeUpdate(query);
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
        }
        
        
        public static boolean dupcheck(String search,String column,String table)//checks if there is a match using what u searching for, the table and which column
        {
            ResultSet check=makeQuery("SELECT "+column+" FROM "+table+" WHERE "+column+"='"+search+"'");
        try {
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

}