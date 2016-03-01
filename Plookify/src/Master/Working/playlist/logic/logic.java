
package Master.Working.playlist.logic;

import Master.Working.Common.database;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Edgar
 */
public class logic {
    
    
    
    public static void main(String[] args) throws SQLException{
        database db = new database();
        String search = "Adele"; 
        ResultSet rs=db.makeQuery("SELECT * from TRACKS where ARTIST = '" + search+"'"); //searches for only one artist
        while(rs.next()){
            String artist = rs.getString(3);
            System.out.println(artist);}
    }
    
    public void createPlaylist()
    {
        
    }
    
    public void renamePlaylist()
    {
        
    }
    
    public void sortPlaylist()
    {
        
    }
    
    public void addToPlaylist()
    {
        
    }
    
    public void searchPlaylist()
    {
        
    }
}
