/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.gui;

import Master.Working.Common.database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class MainScreenController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> tSong;
    @FXML
    private TableColumn<?, ?> tArtist;
    @FXML
    private TableColumn<?, ?> tAlbum;
    @FXML
    private TableColumn<?, ?> tDuration;
    @FXML
    private TextField searchBar;
    @FXML
    private Button search;
    @FXML
    private Label playlistLabel;
    @FXML
    private TextField playlistSearchBar;
    @FXML
    private Button playlistSearch;
    @FXML
    private TableView<?> playlistsTable;
    @FXML
    private TableColumn<?, ?> tPlaylists;
    @FXML
    private TextField newPlaylistName;

    /**
     * Initializes the controller class.
     */
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
     
    public String getUsername(){ 
        String temp = "er303"; 
        return temp;
    }
    
}
