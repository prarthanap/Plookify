
package Master.Working.playlist.gui;

import java.net.URL;
import Master.Working.Common.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class playlistController implements Initializable {

    
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn tSong;
    @FXML
    private TableColumn tArtist;
    @FXML
    private TableColumn tAlbum;
    @FXML
    private TableColumn tDuration;
    @FXML
    private TableView<String> playlistsTable;
    @FXML
    private TableColumn tPlaylists;
    @FXML
    private Button search;
    @FXML
    private Button playlistSearch;
    @FXML
    private Button createPlaylist;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField playlistSearchBar;
    @FXML
    private TextField newPlaylistName;
    @FXML
    private Label playlistLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void createPlaylist(){
        database db = new database();
        String playlistName = newPlaylistName.getText();
        String user = getUsername();
        db.makeUpdate("INSERT into PLAYLIST (PLAYLISTOWNER,PLAYLISTNAME) VALUES('"+user+"','"+playlistName+"')");
        playlistLabel.setText(playlistName);// TEMP CODE
    }
    
    public void searchArtist(){
       database db = new database();
       String searchedArtist = searchBar.getText();
       ResultSet rs=db.makeQuery("SELECT * from TRACKS where ARTIST = '" + search+"'");
       
    }
    
    public String getUsername(){ 
        String temp = "er303"; 
        return temp;
    }
    
      
    
}
