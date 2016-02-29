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
    private Dimension size=new Dimension(500,600);
    private JFrame regScreen;
    private JPanel startPanel;
    private int xMargin=80;//position points of reference
    private int[] yValues={170,250,320};
    public RegisterScreen()
    {
        makeScreenR();
    }
    public void makeScreenR()
    {
        startPanel=panel();
        regScreen=new JFrame("Plookify - Register");
        regScreen.setPreferredSize(size);
        regScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        regScreen.getContentPane().add(startPanel);       
        
    }
    public void startUI()
    {
        regScreen.setResizable(false);
        regScreen.pack();
        regScreen.setLocationRelativeTo(null);
        regScreen.setVisible(true);
    }
    
    public JPanel panel()
    {
        imageLib images=new imageLib();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(size);
        mainPanel.setBackground(Color.BLACK);
        
        JLabel logo1 = new JLabel(images.getImage("logo_small"));
        mainPanel.add(logo1).setBounds(200,10,100,100);//add logo
        JLabel title = new JLabel("Plookify");title.setForeground(Color.CYAN);
        title.setFont(new java.awt.Font("Tahoma", 0, 20));
        mainPanel.add(title).setBounds(210, 120, 90, 20);//add title
        
        JLabel unameLabel = new JLabel("Username");unameLabel.setForeground(Color.WHITE);
        JLabel passLabel = new JLabel("Password");passLabel.setForeground(Color.WHITE);
        JLabel fnameLabel = new JLabel("Firstname");fnameLabel.setForeground(Color.WHITE);
        JLabel lnameLabel = new JLabel("Lastname");lnameLabel.setForeground(Color.WHITE);
        JLabel doorNoLabel = new JLabel("Door No.");doorNoLabel.setForeground(Color.WHITE);
        JLabel streetLabel = new JLabel("Street");streetLabel.setForeground(Color.WHITE);
        JLabel countyLabel = new JLabel("County");countyLabel.setForeground(Color.WHITE);
        JLabel cityLabel = new JLabel("City");cityLabel.setForeground(Color.WHITE);
        JLabel postcodeLabel = new JLabel("PostCode");postcodeLabel.setForeground(Color.WHITE);
        JLabel contactLabel = new JLabel("Contact No.");contactLabel.setForeground(Color.WHITE);
        mainPanel.add(unameLabel).setBounds(xMargin,yValues[0], 70, 20);//add username label
        mainPanel.add(passLabel).setBounds(xMargin, yValues[0]+30, 70, 20);//add password label
        mainPanel.add(fnameLabel).setBounds(xMargin, yValues[1], 70, 20);//add firstname label
        mainPanel.add(lnameLabel).setBounds(xMargin, yValues[1]+30, 70, 20);//add lastname label
        mainPanel.add(doorNoLabel).setBounds(xMargin, yValues[2], 70, 20);//add doorNo label
        mainPanel.add(streetLabel).setBounds(xMargin, yValues[2]+30, 70, 20);//add street label
        mainPanel.add(countyLabel).setBounds(xMargin, yValues[2]+60, 70, 20);//add county label
        mainPanel.add(cityLabel).setBounds(xMargin, yValues[2]+90, 70, 20);//add city label
        mainPanel.add(postcodeLabel).setBounds(xMargin, yValues[2]+120, 70, 20);//add postcode label
         mainPanel.add(contactLabel).setBounds(xMargin, yValues[2]+150,70,20);//add contact no. label
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
        JTextField contactNo = new JTextField();
        contactNo.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (postcode1R.getText().length() >=11 ) // limit textfield to 11 characters
                e.consume(); 
            }  
        });
        contactNo.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            { 
                if (e.getKeyCode()==KeyEvent.VK_ALPHANUMERIC) //consumes non numeric keys
                e.consume(); 
            }  
        });
        JButton RegisterSubmit = new JButton("SUBMIT");
        RegisterSubmit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressSubmit(evt);
            }
            private void pressSubmit(ActionEvent evt)
            {
               logic logicR=new logic();
               if(logicR.duplicateCheck(unameR.getText().trim(),"username","account"))
               {
                    JOptionPane.showMessageDialog(null,"Username already exists");
               }
               else if(passR.getText().trim().trim().length()<6)
               {
                   JOptionPane.showMessageDialog(null,"Password is too short(minimum length is 8 characters");
               }
               else if(unameR.getText().trim().length()<4)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if(fnameR.getText().trim().length()<4)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if(lnameR.getText().trim().length()<4)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if(doorNoR.getText().trim().length()<1)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if(streetR.getText().trim().length()<6)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if(countyR.getText().trim().length()<4)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if(cityR.getText().trim().length()<4)
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else if((postcode1R.getText().trim().length()<3)||(postcode2R.getText().trim().length()<3))
               {
                   JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else
               {
                   String[] address={doorNoR.getText().trim(),streetR.getText().trim(),countyR.getText().trim(),cityR.getText().trim(),postcode1R.getText().trim()+" "+postcode2R.getText().trim()};
                   logicR.addAccount(unameR.getText().trim(),fnameR.getText().trim(),lnameR.getText().trim(),passR.getText().trim(),address);
               }
               
            }
        });
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
                passR.setText("");
                countyR.setText("");
                cityR.setText("");
                postcode2R.setText("");
                postcode1R.setText("");
            }
        });
        
        JButton BackButton = new JButton("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pressBack(evt);
            }
            private void pressBack(ActionEvent evt)
            {
                StartScreen start1=new StartScreen();
                regScreen.dispose();
                start1.startUI();
            }
        });
        
        mainPanel.add(unameR).setBounds(xMargin+100,yValues[0], 100, 20); 
        mainPanel.add(passR).setBounds(xMargin+100, yValues[0]+30, 100, 20);
        mainPanel.add(fnameR).setBounds(xMargin+100, yValues[1], 120,20); 
        mainPanel.add(lnameR).setBounds(xMargin+100, yValues[1]+30, 120, 20);
        mainPanel.add(doorNoR).setBounds(xMargin+100, yValues[2], 30, 20);
        mainPanel.add(streetR).setBounds(xMargin+100, yValues[2]+30, 150, 20);
        mainPanel.add(countyR).setBounds(xMargin+100, yValues[2]+60, 100, 20);
        mainPanel.add(cityR).setBounds(xMargin+100, yValues[2]+90, 100, 20);
        mainPanel.add(postcode2R).setBounds(xMargin+100, yValues[2]+120, 40, 20);
        mainPanel.add(postcode1R).setBounds(xMargin+160, yValues[2]+120, 40, 20);//add textfields and password fields
        mainPanel.add(contactNo).setBounds(xMargin+100, yValues[2]+150, 100, 20);
        mainPanel.add(RegisterSubmit).setBounds(80, yValues[2]+200, 100, 20);
        mainPanel.add(ResetButton).setBounds(200, yValues[2]+200, 100, 20);
        mainPanel.add(BackButton).setBounds(320, yValues[2]+200, 100, 20);//adds buttons
        
        
        
        return mainPanel;
    }
}
