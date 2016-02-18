/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.logic;

import Master.Working.radio.database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
/**
 *
 * @author ec14082 - Samad
 */
public class logic {
    
    private database data = new database();
    
    private String primaryArtist;
    private String primaryGenre;
    private String searchArtist;
    private String randomTrack;
    private String[] radioChannel;
    
    public logic() {
        primaryGenre = "ROCK"; //Temporary
        radioChannel = new String[9];
    }
    
    public String randomArtist() {
        // use primaryGenre to randomly select artist
        String searchArt = "SELECT ARTIST FROM TRACKS WHERE GENRE='"+primaryGenre+"'";
        try {
                return data.makeQuery(searchArt).getString(1);
        }
        catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return primaryArtist;
    }
    
    public String randomTrack() {
        // use searchArtist to randomly select a track
        return randomTrack;
    } 
    
    public void addToRadio (String track, int position) {
        radioChannel[position] = track;
    }
}
