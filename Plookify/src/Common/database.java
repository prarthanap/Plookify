package Common;

/**
 *
 * @author jlleow
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database {

    


public class Database {

	private Connection connection = null;

	private Database()
        {	
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:data.db");
                }
            catch (SQLException ex)
                {
                    throw new RuntimeException("Database connection failed!", ex);
		}
	}
	
	public void start_database(){
		
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate("create table `PERSONAL` (`ID` integer, `USERNAME` string, `FORENAME` string, `SURNAME` string)");
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
    
}
