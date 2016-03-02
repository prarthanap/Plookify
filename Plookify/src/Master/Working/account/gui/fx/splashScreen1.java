/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.gui.imageLib2;
import Master.Working.account.logic.logic;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jll30
 */
public class splashScreen1 extends Application {
    
    private Pane paneSplash;
    private Pane paneStart;
    private Pane paneLogin;
    private Scene loadingSplash;
    private Scene startScreen;
    private Scene loginScreen;
    private Stage screens;
    private final imageLib2 images=new imageLib2();
    
    @Override
    public void start(Stage splashStage) {
        screens=splashStage;
        screens.setResizable(false);
        splashPane();
        startPane();
        loginPane();
        loadingSplash = new Scene(paneSplash, 300, 350);
        startScreen = new Scene(paneStart,400,200);
        loginScreen=new Scene(paneLogin,400,300);
        screens.setTitle("Plookify");
        screens.setScene(loadingSplash);
        screens.show();
        PauseTransition pause=new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event->screens.setScene(startScreen));
        pause.play();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void splashPane()
    {
        paneSplash=new Pane();
        paneSplash.setStyle("-fx-background-color: #000000;");
        Label icon=new Label("",new ImageView(images.getImage("logo")));
        icon.relocate(50,20);
        paneSplash.getChildren().add(icon);
        ProgressBar bar1=new ProgressBar();
        bar1.relocate(50,270);
        bar1.setPrefSize(200,20);
        paneSplash.getChildren().add(bar1);
        paneSplash.setPrefSize(400,350);
    }
    
    public void startPane()
    {
        paneStart=new Pane();
        paneStart.setStyle("-fx-background-color: #000000;");
        paneStart.setPrefSize(300,200);
        Label plookify=new Label("Plookify");
        Label Logo1 =new Label("",new ImageView(images.getImage("logo_small")));
        plookify.setStyle("-fx-text-fill: Cyan;");plookify.setScaleX(1.5);plookify.setScaleY(1.5);
        paneStart.getChildren().add(plookify);plookify.relocate(170,120);
        paneStart.getChildren().add(Logo1);Logo1.relocate(145,20);
        Button registerButton=new Button("REGISTER");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                System.out.println("Pressed Register");
            }
        });
        Button loginButton=new Button("LOGIN");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                System.out.println("Pressed Login");
                screens.setScene(loginScreen);
            }
        }); 
        paneStart.getChildren().add(registerButton);registerButton.setLayoutX(200);registerButton.setLayoutY(160);
        paneStart.getChildren().add(loginButton);loginButton.setLayoutX(120);loginButton.setLayoutY(160);
    }
    public void loginPane()
    {
        paneLogin=new Pane();
        paneLogin.setStyle("-fx-background-color: #000000;");
        paneLogin.setPrefSize(400,300);
        Label plookify=new Label("Plookify");
        Label Logo1 =new Label("",new ImageView(images.getImage("logo_small")));
        plookify.setStyle("-fx-text-fill: Cyan;");plookify.setScaleX(1.5);plookify.setScaleY(1.5);
        plookify.relocate(175,120);
        Logo1.relocate(150,20);
        Label userLabel=new Label("Username");
        userLabel.setStyle("-fx-text-fill: White;");
        Label passLabel=new Label("Password");
        passLabel.setStyle("-fx-text-fill: White;");
        TextField unameField=new TextField();
        unameField.setPromptText("Enter Username");
        unameField.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(unameField.getText().length()>12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        PasswordField passField=new PasswordField();
        passField.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            {
                if(passField.getText().length()>12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        paneLogin.getChildren().addAll(userLabel,passLabel,unameField,passField,Logo1,plookify);
        userLabel.relocate(85, 180);
        passLabel.relocate(85, 210);
        unameField.relocate(170,180);
        passField.relocate(170,210);
        
        /*=====================================================================*/
        /*=========================Now for the Buttons=========================*/
        /*=====================================================================*/
        Button submitButton=new Button("SUBMIT");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                System.out.println("Pressed submit");
                String uname = unameField.getText();
                String passwd = passField.getText();
                logic accLogic=new logic();
                if(accLogic.authCheck(uname,passwd)==9999)//returns 9999(which no one will have for id) when incorrect
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Authentication Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username and/or Password");
                    alert.showAndWait();
                }
                else
                {
                    System.out.println("Match");
                    Alert success = new Alert(AlertType.INFORMATION);
                    success.setTitle("Authentication Success");
                    success.setHeaderText(null);
                    success.setContentText("Welcome "+accLogic.getDetailString(accLogic.authCheck(uname,passwd),"ID","Account","Firstname")+"!");
                    success.showAndWait();
                     
                }
             }
        });
        
        Button resetButton=new Button("RESET");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                System.out.println("Pressed reset");
                unameField.setText("");
                passField.setText("");                
            }
        }); 
        Button backButton=new Button("BACK");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                System.out.println("Pressed back");
                screens.setScene(startScreen);                
            }
        }); 
        
        paneLogin.getChildren().addAll(submitButton,resetButton,backButton);
        submitButton.relocate(50,260);
        resetButton.relocate(150,260);
        backButton.relocate(250,260);
        
    }
}
