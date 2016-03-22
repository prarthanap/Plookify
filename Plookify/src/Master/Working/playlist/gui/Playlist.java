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
public class Playlist {
    private String name;
    private String id;
    
    public Playlist(String name, String id){
        this.name = name;
        this.id = id;
    }
    
    public Playlist(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
