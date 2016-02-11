package Master.Working.player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class NewClass1 {
	Connection conn = null;
	public static Connection DbConnector() throws SQLException{
	try{
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:\\Users\\prarthana\\data.db");


		return conn;
	}
	catch(ClassNotFoundException | SQLException e)
	{
	System.out.println(e);


	}

	return null;
	}


        public void CheckConnection(){
            
            conn = NewClass1.DbConnector();
            if(conn == null){
                System.out.println("Unsuccessful");
                
                
                
            }
            else{
                System.out.println("Successful");
                
            }
            
            
        }

}