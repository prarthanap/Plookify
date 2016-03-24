/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.logic;

import Master.Working.Common.database;
import Master.Working.playlist.gui.MainScreenController;
import Master.Working.playlist.gui.Playlist;
import Master.Working.playlist.gui.Playlist;
import Master.Working.playlist.gui.Songs;
import Master.Working.playlist.gui.Songs;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class PlaylistController implements Initializable {

    @FXML
    private TableView<Songs> table;
    @FXML
    private TableColumn<?, ?> tSong;
    @FXML
    private TableColumn<?, ?> tArtist;
    @FXML
    private TableColumn<?, ?> tDuration;
    @FXML
    private TableView<Songs> searchTable;
    @FXML
    private TableColumn<?, ?> tsSong;
    @FXML
    private TableColumn<?, ?> tsArtist;
    @FXML
    private TableColumn<?, ?> tsDuration;
    @FXML
    private TableView<Playlist> playlistsTable;
    @FXML
    private TableColumn<?, ?> tPlaylists;
    @FXML
    private TextField newPlaylistName;
    @FXML
    private Button createPlaylistBTN;
    @FXML
    private Button search;
    @FXML
    private TextField searchBar;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> pType;
    @FXML
    private Label playlistLabel;
    @FXML
    private Button nowPlayingBTN;
    /**
     * Initializes the controller class.
     */
    database db = new database();
    ObservableList<Playlist> playlistList = FXCollections.observableArrayList();
    ObservableList<Songs> playlistSongs = FXCollections.observableArrayList();
    ObservableList<Songs> songList = FXCollections.observableArrayList();
    int user;
    int currentPlaylist= 1 ;
    @FXML
    private Button removeSongBTN;
    @FXML
    private Button renamePlaylist;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        tDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
        
        tsSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tsArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        tsDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
            
        tPlaylists.setCellValueFactory(new PropertyValueFactory("name"));
         
        pType.getItems().addAll("Private","Friend");
        setID(2);
        updateList();
    }
          

    @FXML
    private void switchPlaylists() {
        
        playlistsTable.setOnMousePressed((MouseEvent event) -> {
            Playlist playlist = playlistsTable.getSelectionModel().getSelectedItem();
            int currentPlaylist1 = Integer.valueOf(playlist.getId());
            setPlaylistID(currentPlaylist1);
            if (event.getClickCount() == 2){
                hideSearch();
            try{
                setPlaylistID(currentPlaylist1);
                String selected = playlist.getName();
                playlistLabel.setText(selected);
                String type = playlist.getType();
                if(type.equals("N"))
                    pType.setValue("Friend");
                else
                    pType.setValue("Private");                      
                System.out.println("playlist ID " + currentPlaylist1);
                updatePlaylist();
                }
            
            catch (Exception ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            updatePlaylist();
            }
        });
        
    }

    @FXML
    private void createPlaylist() {
        String playlistName = newPlaylistName.getText();
        Playlist newPlaylist = new Playlist(playlistName);
        String type = "Y";
        pType.setValue("Private");
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
        updateList();
    }
    public void updateList(){
        try {
            playlistList.clear();
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
    public void renamePlaylist(){
        Playlist playlist = playlistsTable.getSelectionModel().getSelectedItem();
        String playlistID = playlist.getId();
        
        String newName =newPlaylistName.getText();
        
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            PreparedStatement ps=conn.prepareStatement("UPDATE PLAYLIST SET PLAYLISTNAME=? WHERE PLAYLISTID=?");
            ps.setString(1, newName);
            ps.setInt(2, currentPlaylist);
            ps.executeUpdate(); 
            ResultSet rs = ps.getGeneratedKeys();
            ps.close();
            conn.close();
       } 
       catch(Exception e){      
       }   
       updateList();
        
    }
    @FXML
    public void removeSong(){
        ObservableList<?> songSelected,allSongs;
        allSongs = table.getItems();
        songSelected = table.getSelectionModel().getSelectedItems();
        Songs song = table.getSelectionModel().getSelectedItem();
        String songID = song.getSongID(); 
        songSelected.forEach(allSongs::remove);
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
                    PreparedStatement ps=conn.prepareStatement("DELETE FROM PLAYLISTTRACK WHERE PLAYLIST=? AND TRACK=?");
                    ps.setInt(1, currentPlaylist);
                    ps.setString(2, songID);
                    ps.executeUpdate();
                    ps.close();
                    conn.close();
                }
                catch (Exception e2) {
                    System.err.println(e2);
                }
    }
    
    @FXML
    public void addToPlaylist(){
        searchTable.setOnMousePressed((MouseEvent event) -> {
            if (event.getClickCount() == 2){
                Songs song = searchTable.getSelectionModel().getSelectedItem();
                String songID = song.getSongID(); 
                System.out.println(songID);
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
            }
        });
    }
    
    public ArrayList<String> getSavedSongs(){
        ArrayList<String> songIDs = new ArrayList<>();
        songIDs.clear();
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
    
    public void displaySongs() {
        try {
            songList.clear();
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
    public void searchSongs(ActionEvent event) {
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

    @FXML
    private void changePlaylistType(ActionEvent event) {
        String newType;
        if(pType.getValue().equals("Private")){
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
    
    @FXML
    public void addToNowPlaying(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            PreparedStatement ps=conn.prepareStatement("SELECT TRACK FROM PLAYLISTTRACK WHERE PLAYLIST =?");
            PreparedStatement ps2=conn.prepareStatement("INSERT INTO NOWPLAYING (TRACKNAME) VALUES(?)");
            ps.setInt(1, currentPlaylist);
            ps.executeQuery(); 
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                String name =rs.toString();
                ps2.setString(1,name);
                ps2.executeUpdate();
            }
            ps.close();
            ps2.close();
            conn.close();
       } 
        catch(Exception e){  
        }
    }
    
     public void hidePlaylist(){
        searchTable.setVisible(true);
        playlistLabel.setVisible(false);
        pType.setVisible(false);
        nowPlayingBTN.setVisible(false);
        label.setVisible(false);
    }
    
    public void hideSearch(){
        searchTable.setVisible(false);
        playlistLabel.setVisible(true);
        pType.setVisible(true);
        nowPlayingBTN.setVisible(true);
        label.setVisible(true);
    }
    
    public void setID(int id){
        user = id;
    }
    
    public void setPlaylistID(int id){
        currentPlaylist = id;
    }
}
