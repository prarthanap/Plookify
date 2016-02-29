/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui;

import Master.Working.account.logic.logic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jll30
 */
public class RegisterScreen extends JPanel
{
    static Dimension size=new Dimension(500,600);
    public RegisterScreen()
    {
        //makeScreen();
    }
    
    public static void main(String[] args)
    {
        
        JPanel startPanel=panel();
        JFrame firstScreen=new JFrame("Plookify - Register");
        firstScreen.setSize(size);
        firstScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        firstScreen.getContentPane().add(startPanel);
        firstScreen.setLocationRelativeTo(null);
        firstScreen.setResizable(false);
        firstScreen.pack();
        firstScreen.setVisible(true);
    }
    
    
    public static JPanel panel()
    {
        imageLib images=new imageLib();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(size);
        mainPanel.setBackground(Color.BLACK);
        
        JLabel logo1 = new JLabel(images.getImage("logo_small"));
        mainPanel.add(logo1).setBounds(150,10,100,100);//add logo
        JLabel registerTitle = new JLabel("Register Form");registerTitle.setForeground(Color.CYAN);
        registerTitle.setFont(new java.awt.Font("Tahoma", 0, 14));
        mainPanel.add(registerTitle).setBounds(160, 140, 90, 20);//add registerTitle
        JLabel title = new JLabel("Plookify");title.setForeground(Color.CYAN);
        title.setFont(new java.awt.Font("Tahoma", 0, 20));
        
        JLabel unameLabel = new JLabel("Username");unameLabel.setForeground(Color.WHITE);
        JLabel passLabel = new JLabel("Password");passLabel.setForeground(Color.WHITE);
        JLabel fnameLabel = new JLabel("Firstname");fnameLabel.setForeground(Color.WHITE);
        JLabel lnameLabel = new JLabel("Lastname");lnameLabel.setForeground(Color.WHITE);
        JLabel doorNoLabel = new JLabel("Door No.");doorNoLabel.setForeground(Color.WHITE);
        JLabel streetLabel = new JLabel("Street");streetLabel.setForeground(Color.WHITE);
        JLabel countyLabel = new JLabel("County");countyLabel.setForeground(Color.WHITE);
        JLabel cityLabel = new JLabel("City");cityLabel.setForeground(Color.WHITE);
        JLabel postcodeLabel = new JLabel("PostCode");postcodeLabel.setForeground(Color.WHITE);
        mainPanel.add(unameLabel).setBounds(80, 190, 70, 20);//add username label
        mainPanel.add(passLabel).setBounds(80, 220, 70, 20);//add password label
        mainPanel.add(lnameLabel).setBounds(80, 300, 70, 20);//add lastname label
        mainPanel.add(fnameLabel).setBounds(80, 270, 70, 20);//add firstname label
        mainPanel.add(doorNoLabel).setBounds(50, 350, 70, 20);//add doorNo label
        mainPanel.add(streetLabel).setBounds(180, 350, 70, 10);//add street label
        mainPanel.add(countyLabel).setBounds(50, 380, 70, 20);//add county label
        mainPanel.add(cityLabel).setBounds(50, 410, 70, 20);//add city label
        mainPanel.add(postcodeLabel).setBounds(50, 440, 70, 20);//add postcode label
        
        JTextField unameR = new JTextField();
        unameR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (unameR.getText().length() >=12 ) // limit textfield to 12 characters
                e.consume(); 
            }  
        });
        JTextField fnameR = new JTextField();
        fnameR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (fnameR.getText().length() >=12 ) // limit textfield to 12 characters
                e.consume(); 
            }  
        });
        JTextField lnameR = new JTextField();
        lnameR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (lnameR.getText().length() >=12 ) // limit textfield to 12 characters
                e.consume(); 
            }  
        });
        JTextField doorNoR = new JTextField();
        doorNoR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (doorNoR.getText().length() >=5 ) // limit textfield to 5 characters
                e.consume();
            }  
        });
        JTextField streetR = new JTextField();
        streetR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (streetR.getText().length() >=16 ) // limit textfield to 16 characters
                e.consume(); 
            }  
        });
        JTextField passR = new JPasswordField();
        passR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (passR.getText().length() >=12 ) // limit textfield to 12 characters
                e.consume(); 
            }  
        });
        JTextField countyR = new JTextField();
        countyR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (countyR.getText().length() >=12 ) // limit textfield to 12 characters
                e.consume(); 
            }  
        });
        JTextField cityR = new JTextField();
        cityR.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (cityR.getText().length() >=16 ) // limit textfield to 16 characters
                e.consume(); 
            }  
        });
        JTextField postcode2R = new JTextField();
        postcode2R.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (postcode2R.getText().length() >=4 ) // limit textfield to 4 characters
                e.consume(); 
            }  
        });
        JTextField postcode1R = new JTextField();
        postcode1R.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (postcode1R.getText().length() >=4 ) // limit textfield to 4 characters
                e.consume(); 
            }  
        });
        JButton RegisterSubmit = new JButton("REGISTER");
        JButton ResetButton = new JButton("RESET");
        ResetButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressReset(evt);
            }
            private void pressReset(ActionEvent evt)
            {
                unameR.setText("");
                fnameR.setText(""); 
                lnameR.setText("");
                doorNoR.setText("");
                streetR.setText("");
                streetLabel.setText("");
                passR.setText("");
                countyR.setText("");
                cityR.setText("");
                postcode2R.setText("");
                postcode1R.setText("");
            }
        });
        
        JButton BackButton = new JButton("BACK");
        
        mainPanel.add(unameR).setBounds(150, 190, 100, 10); 
        mainPanel.add(fnameR).setBounds(150, 270, 200,10); 
        mainPanel.add(lnameR).setBounds(150, 300, 100, 10);
        mainPanel.add(doorNoR).setBounds(120, 350, 40, 20);
        mainPanel.add(streetR).setBounds(230, 350, 100, 20);
        mainPanel.add(countyR).setBounds(120, 380, 100, 20);
        mainPanel.add(cityR).setBounds(120, 410, 100, 20);
        mainPanel.add(postcode2R).setBounds(180, 440, 30, 20);
        mainPanel.add(postcode1R).setBounds(120, 440, 40, 20);
        mainPanel.add(passR).setBounds(150, 220, 100, -1);//add textfields and password fields
        
        mainPanel.add(RegisterSubmit).setBounds(80, 480, 70, 20);
        mainPanel.add(ResetButton).setBounds(170, 480, 70, 20);
        mainPanel.add(BackButton).setBounds(270, 480, 70, 20);//adds buttons
        
        
        
        
        
        
        
        
        
        
        
        
        
        return mainPanel;
    }
}
