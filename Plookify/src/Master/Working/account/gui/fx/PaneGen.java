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
import javafx.scene.layout.Pane;

/**
 *
 * @author jll30
 */
public class PaneGen
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
}
