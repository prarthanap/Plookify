/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.gui;

import Master.Working.Common.database;
import Master.Working.player.logic.Tracks;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.input.KeyEvent;
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
    @FXML
    private Button addButton;
    
    database db = new database();
    ObservableList<Playlist> playlistList = FXCollections.observableArrayList();
    ObservableList<Songs> playlistSongs = FXCollections.observableArrayList();
    ObservableList<Songs> songList = FXCollections.observableArrayList();
    final ObservableList options = FXCollections.observableArrayList();
    //String user = getUsername();
    int user=getID();
    int currentPlaylist= 1 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateList();
        updatePlaylist();
        //getSavedSongs();
        tSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        tDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
        
        stSong.setCellValueFactory(new PropertyValueFactory("songName"));
        stArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        stDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
            
        tPlaylists.setCellValueFactory(new PropertyValueFactory("name"));
            
        pType.getItems().addAll("Private","Friend");
        pType.setValue("Private");
        
            
        }
    
    @FXML
    public void createPlaylist(){
        String playlistName = newPlaylistName.getText();
        Playlist newPlaylist = new Playlist(playlistName);
        String type = "Y";
        pType.setValue("Private");
        playlistsTable.getItems().add(newPlaylist);
        try (Connection conn1 = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            PreparedStatement ps1=conn1.prepareStatement("INSERT INTO PLAYLIST (PLAYLISTOWNER, PLAYLISTNAME, PRIVATE) VALUES(?,?,?)");
            ps1.setInt(1, user);
            ps1.setString(2, playlistName);
            ps1.setString(3, type);
            ps1.executeUpdate(); 
            ResultSet rs = ps1.getGeneratedKeys();
            ps1.close();
            conn1.close();
       } 
       catch (Exception e2) {
            System.err.println(e2);
            }
    }
    
    @FXML
    public void switchPlaylists(){
        hideSearch();
        playlistsTable.setOnMousePressed((MouseEvent event) -> {
            try {
                Playlist playlist = playlistsTable.getSelectionModel().getSelectedItem();
                int currentPlaylist1 = Integer.valueOf(playlist.getId());
                setPlaylistID(currentPlaylist1);
                String selected = playlist.getName();
                playlistLabel.setText(selected);
                String type = playlist.getType();
                if(type.equals("N"))
                    type="Friend";
                else
                    type="Private";                      
                pType.setValue(type);
                System.out.println("playlist ID " + currentPlaylist1);
            }catch (Exception ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            updatePlaylist();
        });
    }
    public void changePlaylistType(){
        String newType;
        if(pType.getValue().toString().equals("Private")){
           newType="Y";
        }         
        else{
           newType="N"; 
        }    
        System.out.println("Type set to: " +newType);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            PreparedStatement ps=conn.prepareStatement("UPDATE PLAYLIST SET PRIVATE=? WHERE PLAYLISTID =?");
            ps.setString(1, newType);
            ps.setInt(2, currentPlaylist);
            ps.executeUpdate(); 
            ResultSet rs = ps.getGeneratedKeys();
            ps.close();
            conn.close();
       } 
       catch(Exception e){
           
       }    
    }
    
    public void updateList(){
        try {
            ResultSet rs = db.makeQuery("SELECT * FROM PLAYLIST WHERE PLAYLISTOWNER ='"+user+"'");
                while (rs.next()){
                    playlistList.add(new Playlist(
                       rs.getString("PLAYLISTID"),
                       rs.getString("PLAYLISTNAME"),
                       rs.getString("PRIVATE")
                    ));  
            playlistsTable.getSelectionModel().clearSelection();
            playlistsTable.setItems(this.playlistList);
            playlistsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }   
        }
        catch(Exception e){
        }
    }
    public ArrayList<String> getSavedSongs(){
        ArrayList<String> songIDs = new ArrayList<>();
        try{  
            ResultSet rs = db.makeQuery("SELECT TRACK FROM PLAYLISTTRACK WHERE PLAYLIST = '"+currentPlaylist+"'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i=1; i<=columnsNumber; i++){
                    songIDs.add(rs.getString(i));
                }   
            }
        }
        catch(Exception e){
        } 
       return songIDs;
    }
    
    public void printArraylist(){
        ArrayList<String> songIDs = getSavedSongs();
        for (int i = 0; i < songIDs.size(); i++) {
            System.out.println("song ID " +songIDs.get(i));
        }
        
        
    }
    
    public void updatePlaylist(){
        ArrayList<String> songIDs = getSavedSongs();
        playlistSongs.clear();
        try{ 
        for(int i = 0; i < songIDs.size(); i++) {  
            String song =songIDs.get(i);
        ResultSet rs = db.makeQuery("SELECT * FROM TRACKS WHERE TRACKID ='"+song+"'"); // TEMP
            while (rs.next()){
                    playlistSongs.add(new Songs(
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION")
                    ));
            }
            table.getSelectionModel().clearSelection();     
            table.setItems(this.playlistSongs);
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
             }
         }
         catch(Exception e){
         }
        
     }
    
    @FXML
    public void addToPlaylist(){
        searchTable.setOnMousePressed((MouseEvent event) -> {
        Songs song = searchTable.getSelectionModel().getSelectedItem();
        String songID = song.getSongID(); 
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            PreparedStatement ps=conn.prepareStatement("INSERT INTO PLAYLISTTRACK (PLAYLIST,TRACK) VALUES(?,?)");
            ps.setInt(1, currentPlaylist);
            ps.setString(2, songID);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            ps.close();
            conn.close();
        }
        catch (Exception e2) {
            System.err.println(e2);
        }
        });
    }
    
    public void displaySongs() {
        try {
            database db = new database();
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS");

            while (rs.next()) {
                songList.add(new Songs(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION")
                ));

                searchTable.setItems(this.songList);
                searchTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }

        } 
        catch (Exception e2) {
            System.err.println(e2);
        }
    }        
    @FXML
    public void searchSongs(){
        playlistLabel.setText("");
        hidePlaylist();
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
    
    public void hidePlaylist(){
        searchTable.setVisible(true);
        playlistLabel.setVisible(false);
        addButton.setVisible(true);
        label.setText("Search Results");
    }
    
    public void hideSearch(){
        searchTable.setVisible(false);
        playlistLabel.setVisible(true);
        addButton.setVisible(false); 
    }
    
//    public void changeCombo(){
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
//        PreparedStatement ps=conn.prepareStatement("SELECT PLAYLISTNAME FROM PLAYLIST WHERE PLAYLISTOWNER=?)");
//        ps.setInt(1,user);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//        options.add(rs.getString("PLAYLISTNAME"));  
//        }
//        rs.close();
//        ps.close();
//        conn.close();
//        }
//        catch(Exception e){
//        }
//    }
    
    public int getID(){
        return 2;
    }
    
    public int getID(int id){
        return id;
    }
    
    public void setPlaylistID(int id){
        currentPlaylist = id;
    }
}

