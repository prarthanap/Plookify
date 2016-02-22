/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio;

import javax.swing.JOptionPane;
import Master.Working.radio.logic.logic;

/**
 *
 * @author Samad
 */
public class ComponentLoader {
    public static void main(String[] args) {
        logic test = new logic();
        String searchArtist = "";
        String radioTrack = "";
        for (int i = 0; i<=9; i++)
        {
            searchArtist = test.randomArtist();
            radioTrack = test.randomTrack();
            test.addToRadio(radioTrack,i);
        }
        test.printRadio();
        JOptionPane.showMessageDialog(null, "The Application Works.");
    }
    
}
