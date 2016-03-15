/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package Master.Working.player.gui;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author prarthana
 */
/*public class testing implements Initializable {

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
    private Button pause;
    @FXML
    private Slider slider;
    @FXML
    private TextField searchField;
    @FXML
    private Label duration;
    @FXML
    ComboBox nowPlayingMenu;
    @FXML
    private Label totalDuration;

    private static final double MIN_CHANGE = 0.0001;
    private MediaPlayer player;
    private List<String> list = new ArrayList<String>();
    private Iterator<String> itr;

    private String status = "";

    private Duration currentDuration;

    private final ObservableList<Tracks> data = FXCollections.observableArrayList();
    @FXML
    private Button restart;

    /**
     * Initializes the controller class.
     */
/*
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
    private void onPlay(ActionEvent event) {

        if (!status.equals("Paused")) {
            {

                for (Tracks trackSelect : table.getSelectionModel().getSelectedItems()) {

                    nowPlayingMenu.getItems().addAll(trackSelect.getTrackName());
                    list.add(trackSelect.getTrackName() + ".mp3");
                }
            }
        }
        if (!status.equals("Playing")) {
            itr = list.iterator();
            play(itr.next());

        }
    }

    @FXML
    private void onPause(ActionEvent event) {
        player.pause();
        status = "Paused";
        currentDuration = player.getCurrentTime();
    }

    @FXML
    private void onRestart(ActionEvent event) {
        Duration startTime = player.getStartTime();
        player.seek(startTime);
        player.play();
    }

    public void play(String mediaFile) {
        //Resumes
        if (status.equals("Paused")) {
            player.seek(currentDuration);
            player.play();
            status = "Playing";
        } else {

            Media media = new Media(Paths.get("/Users/prarthana/Documents/Software Project/NBProjects/SE23/Plookify/src/Master/Working/player/logic/Tracks/" + mediaFile).toUri().toString());
            player = new MediaPlayer(media);

            player.play();
            status = "Playing";
            getDuration();
            getTrackTime();

            player.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    player.stop();
                    status = "Stopped";
                    itr = updateItr();
                    if (itr.hasNext()) {
                        play(itr.next());
                    }
                    return;
                }
            });
        }

    }

    public Iterator updateItr() {
        list.remove(0);
        nowPlayingMenu.getItems().remove(0);
        itr = list.iterator();
        return itr;

    }

    public void getTrackTime() {

        try {
            String trackPlaying = list.get(0);
            String firstWord = trackPlaying.substring(0, trackPlaying.indexOf("."));

            database db = new database();
            ResultSet rs = db.makeQuery("SELECT * from TRACKS where trackname = '" + firstWord + "'");
            while (rs.next()) {
                String length = rs.getString(4);
                totalDuration.setText(length);
            }
        } catch (Exception e2) {
            System.err.println(e2);

        }
    }

    @FXML
    private void onRemove(ActionEvent event) {

        Object o = nowPlayingMenu.getSelectionModel().getSelectedItem();
        nowPlayingMenu.getItems().remove(o);
        list.remove(o + ".mp3");

    }

    public void getDuration() {

        player.totalDurationProperty().addListener((obs, oldDuration, newDuration) -> slider.setMax(newDuration.toSeconds()));
        slider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                player.seek(Duration.seconds(slider.getValue()));
            }
        });

        slider.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (!slider.isValueChanging()) {
                double currentTime = player.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > MIN_CHANGE) {
                    player.seek(Duration.seconds(newValue.doubleValue()));
                }
            }
        });

        player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!slider.isValueChanging()) {
                slider.setValue(newTime.toSeconds());

                duration.setText(formatDuration(newTime));

            }
        });

    }

    public static String formatDuration(Duration d) {
        long seconds = (long) d.toSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d",
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
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
*/