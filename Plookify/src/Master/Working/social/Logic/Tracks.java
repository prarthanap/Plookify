package Master.Working.social.Logic;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hamza
 */
public class Tracks {

    private final SimpleStringProperty trackName;
    private final SimpleStringProperty artist;
    private final SimpleStringProperty time;
    private final SimpleStringProperty genre;


    public Tracks(String trackName, String artist, String time, String genre) {
        this.trackName = new SimpleStringProperty(trackName);
        this.artist = new SimpleStringProperty(artist);
        this.time = new SimpleStringProperty(time);
        this.genre = new SimpleStringProperty(genre);
        
    }

    public String getTrackName() {
        return trackName.get();
    }

    public String getArtist() {
        return artist.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public void setTrackName(String tName) {
        trackName.set(tName);
    }

    public void setArtist(String artistName) {
        artist.set(artistName);
    }

    public void setTime(String duration) {
        time.set(duration);
    }

    public void setGenre(String gen) {
        genre.set(gen);
    }

}


