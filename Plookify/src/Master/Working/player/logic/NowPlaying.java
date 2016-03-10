/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.player.logic;

import Master.Working.player.gui.Tracks;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 *
 * @author prarthana
 */
public class NowPlaying {

    @FXML
    ComboBox nowPlayingMenu;
    private List<String> list = new ArrayList<String>();
    private Iterator<String> itr;

    public NowPlaying() {
        
        

    }

    public ComboBox addTrack() {
       
        return nowPlayingMenu;
    }

    
    
    
    public ComboBox removeTrack() {

        return nowPlayingMenu;
    }

    public ComboBox updateNowPlaying() {

        return nowPlayingMenu;
    }

}
