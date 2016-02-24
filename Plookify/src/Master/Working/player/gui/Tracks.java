/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.player.gui;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author prarthana
 */
public class Tracks {

    private final SimpleStringProperty ID;
    private final SimpleStringProperty trackName;
    private final SimpleStringProperty artist;
    private final SimpleStringProperty time;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty album;

    public Tracks(String ID, String trackName, String artist, String time, String genre, String album) {
        this.ID = new SimpleStringProperty(ID);
        this.trackName = new SimpleStringProperty(trackName);
        this.artist = new SimpleStringProperty(artist);
        this.time = new SimpleStringProperty(time);
        this.genre = new SimpleStringProperty(genre);
        this.album = new SimpleStringProperty(album);
    }

    public String getID() {
        return ID.get();
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

    public String getAlbum() {
        return album.get();
    }

    public void setID(String TrackID) {
        ID.set(TrackID);
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

    public void setAlbum(String alb) {
        album.set(alb);
    }

}


