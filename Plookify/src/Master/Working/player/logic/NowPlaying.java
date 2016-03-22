package Master.Working.player.logic;

import Master.Working.Common.database;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prarthana
 */
public class NowPlaying {

    private List<String> list = new ArrayList<String>();

    public NowPlaying() {
    }

    public List<String> getNowPlaying() {

        database db = new database();
        ResultSet rs = db.makeQuery("SELECT * FROM NOWPLAYING");
        try {
            while (rs.next()) {
                list.add(rs.getString("TRACKNAME"));

            }

        } catch (Exception e) {

        }
        return list;
    }

    public List<String> removeNowPlaying(Object o) {

        database db = new database();
        ResultSet rs = db.makeQuery("DELETE from NOWPLAYING where TRACKNAME = '" + o + "'");

        try {
            while (rs.next()) {
                list.remove(rs.getString("TRACKNAME"));
            }

        } catch (Exception e) {

        }
        return list;

    }
    
    
    public void removeAll(){
        
        database db = new database();
        ResultSet rs =  db.makeQuery("DELETE * from NOWPLAYING");
        
                
        
    }

}
