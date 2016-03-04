/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.gui.imageLib2;
import Master.Working.account.logic.deviceInfo;
import Master.Working.account.logic.logic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Scene registerScreen;
    private Stage mainStage;
    private final imageLib2 images=new imageLib2();
    private final logic accLogic=new logic();
    private int ID=9999;
    private int premstat=0;
    
    @Override
    public void start(Stage splashStage) {
        mainStage=splashStage;
        mainStage.setResizable(false);
        splashPane();
        startPane();
        loginPane();
        registerPane();        
        
        mainStage.setTitle("Plookify");
        mainStage.setScene(splashScreen);
        mainStage.show();
        System.out.println("Start");
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
        icon.relocate(100,20);
        paneSplash.getChildren().add(icon);
        ProgressBar bar1=new ProgressBar();
        bar1.relocate(50,270);
        bar1.setPrefSize(300,20);
        paneSplash.getChildren().add(bar1);
        paneSplash.setPrefSize(400,350);
        splashScreen = new Scene(paneSplash);
    }
    
    public void startPane()
    {
        paneStart=new Pane();
        paneStart.setStyle("-fx-background-color: #000000;");
        paneStart.setPrefSize(400,200);
        Label plookify=new Label("Plookify");
        Label Logo1 =new Label("",new ImageView(images.getImage("logo_small")));
        plookify.setStyle("-fx-text-fill: Cyan;");plookify.setScaleX(1.5);plookify.setScaleY(1.5);
        paneStart.getChildren().add(plookify);plookify.relocate(170,120);
        paneStart.getChildren().add(Logo1);Logo1.relocate(145,20);
        Button registerButton=new Button("REGISTER");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                System.out.println("Pressed Register");
                mainStage.setScene(registerScreen);
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
        startScreen = new Scene(paneStart);
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
                ID=accLogic.data.authCheckD(uname,passwd);
                 try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.out.println(ID+" from login screen");
                if(ID==9999)//returns 9999(which no one will have for id) when incorrect
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
                            passField.setText("");
                        }
                    }); 
                    incorrect.setScene(new Scene(incorrectDialog));
                    incorrect.show();
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                    Button ok2=new Button("Ok");
                    Label msg2=new Label("Welcome "+accLogic.stringGet(ID,"ID","Account","Firstname")+"!");
                    successDialog.getChildren().addAll(ok2,msg2);
                    msg2.relocate(40,10);
                    ok2.relocate(90, 40);
                    success.setScene(new Scene(successDialog));
                    success.show();
                    ok2.setOnAction(new EventHandler<ActionEvent>() { 
                        public void handle(ActionEvent event) {
                            submitButton.setDisable(false);//re-enable button
                            success.hide();
                            unameField.setText("");
                            passField.setText("");
                            accountPane(ID);
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
                unameField.setText("");
                passField.setText("");
                mainStage.setScene(startScreen);                
            }
        }); 
        
        paneLogin.getChildren().addAll(submitButton,resetButton,backButton);
        submitButton.relocate(80,260);
        resetButton.relocate(180,260);
        backButton.relocate(280,260);
       loginScreen=new Scene(paneLogin);
    }
    
    public void registerPane()
    {
        int xMargin=80;//position points of reference
        int[] yValues={170,250,320};
        Pane paneRegister = new Pane();
        paneRegister.setStyle("-fx-background-color: #000000;");
        paneRegister.setPrefSize(500,600);
        
        Label logo1 = new Label("",new ImageView(images.getImage("logo_small")));
        Label title=new Label("Plookify");
        title.setStyle("-fx-text-fill: Cyan;");title.setScaleX(1.5);title.setScaleY(1.5);
        logo1.relocate(200,10);
        title.relocate(210,120);
        paneRegister.getChildren().addAll(logo1,title);
        
        Label unameLabel = new Label("Username");unameLabel.setStyle("-fx-text-fill: White;");unameLabel.relocate(xMargin,yValues[0]);
        Label passLabel = new Label("Password");passLabel.setStyle("-fx-text-fill: White;");passLabel.relocate(xMargin, yValues[0]+30);
        Label fnameLabel = new Label("Firstname");fnameLabel.setStyle("-fx-text-fill: White;");fnameLabel.relocate(xMargin, yValues[1]);
        Label lnameLabel = new Label("Lastname");lnameLabel.setStyle("-fx-text-fill: White;");lnameLabel.relocate(xMargin, yValues[1]+30);
        Label doorNoLabel = new Label("Door No.");doorNoLabel.setStyle("-fx-text-fill: White;");doorNoLabel.relocate(xMargin, yValues[2]);
        Label streetLabel = new Label("Street");streetLabel.setStyle("-fx-text-fill: White;");streetLabel.relocate(xMargin, yValues[2]+30);
        Label countyLabel = new Label("County");countyLabel.setStyle("-fx-text-fill: White;");countyLabel.relocate(xMargin, yValues[2]+60);
        Label cityLabel = new Label("City");cityLabel.setStyle("-fx-text-fill: White;");cityLabel.relocate(xMargin, yValues[2]+90);
        Label postcodeLabel = new Label("PostCode");postcodeLabel.setStyle("-fx-text-fill: White;");postcodeLabel.relocate(xMargin, yValues[2]+120);
        Label contactLabel = new Label("Contact No.");contactLabel.setStyle("-fx-text-fill: White;");contactLabel.relocate(xMargin, yValues[2]+150);
        paneRegister.getChildren().addAll(unameLabel,passLabel,fnameLabel,lnameLabel,doorNoLabel,streetLabel,countyLabel,cityLabel,postcodeLabel,contactLabel);
        registerScreen=new Scene(paneRegister);
        
        TextField unameR = new TextField();
        unameR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            {
                if(unameR.getText().length()>=12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField fnameR = new TextField();
        fnameR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            {
                if(fnameR.getText().length()>=12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField lnameR = new TextField();
        lnameR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            {
                if(lnameR.getText().length()>=12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField doorNoR = new TextField();
        doorNoR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            {
                if(doorNoR.getText().length()>5)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField streetR = new TextField();
        streetR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(streetR.getText().length()>=16)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField passR = new PasswordField();
        passR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(passR.getText().length()>=12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField countyR = new TextField();
        countyR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(countyR.getText().length()>=12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField cityR = new TextField();
        cityR.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(cityR.getText().length()>=12)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField postcode1R = new TextField();
        postcode1R.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(postcode1R.getText().length()>=4)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField postcode2R = new TextField();
        postcode2R.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(postcode2R.getText().length()>=4)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        TextField contactNo = new TextField();
        contactNo.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            { 
                if(contactNo.getText().length()>=11)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        
        paneRegister.getChildren().addAll(unameR,passR,fnameR,lnameR,doorNoR,streetR,countyR,cityR,postcode1R,postcode2R,contactNo);
        unameR.relocate(xMargin+100,yValues[0]); 
        passR.relocate(xMargin+100, yValues[0]+30);
        fnameR.relocate(xMargin+100, yValues[1]); 
        lnameR.relocate(xMargin+100, yValues[1]+30);
        doorNoR.relocate(xMargin+100, yValues[2]);
        streetR.relocate(xMargin+100, yValues[2]+30);
        countyR.relocate(xMargin+100, yValues[2]+60);
        cityR.relocate(xMargin+100, yValues[2]+90);
        postcode1R.relocate(xMargin+100, yValues[2]+120);
        postcode1R.setPrefSize(50,20);
        postcode2R.setPrefSize(50,20);
        postcode2R.relocate(xMargin+160, yValues[2]+120);//add textfields and password fields
        contactNo.relocate(xMargin+100, yValues[2]+150);
        
        
        Button RegisterSubmit = new Button("SUBMIT");
        RegisterSubmit.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event)
            {
               Stage message=new Stage();
                message.initStyle(StageStyle.UNDECORATED);//removes window decorations
                message.setTitle("Error");
                Pane msgDialog=new Pane();
                msgDialog.setPrefSize(200,70);
                Button ok3=new Button("Ok");
                Label msg3=new Label("");
                msgDialog.getChildren().addAll(ok3,msg3);
                msg3.relocate(10,10);
                ok3.relocate(90, 40);
                ok3.setOnAction(new EventHandler<ActionEvent>() { 
                        public void handle(ActionEvent event) {
                            message.hide();
                        }
                    });
                message.setScene(new Scene(msgDialog));
            
               logic logicR=new logic();
               if(unameR.getText().trim().trim().length()<6)
               {
                   msg3.setText("Username is too short");
                   message.show();
               }
               else if(logicR.duplicateCheck(unameR.getText().trim(),"username","account"))
               {
                    msg3.setText("Username already exists");
                    message.show();
               }
               else if(passR.getText().trim().trim().length()<8)
               {
                   msg3.setText("Password is too short");
                   message.show();
               }
               else if(unameR.getText().trim().length()<4)
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if(fnameR.getText().trim().length()<4)
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if(lnameR.getText().trim().length()<4)
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if(doorNoR.getText().trim().length()<1)
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if(streetR.getText().trim().length()<6)
               {
                  msg3.setText("One or more Fields are incorrectly filled");
                  message.show();
               }
               else if(countyR.getText().trim().length()<4)
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if(cityR.getText().trim().length()<4)
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if((postcode1R.getText().trim().length()<3)||(postcode2R.getText().trim().length()<3))
               {
                   msg3.setText("One or more Fields are incorrectly filled");
                   message.show();
               }
               else if(contactNo.getText().replaceAll("[^\\d.]", "").trim().length()<11)//removes non numbers
               {
                    msg3.setText("One or more Fields are incorrectly filled");
                    message.show();
               }
               else
               {
                    String[] address={doorNoR.getText().trim(),streetR.getText().trim(),countyR.getText().trim(),cityR.getText().trim(),postcode1R.getText().trim()+" "+postcode2R.getText().trim(),contactNo.getText()};
                    logicR.addAccount(unameR.getText().trim(),fnameR.getText().trim(),lnameR.getText().trim(),passR.getText().trim(),address);
                    msg3.setText("Account created, you can now log in.");
                    message.show();
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
                    contactNo.setText("");
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    mainStage.setScene(loginScreen);
               }
               
            }
        });
        
        Button ResetButton = new Button("RESET");
        ResetButton.setOnAction(new EventHandler<ActionEvent>()
        { 
            public void handle(ActionEvent event)
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
                contactNo.setText("");
            }
        });
        
        Button BackButton = new Button("BACK");
        BackButton.setOnAction(new EventHandler<ActionEvent>()
        { 
            public void handle(ActionEvent event)
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
                contactNo.setText("");
                mainStage.setScene(startScreen);
                
            }
        });       
        paneRegister.getChildren().addAll(RegisterSubmit,ResetButton,BackButton);
        RegisterSubmit.relocate(80, yValues[2]+200);
        ResetButton.relocate(200, yValues[2]+200);
        BackButton.relocate(320, yValues[2]+200);
    }
    
    public void accountPane(int ID)
    {
        paneAccount=new Pane();
        paneAccount.setPrefSize(600,400);
        paneAccount.setStyle("-fx-background-color: #000000;");
        ImageView logoM=new ImageView(images.getImage("logo_small"));
        logoM.setPreserveRatio(true);
        logoM.setFitHeight(50);
        Label logoMini= new Label("",logoM);

        logoMini.relocate(20, 20);
        Button prem=new Button();
        int check=accLogic.premCheck(ID);
        try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
        System.out.println(check);
        if (check==2)
            {
                prem.setText("Premium");
                Label expiry=new Label("Next Due : "+accLogic.stringGet(ID,"USERID","SUBSCRIPTION","DUEDATE"));
                try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                expiry.setStyle("-fx-text-fill: red;");
                expiry.relocate(420,70);
                paneAccount.getChildren().add(expiry);
                premstat=1;
            }
        else if(check==0)
            {
                prem.setText("error");
            }
        else
            {
                prem.setText("Subscribe");
            }
        
        prem.relocate(450,40);
        
        Label accountTitle=new Label(accLogic.stringGet(ID,"ID","ACCOUNT", "FIRSTNAME")+" "+accLogic.stringGet(ID,"ID","ACCOUNT", "LASTNAME"));
        accountTitle.setScaleX(1.5);accountTitle.setScaleY(1.5);
        accountTitle.relocate(140, 40);
        accountTitle.setStyle("-fx-text-fill: white;");
        try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }

        Button logOutButton=new Button("Log out");
        Button deviceButton=new Button("Devices");
        Button changeDetailsButton=new Button("Change Account Details");
        deviceButton.relocate(100,350);
        deviceButton.setOnAction(new EventHandler<ActionEvent>()
        { 
            public void handle(ActionEvent event)
            {
                devicePane();
                mainStage.setScene(deviceScreen);
            }
        });
        changeDetailsButton.relocate(200,350);
        logOutButton.relocate(400,350);
        logOutButton.setOnAction(new EventHandler<ActionEvent>()
        { 
            public void handle(ActionEvent event)
            {
                premstat=0;
                mainStage.setScene(startScreen);
            }
        });
        paneAccount.getChildren().addAll(prem,logoMini,accountTitle,logOutButton,deviceButton,changeDetailsButton);
        accountScreen=new Scene(paneAccount);
    }
    public void devicePane()
    {
        paneDevice=new Pane();
        paneDevice.setPrefSize(600,400);
        paneDevice.setStyle("-fx-background-color: #000000;");
        ImageView logoM=new ImageView(images.getImage("logo_small"));
        logoM.setPreserveRatio(true);
        logoM.setFitHeight(50);
        Label logoMini= new Label("",logoM);
        logoMini.relocate(20, 20);
        Button prem=new Button();
        int check=accLogic.premCheck(ID);
        System.out.println(check);
        if (check==2)
            {
                prem.setText("Premium");
                Label expiry=new Label("Next Due : "+accLogic.stringGet(ID,"USERID","SUBSCRIPTION","DUEDATE"));
                try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                expiry.setStyle("-fx-text-fill: red;");
                expiry.relocate(420,70);
                paneDevice.getChildren().add(expiry);
            }
        else if(check==0)
            {
                prem.setText("error");
            }
        else
            {
                prem.setText("Subscribe");
            }
        
        prem.relocate(450,40);
        
        Label accountTitle=new Label(accLogic.stringGet(ID,"ID","ACCOUNT", "FIRSTNAME")+" "+accLogic.stringGet(ID,"ID","ACCOUNT", "LASTNAME"));
        try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
        accountTitle.setScaleX(1.5);accountTitle.setScaleY(1.5);
        accountTitle.relocate(140, 40);
        accountTitle.setStyle("-fx-text-fill: white;");
        Button logOutButton=new Button("Log out");
        Button changeDetailsButton=new Button("Change Account Details");
        changeDetailsButton.relocate(200,350);
        logOutButton.relocate(400,350);
        logOutButton.setOnAction(new EventHandler<ActionEvent>()
        { 
            @Override
            public void handle(ActionEvent event)
            {
                ID=9999;
                mainStage.setScene(startScreen);
            }
        });
        paneDevice.getChildren().addAll(prem,logoMini,accountTitle,logOutButton,changeDetailsButton);
        
        ResultSet deviceList=accLogic.resultGet("SELECT DEVICEID,DEVICENAME,DEVICETYPE,DATE FROM DEVICE WHERE DEVICEOWNER='"+ID+"'");    
        TableView deviceTable = new TableView();
        deviceTable.setEditable(false);
        TableColumn col1 = new TableColumn("Device Name");
        col1.setMinWidth(150);
        col1.setCellValueFactory(new PropertyValueFactory<>("deviceName"));
        TableColumn col2 = new TableColumn("Device Type");
        col2.setMinWidth(100);
        col2.setCellValueFactory(new PropertyValueFactory<>("deviceType"));
        TableColumn col3 = new TableColumn("Days since added");
        col3.setMinWidth(130);
        col3.setCellValueFactory(new PropertyValueFactory<>("deviceDate"));
        deviceTable.getColumns().addAll(col1,col2,col3);
        deviceTable.setPrefSize(385,200);
        deviceTable.relocate(60,100);
        ObservableList<deviceInfo> deviceData = accLogic.makeTableInfo(ID);
        deviceTable.setItems(deviceData);
        paneDevice.getChildren().add(deviceTable);
        try {
            deviceList.close();
        } catch (SQLException ex) {
            Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Button[][] deviceButtons=new Button[5][2];
        for(int i=0;i<5;i++)
        {
            deviceButtons[i][0]=new Button("Modify");
            deviceButtons[i][1]=new Button("Delete");
            deviceButtons[i][0].relocate(450,120+(i*25));
            deviceButtons[i][1].relocate(520,120+(i*25));
            if(deviceData.get(i).getDeviceDate()>=30)
            {
                paneDevice.getChildren().addAll(deviceButtons[i][0],deviceButtons[i][1]);
            }
            else
            {
                paneDevice.getChildren().add(deviceButtons[i][0]);
            }
            
        }
        deviceButtons[0][0].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event)
                {
                    int devID=deviceData.get(0).getDeviceID();
                    deviceModify(devID);
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        devicePane();
                    }
                }
            });
        deviceButtons[1][0].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event)
                {
                    int devID=deviceData.get(1).getDeviceID();
                    deviceModify(devID);
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        devicePane();
                    }
                }
            });
        deviceButtons[2][0].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event)
                {
                    int devID=deviceData.get(2).getDeviceID();
                    deviceModify(devID);
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        devicePane();
                    }
                }
            });
        deviceButtons[3][0].setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    int devID=deviceData.get(3).getDeviceID();
                    deviceModify(devID);
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        devicePane();
                    }
                }
            });
        deviceButtons[4][0].setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    int devID=deviceData.get(5).getDeviceID();
                    deviceModify(devID);
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        devicePane();
                    }
                }
            });
        
        
        
        deviceScreen=new Scene(paneDevice);
        
    }
    
    public void deviceModify(int pos)
    {
        System.out.println(pos);
        Stage modifyName=new Stage();
        modifyName.setTitle("Input name you want to change name of device to");
        Pane dMod=new Pane();
        dMod.setPrefSize(250,150);
        dMod.setStyle("-fx-background-color: #000000;");
        TextField nameChange = new TextField();
        nameChange.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            { 
                if(nameChange.getText().length()>=16)
                {ke.consume();
                System.out.println("consumed");}
            }  
        });
        Button confirm=new Button("Confirm");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    String stat="UPDATE DEVICE SET DEVICENAME='"+nameChange.getText()+"' WHERE DEVICEID='"+pos+"'";
                    accLogic.changeRecord(stat);
                    try {
                        accLogic.data.conn.close();
                        } catch (SQLException ex) {
                        Logger.getLogger(splashScreen1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    modifyName.close();
                }
            });
        
        dMod.getChildren().addAll(nameChange,confirm);
        confirm.relocate(90, 40);
        nameChange.relocate(90,10);
        modifyName.setScene(new Scene(dMod));
        modifyName.show();
    }
}
