/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.swing;

import Master.Working.account.gui.imageLib;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jll30
 */
public class Splash
{
    private Dimension size=new Dimension(400,400);
    private JPanel panel1;
    private JFrame splash;
    private JProgressBar prog1;
    public Splash()
    {
        makeSplash();
    }

    private void makeSplash()
    {
        panel1=panel();
        splash=new JFrame("Plookify");
        splash.setSize(size);
        splash.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        splash.getContentPane().add(panel1);
    }
    
    public JPanel panel()
    {
        JPanel retPanel=new JPanel();
        imageLib images=new imageLib();
        retPanel.setLayout(null);
        retPanel.setPreferredSize(size);
        retPanel.setBackground(Color.WHITE);
        
        JLabel Logo1 =new JLabel(images.getImage("logo_small"));
        retPanel.add(Logo1).setBounds(100,100,195,195);
        prog1=new JProgressBar();
        prog1.setMaximum(100);
        prog1.setValue(0);
        retPanel.add(prog1).setBounds(100,300,200,30);
        return retPanel;
    }
    public void startUI()
    {
        splash.setResizable(false);
        splash.pack();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        Timer time=new Timer(250, new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)//everything moves rightward(displays left side)
                   {
                      if(prog1.getValue()<100)
                      {
                          prog1.setValue(prog1.getValue()+10);
                      }
                      else if(prog1.getValue()==100){
                      ((Timer) e.getSource()).stop();
                      splash.dispose();
                      StartScreen start=new StartScreen();
                      start.startUI();}
                   }
                        });
        time.start();
    }

    
    
}
