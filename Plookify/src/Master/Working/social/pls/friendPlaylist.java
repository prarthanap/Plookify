/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hamza
 */
public class friendPlaylist {
    private final SimpleStringProperty playlist;
    
    public friendPlaylist(String playlist) {
        this.playlist = new SimpleStringProperty(playlist);
        
    }

    public String getListname() {
        return playlist.get();
    }

    public void setID(String Playlist) {
        playlist.set(Playlist);
    }
    
    
    
}
