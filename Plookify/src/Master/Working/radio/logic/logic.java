/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.logic;

import Master.Working.Common.database;
import java.sql.Array;
import java.sql.ResultSet;
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
        //Temporarily in place for search function/////////////////////
        String sQry = "SELECT ARTIST FROM TRACKS ORDER BY RANDOM() LIMIT 1";
        try {
        String searching = data.makeQuery(sQry).getString(1);
        primaryGenre = findGenre(searching); //Temporary
        primaryArtist = "";//Temporary 
        }
        catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////////////////////////////////////////////////////////
        radioChannel = new String[10];
    }
    
    public String findGenre(String search){
        String searchArt = "SELECT GENRE FROM TRACKS WHERE ARTIST='"+search+"'ORDER BY RANDOM() LIMIT 1";
        try {
                return data.makeQuery(searchArt).getString(1);
        }
        catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ROCK"; //Temporary
    }
    
    public String getGenre() {
        return primaryGenre;
    }
    
    public String randomArtist() {
        // use primaryGenre to randomly select artist
        String searchArt = "SELECT ARTIST FROM TRACKS WHERE GENRE='"+primaryGenre+"'ORDER BY RANDOM() LIMIT 10";
        try {
            /*ArrayList<String> list= new ArrayList<String>();
            ResultSet rs = data.makeQuery(searchArt);
            while (rs.next()) {
                list.add(rs.getString(1)); 
            }
            radioChannel = list.toArray(radioChannel);*/
                searchArtist = data.makeQuery(searchArt).getString(1);
        }
        catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchArtist;
        //return radioChannel;
    }
    
    public String randomTrack() {
        // use searchArtist to randomly select a track
        String qryTrack = "SELECT TRACKNAME FROM TRACKS WHERE ARTIST='"+searchArtist+"' AND GENRE='"+primaryGenre+"' ORDER BY RANDOM() LIMIT 10";
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
