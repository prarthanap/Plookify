/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.logic;

import Master.Working.Common.database;
import Master.Working.playlist.gui.Playlist;
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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class PlaylistController implements Initializable {

    public int user=9999;
    public int currentPlaylist= 1 ;
    @FXML
    public TableView<Songs> table;
    @FXML
    public TableColumn<?, ?> tSong;
    @FXML
    public TableColumn<?, ?> tArtist;
    @FXML
    public TableColumn<?, ?> tDuration;
    @FXML
    public TableView<Songs> searchTable;
    @FXML
    public TableColumn<?, ?> tsSong;
    @FXML
    public TableColumn<?, ?> tsArtist;
    @FXML
    public TableColumn<?, ?> tsDuration;
    @FXML
    public TableView<Playlist> playlistsTable;
    @FXML
    public TableColumn<?, ?> tPlaylists;
    @FXML
    public TextField newPlaylistName;
    @FXML
    public Button createPlaylistBTN;
    @FXML
    public Button search;
    @FXML
    public TextField searchBar;
    @FXML
    public Label label;
    @FXML
    public ComboBox<String> pType;
    @FXML
    public Label playlistLabel;
    @FXML
    public Button nowPlayingBTN;
    @FXML
    public Button removeSongBTN;
    @FXML
    public Button renamePlaylist;
    /**
     * Initializes the controller class.
     */
    database db = new database();
    ObservableList<Playlist> playlistList = FXCollections.observableArrayList();
    ObservableList<Songs> playlistSongs = FXCollections.observableArrayList();
    ObservableList<Songs> songList = FXCollections.observableArrayList();

    @FXML
    public Pane leftPane;
    @FXML
    public Pane rightPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
       
    public void initP()
    {
        tSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        tDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
        
        tsSong.setCellValueFactory(new PropertyValueFactory("songName"));
        tsArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        tsDuration.setCellValueFactory(new PropertyValueFactory("songDur"));
            
        tPlaylists.setCellValueFactory(new PropertyValueFactory("name"));
         
        pType.getItems().addAll("Private","Friend");
        //setID(2);
        System.out.println(user);
        updateList();
    }

    @FXML
    public void switchPlaylists() {
        
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
                Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE, null, ex);
            }
            updatePlaylist();
            }
        });
        
    }

    @FXML
    public void createPlaylist() {
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
            rs.close();            
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
            rs.close();
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
       updatePlaylist();
        
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
    public void changePlaylistType(ActionEvent event) {
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
        ArrayList<Integer> songIDs = new ArrayList<>();
        ArrayList<String> songNames = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            PreparedStatement ps=conn.prepareStatement("SELECT TRACK FROM PLAYLISTTRACK WHERE PLAYLIST =?");
            PreparedStatement ps2=conn.prepareStatement("SELECT TRACKNAME FROM TRACKS WHERE TRACKID=?");
            PreparedStatement ps3=conn.prepareStatement("INSERT INTO NOWPLAYING (TRACKNAME) VALUES(?)");
            ps.setInt(1, currentPlaylist);
            ps.executeQuery(); 
            ResultSet rs = ps.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for(int i=1; i<=columnsNumber; i++){
                    songIDs.add(rs.getInt(i));
                }   
            }
            ps.close();
            for(int x = 0; x < songIDs.size(); x++) {  
                int songID =songIDs.get(x);   
                ps2.setInt(1, songID);
                ps2.executeQuery();
                ResultSet rs2 = ps2.getGeneratedKeys();
                songNames.add(rs.getString("TRACKNAME"));
            }
            ps2.close();
            for(int y = 0; y < songNames.size(); y++){  
                String name = songNames.get(y);
                ps3.setString(1, name);
                ps3.executeUpdate();
                ResultSet rs3 = ps3.getGeneratedKeys();
                
            }
            ps3.close();
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
    
    public void setUser(int id){
        this.user = id;
    }
    public int getUser(){
        return this.user;
    }
    
    public void setPlaylistID(int id){
        currentPlaylist = id;
    }
    public Pane getPane(String p)
    {
        if (p.equals("l"))
        {
            return leftPane;
        }
        else
        {
            return rightPane;
        }
    }
}
