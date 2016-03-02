/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.gui.imageLib2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author jll30
 */
public class PaneGen extends splashScreen1
{
    imageLib2 images=new imageLib2();
    public PaneGen()
    {}
    public Pane splashPane()
    {
        Pane paneS=new Pane();
        paneS.setStyle("-fx-background-color: #000000;");
        Label icon=new Label("",new ImageView(images.getImage("logo")));
        icon.setLayoutX(50);
        icon.setLayoutY(20);
        paneS.getChildren().add(icon);
        ProgressBar bar1=new ProgressBar();
        bar1.setLayoutX(50);
        bar1.setLayoutY(270);
        bar1.setMinSize(200,20);
        paneS.getChildren().add(bar1);
        paneS.setPrefSize(400,350);
        return paneS;
    }
    
    public Pane startPane()
    {
        Pane paneStart=new Pane();
        paneStart.setStyle("-fx-background-color: #000000;");
        paneStart.setPrefSize(300,200);
        Label plookify=new Label("Plookify");
        Label Logo1 =new Label("",new ImageView(images.getImage("logo_small")));
        plookify.setStyle("-fx-text-fill: Cyan;");plookify.setScaleX(1.5);plookify.setScaleY(1.5);
        paneStart.getChildren().add(plookify);plookify.setLayoutX(170);plookify.setLayoutY(120);
        paneStart.getChildren().add(Logo1);Logo1.setLayoutX(150);Logo1.setLayoutY(20);
        Button registerButton=new Button("REGISTER");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pressed Register");
            }
        });
        Button loginButton=new Button("LOGIN");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pressed Login");
            }
        }); 
        paneStart.getChildren().add(registerButton);registerButton.setLayoutX(200);registerButton.setLayoutY(160);
        paneStart.getChildren().add(loginButton);loginButton.setLayoutX(120);loginButton.setLayoutY(160);
        return paneStart;
    }
    
    public Pane loginPane()
    {
        Pane paneLogin=new Pane();
        paneLogin.setStyle("-fx-background-color: #000000;");
        paneLogin.setPrefSize(400,300);
        Label plookify=new Label("Plookify");
        Label Logo1 =new Label("",new ImageView(images.getImage("logo_small")));
        plookify.setStyle("-fx-text-fill: Cyan;");plookify.setScaleX(1.5);plookify.setScaleY(1.5);
        
        Label userLabel=new Label("Username");
        userLabel.setStyle("-fx-text-fill: White;");
        Label passLabel=new Label("Password");
        passLabel.setStyle("-fx-text-fill: White;");
        HBox credBox=new HBox();
        TextField unameField=new TextField();
        unameField.setPromptText("Enter Username");
        unameField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if (unameField.getText().length() >=12 )
                ke.consume(); 
            }  
        });
        PasswordField passField=new PasswordField();
        passField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if (unameField.getText().length() >=12 )
                ke.consume(); 
            }  
        });
        credBox.getChildren().addAll(userLabel,unameField);
        credBox.setSpacing(10);
        
/*==============================================================================================================================================*/
/*===========================================Makes the Submit and Reset Buttons=================================================================*/
/*==============================================================================================================================================*/
        /*JButton submitButton=new JButton("SUBMIT");
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
        panel1.add(backButton).setBounds(250,260,90,20);*/
        
        paneLogin.getChildren().add(credBox);
        
        
        return paneLogin;
    }
}
