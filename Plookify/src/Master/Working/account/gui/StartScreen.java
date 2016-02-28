/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jll30
 */
public class StartScreen extends JPanel
{
    public StartScreen()
    {
        setLayout(null);
        setPreferredSize(new Dimension(400,200));
        setBackground(Color.BLACK);
        
        JLabel plookify=new JLabel("Plookify");
        plookify.setForeground(Color.CYAN);
        plookify.setFont(new java.awt.Font("Tahoma", 0, 20));
        plookify.setHorizontalTextPosition(JLabel.CENTER);
        
        JButton registerButton=new JButton("REGISTER");
        
        JButton loginButton=new JButton("LOGIN");
        add(plookify).setBounds(160,120,70,40);
        add(registerButton).setBounds(200,160,90,20);
        add(loginButton).setBounds(100,160,90,20);;
    }
    
    public static void main(String[] args)
    {
        JFrame firstScreen=new JFrame("Plookify");
        firstScreen.setSize(400, 200);
        JPanel startPanel=new StartScreen();
        firstScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        firstScreen.getContentPane().add(startPanel);
        firstScreen.setLocationRelativeTo(null);
        firstScreen.setResizable(false);
        firstScreen.pack();
        firstScreen.setVisible(true);
    }
}
