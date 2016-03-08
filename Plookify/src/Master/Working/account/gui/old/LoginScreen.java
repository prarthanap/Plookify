/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.old;

import Master.Working.account.gui.imageLib;
import Master.Working.account.logic.logic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jll30
 */
/*public class LoginScreen extends JPanel
{
    private Dimension size=new Dimension(400,300);
    private JPanel startPanel;
    private JFrame firstScreen;
    public LoginScreen()
    {
        makeScreenL();
    }
    
    public void makeScreenL()
    {
        startPanel=panel();
        firstScreen=new JFrame("Plookify - Login");
        firstScreen.setSize(size);
        firstScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        firstScreen.getContentPane().add(startPanel);
    }
    public void startUI()
    {
        firstScreen.setResizable(false);
        firstScreen.pack();
        firstScreen.setLocationRelativeTo(null);
        firstScreen.setVisible(true);
    }
    
    
    public JPanel panel()
    {
        JPanel panel1=new JPanel();
        imageLib images=new imageLib();
        panel1.setLayout(null);
        panel1.setPreferredSize(size);
        panel1.setBackground(Color.BLACK);
/*==============================================================================================================================================*/
/*======================================Makes the Logo,Title, username+password label and textfields============================================*/
/*==============================================================================================================================================*
        JLabel plookify=new JLabel("Plookify");
        JLabel Logo1 =new JLabel(images.getImage("logo_small"));
        plookify.setForeground(Color.CYAN);
        plookify.setFont(new java.awt.Font("Tahoma", 0, 20));
        plookify.setHorizontalTextPosition(JLabel.CENTER);
        
        JLabel userLabel=new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        JLabel passLabel=new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        JTextField unameField=new JTextField();
        unameField.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (unameField.getText().length() >=12 ) // limit textfield to 12 characters
                e.consume(); 
            }  
        });
        JPasswordField passField=new JPasswordField();
        passField.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (unameField.getText().length() >=12 )
                e.consume(); 
            }  
        });
        
        
        panel1.add(unameField).setBounds(170,180,110,20);
        panel1.add(passField).setBounds(170,210,110,20);
        panel1.add(userLabel).setBounds(80,180,100,20);
        panel1.add(passLabel).setBounds(80,210,100,20);
/*==============================================================================================================================================*/
/*===========================================Makes the Submit and Reset Buttons=================================================================*/
/*==============================================================================================================================================*
        JButton submitButton=new JButton("SUBMIT");
        submitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressSubmit(evt);
            }
            private void pressSubmit(ActionEvent evt)
            {
                String uname = unameField.getText();
                String passwd = passField.getText();
                logic accLogic=new logic();
                if(accLogic.authCheck(uname,passwd)==9999)//returns 9999(which no one will have for id) when incorrect
                {
                    JOptionPane.showMessageDialog(null,"Incorrect Username and/or Password");
                }
                else
                {
                     System.out.println("Match");
                     JOptionPane.showMessageDialog(null,"Welcome "+accLogic.getDetailString(accLogic.authCheck(uname,passwd),"ID","Account","Firstname")+"!");//welcome message with users name.
                     
                }
             }
        });
        
        JButton resetButton=new JButton("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressReset(evt);
            }
            private void pressReset(ActionEvent evt)
            {
                System.out.println("Pressed Reset");
            }
        });
        JButton backButton=new JButton("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressBack(evt);
            }
            private void pressBack(ActionEvent evt)
            {
                StartScreen start1=new StartScreen();
                firstScreen.dispose();
                start1.startUI();
            }
        });
        panel1.add(Logo1).setBounds(150,20,100,100);
        panel1.add(plookify).setBounds(160,120,100,40);
        panel1.add(resetButton).setBounds(150,260,90,20);
        panel1.add(submitButton).setBounds(50,260,90,20);
        panel1.add(backButton).setBounds(250,260,90,20);

        return panel1;
    }
}
*/