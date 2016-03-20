package Master.Working.player.gui;

import Master.Working.Common.database;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author prarthana
 */
public class PlaybarController implements Initializable {

    @FXML
    private Slider slider;
    @FXML
    private ImageView onPlay;
    @FXML
    private ImageView onRestart;
    @FXML
    private Label duration;
    @FXML
    private Label totalDuration;
    @FXML
    private ComboBox nowPlayingMenu;

    private String status = "";
    private List<String> tracksList = new ArrayList<String>();
    private Iterator<String> itr;
    private MediaPlayer player;
    private Duration currentDuration;
    private static final double MIN_CHANGE = 0.5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NowPlaying play = new NowPlaying();
        nowPlayingMenu.getItems().addAll(play.getNowPlaying());

        ObservableList<String> items = nowPlayingMenu.getItems();
        for (String item : items) {
            tracksList.add(item + ".mp3");
        }

        System.out.println(tracksList);
    }

    @FXML
    private void onRemove(ActionEvent event) {
        Object o = nowPlayingMenu.getSelectionModel().getSelectedItem();
        NowPlaying play = new NowPlaying();
        play.removeNowPlaying(o);
        nowPlayingMenu.getItems().remove(o);

    }

    @FXML
    private void onPlay(ActionEvent event) {

        if (!status.equals(
                "Playing")) {
            itr = tracksList.iterator();
            play(itr.next());

        }
    }

    private void play(String mediaFile) {

        //Resumes
        if (status.equals("Paused")) {
            player.seek(currentDuration);
            player.play();
            status = "Playing";
        } else {

            Media media;
            try {
                URL resource = getClass().getResource(mediaFile);
                media = new Media(resource.toString());

            } catch (Exception e) {

                URL resource = getClass().getResource("empty.mp3");
                media = new Media(resource.toString());

            }
            player = new MediaPlayer(media);

            player.play();

            status = "Playing";

            getDuration();
            getTotalTrackTime();
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
        Object o = nowPlayingMenu.getItems().get(0);
        tracksList.remove(0);
        NowPlaying play = new NowPlaying();
        play.removeNowPlaying(o);
        nowPlayingMenu.getItems().remove(0);

       // nowPlayingMenu.getItems().remove(0);
        itr = tracksList.iterator();
        return itr;

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

    public void getTotalTrackTime() {

        try {
            String trackPlaying = tracksList.get(0);
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

}
