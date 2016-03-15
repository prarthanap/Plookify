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
    private TextField playlistNameField;
    @FXML
    private TextField searchField;
    
    private final ObservableList<Tracks> data = FXCollections.observableArrayList();
    private ObservableList<Tracks> radioData = FXCollections.observableArrayList();
   
    database db = new database();
    private String radioGenre = "";
    private String radioArtist = ""; 
    private String radioTrack = ""; 
    private boolean radioStatus = false;
    private int lastID = 0;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void savePlaylist(ActionEvent event) {
        //System.out.println("TO DO...");
        //String playlistName = playlistNameField.getText();
        String playlistUser = getUsername();
       try (Connection conn1 = DriverManager.getConnection("jdbc:sqlite:data.db")) {
                PreparedStatement ps1=conn1.prepareStatement("INSERT INTO PLAYLIST (PLAYLISTOWNER) VALUES(?)");
                ps1.setString(1, playlistUser);
                ps1.execute();
                System.out.println("Update Made");
                ps1.close();
                conn1.close();
            lastID = db.makeQuery("SELECT MAX(PLAYLISTID) FROM PLAYLIST").getInt(1);
            radioTable.getItems().stream().map((item) -> "INSERT into PLAYLISTTRACK (PLAYLIST,TRACK) VALUES('"+lastID+"','"+item.getID()+"')").forEach((updatePTRACK) -> {
                db.makeUpdate(updatePTRACK);
            });
            db.conClose();
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Playlist has been saved " + lastID);

    }
    
    
    private String getUsername(){
        return "mas36"; //Temporary
    }
    
    @FXML
    private void viewRadioPane(ActionEvent event2) {
        radioPane.setStyle("-fx-background-color: #383838");
        if (radioStatus == false) {
            radioPane.setVisible(true);
            radioStatus = true;
        }
        else {
            radioPane.setVisible(false);
            radioStatus = false;
        }
    }
    
    public void createRadio(String searchTermTrack, String searchTermArtist, String searchTermGenre) {
        radioData.removeAll(radioData);
        radioTable.setItems(radioData);


        if (!searchTermGenre.equals("empty")) {
            radioGenre = searchTermGenre.toUpperCase();
        }
        else if (!searchTermArtist.equals("empty")) {
            String[] termSplit = searchTermArtist.split(" ");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < termSplit.length-1; i++) {
                termSplit[i] = termSplit[i] + " ";
            }
            for (String word : termSplit) {
                sb.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            }  
            
            String fixedTerm = sb.toString();
            radioArtist = fixedTerm;
            System.out.println(radioArtist);
            try {
                ResultSet rs2 = db.makeQuery("SELECT GENRE FROM TRACKS WHERE ARTIST = '"+radioArtist+"'");
                radioGenre = rs2.getString("GENRE");
            }
            catch (Exception e2) {
            System.err.println(e2);
            }
        }
        else if (!searchTermTrack.equals("empty")) {
            String[] termSplit = searchTermTrack.split(" ");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < termSplit.length-1; i++) {
                termSplit[i] = termSplit[i] + " ";
            }
            for (String word : termSplit) {
                sb.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            }  
            String fixedTerm = sb.toString();
            radioTrack = fixedTerm;
            System.out.println(radioTrack);
            try {
                ResultSet rs2 = db.makeQuery("SELECT GENRE,ARTIST FROM TRACKS WHERE TRACKNAME = '"+radioTrack+"'");
                radioArtist = rs2.getString("ARTIST");
                radioGenre = rs2.getString("GENRE");
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
                        createRadio(tracks.getTrackName(), "empty", "empty");
                        return true;

                    } else if (tracks.getArtist().toLowerCase().contains(lowerCaseFilter)) {
                        createRadio("empty", tracks.getArtist(), "empty");
                        return true;

                    } else if (tracks.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                        createRadio("empty", "empty", tracks.getGenre());
                        return true;
                    }
                    return false;
                    
                });
            });
        });

        SortedList<Tracks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        
        
    }

    public void updateTable() {
        try {
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
