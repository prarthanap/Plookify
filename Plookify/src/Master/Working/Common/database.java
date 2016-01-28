package Master.Working.Common;

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
                        //statement.executeUpdate("create table TRACKS('TRACKID' INT(3),'SONG NAME' STRING(10),'ARTIST' STRING(10),'GENRE' STRING(10),'ALBUM' STRING(16),'LENGTH' INT(6))");
                        //statement.executeUpdate("create table PLAYLISTS('ID' INT(3),'PLAYLISTID' INT(4),'SONGS' BIGINT(10),'PLAYLISTTYPE' STRING(10))");
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
        
        public ResultSet makeQuery(String query)//generic query method for database
        {
            Statement stat;
            ResultSet result=null;
            try {
			stat = connection.createStatement();
			stat.setQueryTimeout(10);
                        result=stat.executeQuery(query);
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
            return result;
        }
        
}
