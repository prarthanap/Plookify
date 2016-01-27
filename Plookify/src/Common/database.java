package Common;

/**
 *
 * @author jlleow
 * 
 * 
 * Database testing area
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database
{
    private Connection connection = null;

 
    public static void main(String[] args)
        {
            System.out.println("starto!");
            database test=new database();
            test.makeTables();
            System.out.println("endo!");
        }

	private void Database(){}
	
	public void makeTables()//CURRENTLY RANDOM TESTING
        {
		try {
                connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException("Database connection failed!", ex);
		}
                
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
			//statement.executeUpdate("drop table PERSONAL");
                        //statement.executeUpdate("create table PERSONAL('ID' INT(3),'USERNAME' STRING(10))");
                        statement.executeUpdate("INSERT INTO PERSONAL VALUES (14,'ab')");
                        ResultSet rs=statement.executeQuery("SELECT ID,USERNAME FROM PERSONAL");
                        while (rs.next())
                        {
                            System.out.println("ID = " + rs.getString("ID"));
                            System.out.println("USERNAME = " + rs.getString("USERNAME"));
                        }
                    }
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			if (connection != null){
				try{
					connection.close();
				}
				catch(SQLException ex){
					System.err.println(ex.getMessage());
				}
			}
		}
	}

	public Connection getConnection(){
		return this.connection;
	}
}
