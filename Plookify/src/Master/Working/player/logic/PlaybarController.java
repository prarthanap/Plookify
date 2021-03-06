package Master.Working.player.logic;

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
    private Label duration;
    @FXML
    private Label totalDuration;
    @FXML
    private Slider slider;
    @FXML
    private ComboBox nowPlayingMenu;

    private String status = "";
    private List<String> tracksList = new ArrayList<String>();
    private Iterator<String> itr;
    private MediaPlayer player;
    private Duration currentDuration;
    private static final double CHANGE = 0.3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NowPlaying play = new NowPlaying();
        nowPlayingMenu.getItems().clear();
        tracksList.clear();
        nowPlayingMenu.getItems().addAll(play.getNowPlaying());

        ObservableList<String> items = nowPlayingMenu.getItems();
        for (String item : items) {
            tracksList.add(item + ".mp3");
        }

        System.out.println(tracksList);

        nowPlayingMenu.getSelectionModel().selectFirst();
    }

    @FXML
    private void onRemove(ActionEvent event) {

        NowPlaying play = new NowPlaying();

        if (nowPlayingMenu.getSelectionModel().isSelected(0)) {
            Duration end = player.getStopTime();
            player.seek(end);
        } else {
            Object o = nowPlayingMenu.getSelectionModel().getSelectedItem();

            tracksList.remove(o + ".mp3");

            play.removeNowPlaying(o);
            nowPlayingMenu.getItems().remove(o);
        }
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
                URL resource = getClass().getResource("/Master/Working/player/logic/Tracks/" + mediaFile);
                media = new Media(resource.toString());

            } catch (Exception e) {

                URL resource = getClass().getResource("/Master/Working/player/logic/Tracks/empty.mp3");
                media = new Media(resource.toString());

            }
            player = new MediaPlayer(media);

            player.play();

            nowPlayingMenu.getSelectionModel().selectFirst();

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

        System.out.println(tracksList);

        NowPlaying play = new NowPlaying();
        play.removeNowPlaying(o);
        nowPlayingMenu.getItems().remove(0);
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

        player.totalDurationProperty().addListener((observ, oldTime, newTime) -> slider.setMax(newTime.toSeconds()));
        slider.valueChangingProperty().addListener((observ, oldChanging, newChanging) -> {
            if (!newChanging) {
                player.seek(Duration.seconds(slider.getValue()));
            }
        });

        slider.valueProperty().addListener((observ, oldValue, newValue) -> {
            if (!slider.isValueChanging()) {
                double currentTime = player.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > CHANGE) {
                    player.seek(Duration.seconds(newValue.doubleValue()));
                }
            }
        });

        player.currentTimeProperty().addListener((observ, oldDur, newDur) -> {
            if (!slider.isValueChanging()) {
                slider.setValue(newDur.toSeconds());

                duration.setText(formatDuration(newDur));

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
