/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Master.Working.Common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jll30
 */
public class tracksLib {
    private Map<String,String> trackmap=new HashMap<>();;
    
    public tracksLib()
    {
        String f1="/Master/Working/player/logic/Tracks/Mad.mp3";
        trackmap.put("Mad.mp3",f1);
        trackmap.put("Hotline Bling.mp3",f1);
        trackmap.put("You.mp3",f1);
    }
    public String getTrack(String label)
    {
        return trackmap.get(label);
    }
}
