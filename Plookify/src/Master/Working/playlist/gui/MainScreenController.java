/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.gui;

import Master.Working.Common.database;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class MainScreenController implements Initializable {

    @FXML
    private TableView<String> table;
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
    private TableView<String> playlistsTable;
    @FXML
    private TableColumn<?, ?> tPlaylists;
    @FXML
    private TextField newPlaylistName;
    @FXML
    private ComboBox pType;
    @FXML
    private Label label;
    
    ObservableList<String> playlistList = FXCollections.observableArrayList();
    ObservableList<String> playlist = FXCollections.observableArrayList();
    String user = getUsername();
    database db = new database();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tArtist.setCellValueFactory(new PropertyValueFactory("artist"));
        tAlbum.setCellValueFactory(new PropertyValueFactory("album"));
        tDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        
        tPlaylists.setCellValueFactory(new PropertyValueFactory("playlists"));
        
        pType.getItems().addAll("Private","Friend");
        pType.setValue("Private");
        //updatePlaylists();
        
    }    
    
    public void createPlaylist(){
        try{
        String playlistName = newPlaylistName.getText();
        String type = "Y";
        db.makeUpdate("INSERT into PLAYLIST (PLAYLISTOWNER,PLAYLISTNAME,PRIVATE) VALUES('"+user+"','"+playlistName+"','"+type+"')");
        playlistLabel.setText(playlistName);// TEMP CODE
        pType.setValue("Private");
        updateList();
        }
        catch(Exception ee){
        }
    }
    
    public void setPlaylistType(){
        
        
    }
    
    public void updateList() throws SQLException{
        try {
            ResultSet rs = db.makeQuery("SELECT * FROM PLAYLIST WHERE PLAYLISTOWNER ='"+user+"'");
                while (rs.next()){
                    playlistList.add(
                       rs.getString("PLAYLISTNAME")
                    );
            System.out.println(playlistList);      
            playlistsTable.setItems(playlistList);
            playlistsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }
        }
        catch(Exception e){
        }
    }
    
    public void updatePlaylist ()throws SQLException{
        ResultSet rs = db.makeQuery("SELECT * FROM PLAYLIST-TRACK"); // TEMP
            while (rs.next()){
                    playlist.add(
                       rs.getString("TRACK") // TEMP
                    );
            System.out.println(playlist);      
            table.setItems(playlist);
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    }
            
     
    public String getUsername(){ 
        String temp = "er303"; 
        return temp;
    }
    
}
