/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.player.gui;

import Master.Working.Common.database;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author prarthana
 */
public class PlayerController implements Initializable {

    @FXML
    private TableView<Tracks> table;
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
    private Button play;
    @FXML
    private Slider slider;
    @FXML
    private TextField searchField;
    @FXML
    private Label duration;
    @FXML
    ComboBox nowPlayingMenu;

    private MediaPlayer player;
    private List<String> list = new ArrayList<String>();
    private Iterator<String> itr;

    private final ObservableList<Tracks> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        trackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        artistCol.setCellValueFactory(new PropertyValueFactory("artist"));
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        genreCol.setCellValueFactory(new PropertyValueFactory("genre"));
        albumCol.setCellValueFactory(new PropertyValueFactory("album"));

        updateTable();

    }

    @FXML
    private void playTrack(MouseEvent event) {

    }

    @FXML
    private void onPlay(ActionEvent event) {
        for (Tracks trackSelect : table.getSelectionModel().getSelectedItems()) {

            nowPlayingMenu.getItems().addAll(trackSelect.getTrackName());
            list.add(trackSelect.getTrackName() + ".mp3");

            itr = list.iterator();

            play(itr.next());

        }

    }

    public void play(String mediaFile) {
        Media media = new Media(Paths.get("/Users/prarthana/PlzWork/src/plzwork/Tracks/" + mediaFile).toUri().toString());
        player = new MediaPlayer(media);

        player.play();
        // getDuration();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.stop();
                itr = updateItr();
                if (itr.hasNext()) {
                    play(itr.next());
                }
                return;
            }
        });
    }

    public Iterator updateItr() {
        list.remove(0);
        nowPlayingMenu.getItems().remove(0);
        itr = list.iterator();
        return itr;

    }

    @FXML
    private void onPause(ActionEvent event) {
         player.pause();
        
        
        
    }

    @FXML
    private void onRemove(ActionEvent event) {

        Object o = nowPlayingMenu.getSelectionModel().getSelectedItem();
        nowPlayingMenu.getItems().remove(o);
        list.remove(o + ".mp3");

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

            database db = new database();
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
