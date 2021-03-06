/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.logic;
import Master.Working.Common.database;
import Master.Working.player.logic.Tracks;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
/**
 *
 * @author Samad
 */
public class RadioController implements Initializable {
    @FXML
    private Pane radioPane;
    @FXML
    private TableView<Tracks> table;
    @FXML
    private TableView<Tracks> radioTable;
    @FXML
    private TableColumn IDCol;
    @FXML
    private TableColumn trackNameCol;
    @FXML
    private TableColumn artistCol;
    @FXML
    private TableColumn timeCol;
    @FXML
    private TableColumn genreCol;
    @FXML
    private TableColumn radioIDCol;
    @FXML
    private TableColumn radioTrackNameCol;
    @FXML
    private TableColumn radioArtistCol;
    @FXML
    private Button saveAsPlaylist;
    @FXML
    private Button viewRadioButton;
    @FXML
    private Label currentGenre;
    @FXML
    private Label playlistSavedLabel;
    @FXML
    private Label noSongsLabel;
    @FXML
    private Label noSubLabel;
    @FXML
    private TextField playlistNameField;
    @FXML
    private TextField searchField;
    
    
    private final ObservableList<Tracks> data = FXCollections.observableArrayList();
    private ObservableList<Tracks> radioData = FXCollections.observableArrayList();
   
    database db = new database();
    private String radioGenre = "";
    private String radioArtist = ""; 
    private String radioTrack = ""; 
    private String radioGenre2 = "empty";
    private String radioArtist2 = "empty"; 
    private String radioTrack2 = "empty"; 
    private boolean radioStatus = false;
    private int lastID = 0;
    private int trackID = 0;
    private int ID = 9999;
    private int isSub = 0;
    private int defaultNum = 0;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.ID = 4;//temporary for individual use.

        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        trackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        artistCol.setCellValueFactory(new PropertyValueFactory("artist"));
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        genreCol.setCellValueFactory(new PropertyValueFactory("genre"));

        
        updateTable();
        
        radioIDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        radioTrackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        radioArtistCol.setCellValueFactory(new PropertyValueFactory("artist"));
    }
    
    @FXML
    private void savePlaylist(ActionEvent event) throws SQLException { 
        noSongsLabel.setVisible(false);
        playlistSavedLabel.setVisible(false);
        defaultNum++;
        if (radioData.isEmpty()) {
            noSongsLabel.setVisible(true);
        }
        else {
       String playlistName = playlistNameField.getText();
       if (playlistName.isEmpty()) {
           playlistName = "Radio " + defaultNum; //If playlist name text box is empty
       }
       int playlistUser = getUser();
       try (Connection conn1 = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
                PreparedStatement ps1=conn1.prepareStatement("INSERT INTO PLAYLIST (PLAYLISTOWNER, PLAYLISTNAME, PRIVATE) VALUES(?,?,?)");
                PreparedStatement ps2=conn1.prepareStatement("INSERT INTO PLAYLISTTRACK (PLAYLIST,TRACK) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
                ps1.setInt(1, playlistUser);
                ps1.setString(2, playlistName);
                ps1.setString(3, "N"); //Default
                ps1.executeUpdate(); 
                ResultSet rs = ps1.getGeneratedKeys();
                while (rs.next()) {
                        lastID = rs.getInt(1); 
                }
                ps1.close();
                for (Tracks item : radioTable.getItems()) {
                        trackID = Integer.parseInt(item.getID());
                        System.out.println(lastID + ", " + trackID);
                        ps2.setInt(1, lastID);
                        ps2.setInt(2, trackID);
                        ps2.addBatch();
                    }
                ps2.executeBatch(); 
                ps2.close();
                conn1.close();
       } catch (Exception e2) {
            System.err.println(e2);
            }
        playlistSavedLabel.setText("Playlist " + lastID + " Saved.");
        playlistSavedLabel.setVisible(true);
        System.out.println("Playlist has been saved " + lastID); 
        }
    }
    
    
    //private String getUsername(){
    //    return "mas36"; //Temporary
    //}
    
   public void setUser(int pass)
    {
        this.ID=pass;
    }
    public int getUser()
    {
        return this.ID;
    }
    
    //private int getUserID(){
    //    return 4; //Temporary
    //}
    
    @FXML
    private void viewRadioPane(ActionEvent event2) {
        noSubLabel.setVisible(false);
        try {
            ResultSet rs3 = db.makeQuery("SELECT PREMIUM FROM SUBSCRIPTION WHERE USERID = '"+ID+"'");
            isSub = rs3.getInt(1);
            //System.out.println(isSub);
            rs3.close();
         }
            catch (Exception e2) {
            System.err.println(e2);
         }
        noSongsLabel.setVisible(false);
        playlistSavedLabel.setVisible(false);
        radioPane.setStyle("-fx-background-color: #383838");
        if (radioStatus == false) {
            radioPane.setVisible(true);
            radioStatus = true;
            viewRadioButton.setText("Close Radio View");
            if (isSub == 0) {
                radioTable.setVisible(false);
                playlistNameField.setVisible(false);
                currentGenre.setVisible(false);
                saveAsPlaylist.setVisible(false);
                noSubLabel.setVisible(true);
            }          
        }
        else {
            viewRadioButton.setText("View Radio Channel");
            radioPane.setVisible(false);
            radioStatus = false;
            playlistSavedLabel.setVisible(false);
        }
    }
    
    public void createRadio(String searchTermTrack, String searchTermArtist, String searchTermGenre) {
        radioData.removeAll(radioData);
        radioTable.setItems(radioData);


        if (!searchTermGenre.equals("empty")) {
            radioGenre = searchTermGenre.toUpperCase();
        }
        else if (!searchTermArtist.equals("empty")) {
            ////////////Fix Search Term for Query/////////////////////////////////
            String[] termSplit = searchTermArtist.split(" ");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < termSplit.length-1; i++) {
                termSplit[i] = termSplit[i] + " ";
            }
            for (String word : termSplit) {
                sb.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            }  
            /////////////////////////////////////////////////////////////////////
            String fixedTerm = sb.toString();
            radioArtist = fixedTerm;
            //System.out.println(radioArtist);
            try {
                ResultSet rs2 = db.makeQuery("SELECT GENRE FROM TRACKS WHERE ARTIST = '"+radioArtist+"'");
                radioGenre = rs2.getString("GENRE");
                rs2.close();
            }
            catch (Exception e2) {
            System.err.println(e2);
            }
        }
        else if (!searchTermTrack.equals("empty")) {
            ////////////Fix Search Term for Query/////////////////////////////////
            String[] termSplit = searchTermTrack.split(" ");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < termSplit.length-1; i++) {
                termSplit[i] = termSplit[i] + " ";
            }
            for (String word : termSplit) {
                sb.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            }  
            ////////////////////////////////////////////////////////////////////
            String fixedTerm = sb.toString();
            radioTrack = fixedTerm;
            //System.out.println(radioTrack);
            try {
                ResultSet rs2 = db.makeQuery("SELECT GENRE,ARTIST FROM TRACKS WHERE TRACKNAME = '"+radioTrack+"'");
                radioArtist = rs2.getString("ARTIST");
                radioGenre = rs2.getString("GENRE");
                rs2.close();
            }
            catch (Exception e2) {
            System.err.println(e2);
            }
           
        }
        
       currentGenre.setText(radioGenre);
       try {
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS WHERE GENRE = '"+radioGenre+"' AND ARTIST != '"+radioArtist+"' ORDER BY RANDOM() LIMIT 10");
            while (rs.next()) {
                radioData.add(new Tracks(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION"),
                        rs.getString("GENRE")
                       
                ));
                radioTable.setItems(radioData);
                radioTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }

        } catch (Exception e2) {
            System.err.println(e2);
        }
       
    }
    
    @FXML
    private void searchFunction(KeyEvent event) {

        FilteredList<Tracks> filteredData = new FilteredList<>(data, e -> true);

        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Tracks>) tracks -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tracks.getID().contains(newValue)) {
                        return true;
                    } else if (tracks.getTrackName().toLowerCase().contains(lowerCaseFilter)) {
                        radioTrack2 = tracks.getTrackName();
                        return true;

                    } else if (tracks.getArtist().toLowerCase().contains(lowerCaseFilter)) {
                        radioArtist2 = tracks.getArtist();
                        return true;

                    } else if (tracks.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                        radioGenre2 = tracks.getGenre();
                        return true;
                    }
                    return false;
                    
                });
                createRadio(radioTrack2,radioArtist2,radioGenre2);
            });
        });

        SortedList<Tracks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        
        
    }

    public void updateTable() {
        try {
            database db = new database();
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS");
            while (rs.next()) {
                data.add(new Tracks(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION"),
                        rs.getString("GENRE")
                       
                ));

                table.setItems(this.data);
                table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }
}
