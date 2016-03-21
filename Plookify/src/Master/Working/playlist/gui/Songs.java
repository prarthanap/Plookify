/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.gui;

/**
 *
 * @author Edgar
 */
public class Songs {
    private String songName;
    private String songArtist;
    private String songAlbum;
    private String songDur;

    
    public Songs(String songName, String songArtist, String songDur) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songDur = songDur;
    }
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongDur() {
        return songDur;
    }

    public void setSongDur(String songDur) {
        this.songDur = songDur;
    }
    
    
}
