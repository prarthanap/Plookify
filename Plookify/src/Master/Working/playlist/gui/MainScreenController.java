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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class MainScreenController implements Initializable {

    @FXML
    private TableView<Songs> table;
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
    private TableView<Playlist> playlistsTable;
    @FXML
    private TableColumn<Playlist, String> tPlaylists;
    @FXML
    private TextField newPlaylistName;
    @FXML
    private ComboBox pType;
    @FXML
    private Label label;
    @FXML
    private AnchorPane playlistType;
    @FXML
    private TableView<Songs> searchTable;
    @FXML
    private TableColumn<?, ?> stSong;
    @FXML
    private TableColumn<?, ?> stArtist;
    @FXML
    private TableColumn<?, ?> stDuration;
    
    ObservableList<Playlist> playlistList = FXCollections.observableArrayList();
    ObservableList<Songs> playlistSongs = FXCollections.observableArrayList();
    ObservableList<Songs> songList = FXCollections.observableArrayList();
    String user = getUsername();
    database db = new database();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateList();
        tSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        tAlbum.setCellValueFactory(new PropertyValueFactory("album"));
        tDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
        
        stSong.setCellValueFactory(new PropertyValueFactory("songName"));
        stArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        stDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
            
        tPlaylists.setCellValueFactory(new PropertyValueFactory<Playlist,String>("name"));
            
        pType.getItems().addAll("Private","Friend");
        pType.setValue("Private");
            
        }
    
    @FXML
    public void createPlaylist(){
            
        String playlistName = newPlaylistName.getText();
        Playlist newPlaylist = new Playlist(playlistName);
        String type = "Y";
        playlistLabel.setText(playlistName);
        pType.setValue("Private");
        playlistsTable.getItems().add(newPlaylist);
        String update="INSERT INTO PLAYLIST (PLAYLISTOWNER,PLAYLISTNAME,PRIVATE) VALUES('"+user+"','"+playlistName+"','"+type+"')";
        db.makeUpdate(update);
    }

    public void switchPlaylists(){
        playlistsTable.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override 
        public void handle(MouseEvent event) {
            Playlist playlist = playlistsTable.getSelectionModel().getSelectedItem();
            String selected = playlist.getName();
            playlistLabel.setText(selected);
            searchTable.setVisible(false);
        
        }});
    }
    
    public void setPlaylistType(){
        String newType="Y";
        String type = pType.getValue().toString();
        String playlistName = playlistLabel.getText();
        if(type.equals("Private")){
           newType="Y";
        }         
        else{
           newType="N"; 
        }    
        System.out.println("Type set to: " +newType);
        db.makeUpdate("UPDATE PLAYLIST WHERE PLAYLISTNAME='"+playlistName+"'SET TYPE='"+newType+"'"); 
 
    }
    
    public void updateList(){
       //playlistsTable = new TableView();
        try {
            ResultSet rs = db.makeQuery("SELECT * FROM PLAYLIST WHERE PLAYLISTOWNER ='"+user+"'");
                while (rs.next()){
                    playlistList.add(new Playlist(
                       rs.getString("PLAYLISTNAME")                    
                    ));
            System.out.println(playlistList);   
            playlistsTable.getSelectionModel().clearSelection();
            playlistsTable.setItems(this.playlistList);
            playlistsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }
        }
        catch(Exception e){
        }
    }
    
    public void displaySongs() {
        try {

            database db = new database();
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS");

            while (rs.next()) {
                songList.add(new Songs(
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION")
                ));

                searchTable.setItems(this.songList);
                searchTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }        
    @FXML
    public void searchSongs(){
        playlistLabel.setText("");
        searchTable.setVisible(true);
        displaySongs();
        FilteredList<Songs> filteredData = new FilteredList<>(songList, e -> true);
        String search = searchBar.getText();

        filteredData.setPredicate((Predicate<? super Songs>) songs -> {
            String filtered = search.toLowerCase();
            if (songs.getSongArtist().toLowerCase().contains(filtered)) {
               return true; 
            }
               return false; 
            });
            
        SortedList<Songs> songs = new SortedList<>(filteredData);
        songs.comparatorProperty().bind(searchTable.comparatorProperty());
        searchTable.setItems(songs);
    }
    
    public String getUsername(){ 
        String temp = "2"; 
        return temp;
    }
    
}
