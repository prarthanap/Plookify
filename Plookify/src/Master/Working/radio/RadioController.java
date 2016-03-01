/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio;
import Master.Working.Common.database;
import Master.Working.player.gui.Tracks;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author Samad
 */
public class RadioController implements Initializable {
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
    private TableColumn albumCol;
    @FXML
    private TableColumn radioIDCol;
    @FXML
    private TableColumn radioTrackNameCol;
    @FXML
    private TableColumn radioArtistCol;
    @FXML
    private Label currentGenre;
    @FXML
    private TextField searchField;
    
    private SortedList<Tracks> listForRadio;
    private final ObservableList<Tracks> data = FXCollections.observableArrayList();
    private ObservableList<Tracks> radioData = FXCollections.observableArrayList();
   
    database db = new database();
    private String searchTerm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        trackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        artistCol.setCellValueFactory(new PropertyValueFactory("artist"));
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        genreCol.setCellValueFactory(new PropertyValueFactory("genre"));
        albumCol.setCellValueFactory(new PropertyValueFactory("album"));

        updateTable();
        
        radioIDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        radioTrackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        radioArtistCol.setCellValueFactory(new PropertyValueFactory("artist"));
        
        createRadio();
    }
    
    public void createRadio() {
        String radioGenre = "ROCK";//Temporary
        currentGenre.setText(radioGenre);
        
       try {
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS WHERE GENRE = '"+radioGenre+"' ORDER BY RANDOM() LIMIT 10");
            while (rs.next()) {
                radioData.add(new Tracks(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION"),
                        rs.getString("GENRE"),
                        rs.getString("ALBUM")
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
                        return true;

                    } else if (tracks.getArtist().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    } else if (tracks.getGenre().toLowerCase().contains(lowerCaseFilter)) {
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
                        rs.getString("GENRE"),
                        rs.getString("ALBUM")
                ));

                table.setItems(this.data);
                table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }
}
