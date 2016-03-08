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
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jll30
 *//*
public class accountScreen extends JPanel
{
    private static Dimension size=new Dimension(600,400);
    private static JFrame regScreen;
    private static JPanel startPanel;
    private static int xMargin=80;//position points of reference
    private static int[] yValues={170,250,320};
    private static int iD=9999;
    private static logic logicA=new logic();
    public accountScreen(int identifier)
    {
        iD=identifier;
        //makeScreenA();
    }
    public static void main(String[] args)
    {
        startPanel=panel();
        regScreen=new JFrame("Plookify - Account");
        regScreen.setPreferredSize(size);
        regScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        regScreen.getContentPane().add(startPanel);
        regScreen.setResizable(false);
        regScreen.pack();
        regScreen.setLocationRelativeTo(null);
        regScreen.setVisible(true);
        
    }
    public void startUI()
    {
        
        regScreen.setResizable(false);
        regScreen.pack();
        regScreen.setLocationRelativeTo(null);
        regScreen.setVisible(true);
    }
    
    public static JPanel panel()
    {
        imageLib images=new imageLib();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(size);
        mainPanel.setBackground(Color.BLACK);
        
        
        Image logoMini= images.getImage("logo_small").getImage();
        logoMini=logoMini.getScaledInstance(50, 50,java.awt.Image.SCALE_SMOOTH);
        ImageIcon logoM=new ImageIcon(logoMini);
        JLabel logo1 = new JLabel(logoM);
        mainPanel.add(logo1).setBounds(20,20,50,50);//add mini logo
        JButton prem=new JButton();
        if(logicA.premCheck(4)==2)
        {
            prem.setText("Premium");
            JLabel expiry=new JLabel("Next Due :"+logicA.getDetailString(4,"USERID","SUBSCRIPTION","DUEDATE"), SwingConstants.CENTER);
            expiry.setForeground(Color.RED);
            mainPanel.add(expiry).setBounds(420,70,150,20);
        }
        else if(logicA.premCheck(4)==0)
        {
            prem.setText("error");
        }
        else 
        {
            prem.setText("Free");
        }
        mainPanel.add(prem).setBounds(450,40,110,20);//prem status button

        JLabel accountTitle=new JLabel(logicA.getDetailString(4,"ID","ACCOUNT", "FIRSTNAME")+" "+logicA.getDetailString(4,"ID","ACCOUNT", "LASTNAME"), SwingConstants.CENTER);accountTitle.setForeground(Color.WHITE);      
        accountTitle.setFont(new java.awt.Font("Tahoma", 0, 22));
        accountTitle.setOpaque(true);
        accountTitle.setBackground(Color.GRAY);
        mainPanel.add(accountTitle).setBounds(100,40,350,20);
        
        JButton logOutButton=new JButton("Log out");
        mainPanel.add(logOutButton).setBounds(100,300,100,20);
        JButton deviceButton=new JButton("Devices");
        mainPanel.add(deviceButton).setBounds(225,300,100,20);
        JButton changeDetailsButton=new JButton("<html><center>Change Account Details</center></html>");
        mainPanel.add(changeDetailsButton).setBounds(350,300,150,30);
        
        
        
        return mainPanel;
    }
}
*/