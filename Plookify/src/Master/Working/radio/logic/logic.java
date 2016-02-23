/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.logic;

import Master.Working.Common.database;
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
    private String artTrack;
    public String[] radioChannel;
    
    public logic() {
        primaryGenre = "ROCK"; //Temporary
        primaryArtist = "";//Temporary
        radioChannel = new String[10];
    }
    
    public String randomArtist() {
        // use primaryGenre to randomly select artist
        String searchArt = "SELECT ARTIST FROM TRACKS WHERE GENRE='"+primaryGenre+"'ORDER BY RANDOM() LIMIT 1";
        try {
                searchArtist = data.makeQuery(searchArt).getString(1);
        }
        catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchArtist;
    }
    
    public String randomTrack() {
        // use searchArtist to randomly select a track
        String qryTrack = "SELECT TRACKNAME FROM TRACKS WHERE ARTIST='"+searchArtist+"' AND GENRE='"+primaryGenre+"' ORDER BY RANDOM() LIMIT 1";
        try {
                artTrack = data.makeQuery(qryTrack).getString(1);
        }
        catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artTrack;
    } 
    
    public void addToRadio (String track, int position) {

        radioChannel[position] = track;
    }
    
    public void printRadio() {
        System.out.println(Arrays.toString(radioChannel));
    }
    
    public void saveAsPlaylist() {
        
    }
    
    public String[] getRadio() {
        return radioChannel;
    }
}
