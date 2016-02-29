/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui;

import javax.swing.*;

/**
 *
 * @author jll30
 */
public class Splash
{
    private JFrame splash;
    public Splash()
    {
        makeSplash();
    }

    private void makeSplash()
    {
        splash=new JFrame("Plookify");
        splash.setSize(400, 400);
        splash.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
        imageLib images=new imageLib();
        JLabel icon=new JLabel(images.getImage("logo"));
        panel1.add(icon).setBounds(200,200,195,195);
        JProgressBar bar1=new JProgressBar();
        splash.getContentPane().add(panel1);
        splash.setResizable(false);
    }
    private void starto()
    {
        splash.pack();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
    }
    
}
