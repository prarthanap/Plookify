package Master.Working.Common;

/**
 *
 * @author jlleow AKA ec14002
 * 
 * Code for making queries or updates to database
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
    private Connection connection = null;
 
    public void Database(){}
    public static void main(String[] args)//just testing code (DO NOT RUN)
        {
            System.out.println("starto!");
            database data1=new database();
            ArrayList<String> inst1=fillAl();
            data1.makeUpdate(inst1);
            System.out.println("endo!");
        }
	
	public ResultSet makeQuery(String query)//method to take a string as a query for database, returns resultset
        {
		try {connection = DriverManager.getConnection("jdbc:sqlite:data.db");}
                catch (SQLException ex)
                {throw new RuntimeException("Database connection failed!", ex);}
                
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
                        return statement.executeQuery(query);
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		finally {
			if (connection != null){
				try{connection.close();}
				catch(SQLException ex){System.err.println(ex.getMessage());}
			}
		}
            return null;
	}
        
        public void makeUpdate(ArrayList<String> list1)//method for running multiple statements from an arraylist
        {
            try {connection = DriverManager.getConnection("jdbc:sqlite:data.db");}
                catch (SQLException ex)
                {throw new RuntimeException("Database connection failed!", ex);}
                
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
                        for(int i=0;i<list1.size();i++)
                        {
                            statement.executeUpdate(list1.get(i));
                        }
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		finally {
			if (connection != null){
				try{connection.close();}
				catch(SQLException ex){System.err.println(ex.getMessage());}
			}
		}
            
        }
        
        public void makeUpdate(String query)//method running only one statement(not worth creating an arraylist for 1 update using the prev method
        {
            try {connection = DriverManager.getConnection("jdbc:sqlite:data.db");}
                catch (SQLException ex)
                {throw new RuntimeException("Database connection failed!", ex);}
                
		Statement statement;
		try {
                            statement = connection.createStatement();
                            statement.setQueryTimeout(10);
                            statement.executeUpdate(query);
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		finally {
			if (connection != null){
				try{connection.close();}
				catch(SQLException ex){System.err.println(ex.getMessage());}
			}
		}
        }
        
	public Connection getConnection(){return this.connection;}
        
        
        public static ArrayList<String> fillAl()
        {
            ArrayList<String> inst=new ArrayList();
            inst.add("create table TRACKS('SONGID' INT(3),'SONG_NAME' STRING(16),'ARTIST' STRING(20),'GENRE' STRING(12),'ALBUM' STRING(15),'LENGTH' INT(6))");
            inst.add("create table PLAYLIST('ID' INT(3),'PLAYLISTID' INT(3),'SONGIDS' INT(15),'PTYPE' STRING(10))");
            inst.add("create table FRIENDLIST('ID' INT(3),FRIENDIDS INT(15))");
            inst.add("create table PERSONAL('ID' INT(3), 'USERNAME' STRING(12),'PASSWORD' STRING(16), 'TITLE' STRING(4),'FORENAME' STRING(15), 'SURNAME' STRING(15))");
            return inst;            
        }
        
        public boolean dupcheck(String query)//checks if there is a match
        {
            ResultSet check=makeQuery(query);
        try {
            int count=0;
            while(check.next())
                {
                    count++;
                }
                return count != 0;
            } 
        catch (SQLException ex) {Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);}
        return false;
        }

        
}
