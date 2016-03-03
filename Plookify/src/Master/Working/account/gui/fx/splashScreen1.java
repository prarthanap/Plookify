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
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author jll30
 */
public class splashScreen1 extends Application {
    
    private Pane paneSplash;
    private Pane paneStart;
    private Pane paneLogin;
    private Pane paneAccount;
    private Pane paneAccountD;
    private Pane paneDevice;
    private Scene splashScreen;
    private Scene startScreen;
    private Scene loginScreen;
    private Scene accountScreen;
    private Scene accountDScreen;
    private Scene deviceScreen;
    private Stage mainStage;
    private final imageLib2 images=new imageLib2();
    
    @Override
    public void start(Stage splashStage) {
        mainStage=splashStage;
        mainStage.setResizable(false);
        splashPane();
        startPane();
        loginPane();
        accountPane();
        splashScreen = new Scene(paneSplash);
        startScreen = new Scene(paneStart);
        loginScreen=new Scene(paneLogin);
        accountScreen=new Scene(paneAccount);
        mainStage.setTitle("Plookify");
        mainStage.setScene(splashScreen);
        mainStage.show();
        PauseTransition pause=new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event->mainStage.setScene(startScreen));
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
                mainStage.setScene(loginScreen);
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
                    submitButton.setDisable(true);//prevents button being clicked untill popup is gone
                    Stage incorrect=new Stage();
                    incorrect.initStyle(StageStyle.UNDECORATED);//removes window decorations
                    incorrect.setTitle("Error!");
                    Pane incorrectDialog=new Pane();
                    incorrectDialog.setPrefSize(200,70);
                    Button ok=new Button("Ok");
                    Label msg=new Label("Authentication Error");
                    incorrectDialog.getChildren().addAll(ok,msg);
                    msg.relocate(40,10);
                    ok.relocate(90, 40);
                    ok.setOnAction(new EventHandler<ActionEvent>() { 
                        public void handle(ActionEvent event) {
                            submitButton.setDisable(false);//re-enable button
                            incorrect.hide();
                        }
                    }); 
                    incorrect.setScene(new Scene(incorrectDialog));
                    incorrect.show();
                    //Alert alert = new Alert(AlertType.INFORMATION);       //Would have used Alert if the ITL machines used jdk newer than 1.8 u40, machines used 1.8 u25 instead
                    //alert.setTitle("Authentication Error");
                    //alert.setHeaderText(null);
                    //alert.setContentText("Incorrect Username and/or Password");
                    //alert.showAndWait();
                }
                else
                {
                    System.out.println("success");
                    submitButton.setDisable(true);//prevents button being clicked untill popup is gone
                    Stage success=new Stage();
                    success.initStyle(StageStyle.UNDECORATED);//removes window decorations
                    success.setTitle("Success!");
                    Pane successDialog=new Pane();
                    successDialog.setPrefSize(200,70);
                    Button ok=new Button("Ok");
                    Label msg=new Label("Authentication Error");
                    successDialog.getChildren().addAll(ok,msg);
                    msg.relocate(40,10);
                    ok.relocate(90, 40);
                    success.show();
                    ok.setOnAction(new EventHandler<ActionEvent>() { 
                        public void handle(ActionEvent event) {
                            submitButton.setDisable(false);//re-enable button
                            success.hide();
                            mainStage.setScene(accountScreen);
                        }
                    });
                    //System.out.println("Match");
                    //Alert success = new Alert(AlertType.INFORMATION);
                    //success.setTitle("Authentication Success");
                    //success.setHeaderText(null);
                    //success.setContentText("Welcome "+accLogic.getDetailString(accLogic.authCheck(uname,passwd),"ID","Account","Firstname")+"!");
                    //success.showAndWait();
                     
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
                mainStage.setScene(startScreen);                
            }
        }); 
        
        paneLogin.getChildren().addAll(submitButton,resetButton,backButton);
        submitButton.relocate(50,260);
        resetButton.relocate(150,260);
        backButton.relocate(250,260);
        
    }
    
    public void accountPane()
    {
        paneAccount=new Pane();
        paneAccount.setPrefSize(600,400);
        paneAccount.setStyle("-fx-background-color: #000000;");
        Label logoMini= new Label("",new ImageView(images.getImage("logo_small")));
        logoMini.setScaleX(50);logoMini.setScaleY(50);
        paneAccount.getChildren().add(logoMini);
        logoMini.relocate(20, 20);
        /*JButton prem=new JButton();
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
        mainPanel.add(changeDetailsButton).setBounds(350,300,150,30);*/
    }
}
