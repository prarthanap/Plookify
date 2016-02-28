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
import java.io.File;
import javax.swing.*;

/**
 *
 * @author jll30
 */
public class StartScreen extends JPanel
{
    public StartScreen()
    {
        //makeScreen();
    }
    
    public static void main(String[] args)
    {
        JPanel startPanel=panel();
        JFrame firstScreen=new JFrame("Plookify");
        firstScreen.setSize(400, 200);
        firstScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        firstScreen.getContentPane().add(startPanel);
        firstScreen.setLocationRelativeTo(null);
        firstScreen.setResizable(false);
        firstScreen.pack();
        firstScreen.setVisible(true);
    }
    
    public static JPanel panel()
    {
        JPanel panel1=new JPanel();
        imageLib images=new imageLib();
        panel1.setLayout(null);
        panel1.setPreferredSize(new Dimension(400,200));
        panel1.setBackground(Color.BLACK);
        JLabel plookify=new JLabel("Plookify");
        JLabel Logo1 =new JLabel(images.getImage("logo_small"));
        plookify.setForeground(Color.CYAN);
        plookify.setFont(new java.awt.Font("Tahoma", 0, 20));
        plookify.setHorizontalTextPosition(JLabel.CENTER);
        JButton registerButton=new JButton("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressRegister(evt);
            }
            private void pressRegister(ActionEvent evt)
            {
                System.out.println("Pressed Registered");
            }
        });
        
        JButton loginButton=new JButton("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressLogin(evt);
            }
            private void pressLogin(ActionEvent evt)
            {
                System.out.println("Pressed Login");
            }
        });     
        panel1.add(Logo1).setBounds(150,20,100,100);
        panel1.add(plookify).setBounds(160,120,70,40);
        panel1.add(registerButton).setBounds(200,160,90,20);
        panel1.add(loginButton).setBounds(100,160,90,20);
        return panel1;
    }
}
