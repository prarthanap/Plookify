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
import javafx.scene.text.TextAlignment;
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
    private logic accLogic=new logic();
    private int ID=9999;
    
    @Override
    public void start(Stage splashStage) {
        mainStage=splashStage;
        mainStage.setResizable(false);
        splashPane();
        startPane();
        loginPane();
        registerPane();
        splashScreen = new Scene(paneSplash);
        startScreen = new Scene(paneStart);
        loginScreen=new Scene(paneLogin);
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
                ID=accLogic.authCheck(uname,passwd);
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
                            accountPane(ID);
                            accountScreen=new Scene(paneAccount);
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
        submitButton.relocate(80,260);
        resetButton.relocate(180,260);
        backButton.relocate(280,260);
        
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
        logo1.relocate(200, 10);
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
        paneRegister.getChildren().addAll(unameLabel,passLabel,fnameLabel,lnameLabel,doorNoLabel,streetLabel,countyLabel,cityLabel,postcodeLabel);
        registerScreen=new Scene(paneRegister);
        
        /*TextField unameR = new TextField();
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
                if (contactNo.getText().length() >=11 ) // limit textfield to 11 characters
                {e.consume();}
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
               else if(contactNo.getText().replaceAll("[^\\d.]", "").trim().length()<11)//removes non numbers
               {
                    JOptionPane.showMessageDialog(null,"One or more Fields are incorrectly filled");
               }
               else
               {
                   String[] address={doorNoR.getText().trim(),streetR.getText().trim(),countyR.getText().trim(),cityR.getText().trim(),postcode1R.getText().trim()+" "+postcode2R.getText().trim(),contactNo.getText()};
                   logicR.addAccount(unameR.getText().trim(),fnameR.getText().trim(),lnameR.getText().trim(),passR.getText().trim(),address);
                   JOptionPane.showMessageDialog(null,"Account created, you can now log in.");
                   StartScreen start1=new StartScreen();
                   regScreen.dispose();
                   start1.startUI();
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
        mainPanel.add(BackButton).setBounds(320, yValues[2]+200, 100, 20);//adds buttons*/
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
        System.out.println(check);
        if (check==2)
            {
                prem.setText("Premium");
                Label expiry=new Label("Next Due :"+accLogic.stringGet(ID,"USERID","SUBSCRIPTION","DUEDATE"));
                expiry.setStyle("-fx-text-fill: red;");
                expiry.relocate(420,70);
                paneAccount.getChildren().add(expiry);
            }
        else if(check==0)
            {
                prem.setText("error");
            }
        else
            {
                prem.setText("Free");
            }
        
        prem.relocate(450,40);
        
        Label accountTitle=new Label(accLogic.stringGet(ID,"ID","ACCOUNT", "FIRSTNAME")+" "+accLogic.stringGet(ID,"ID","ACCOUNT", "LASTNAME"));
        accountTitle.setScaleX(1.5);accountTitle.setScaleY(1.5);
        accountTitle.relocate(140, 40);
        accountTitle.setStyle("-fx-text-fill: white;");

        Button logOutButton=new Button("Log out");
        Button deviceButton=new Button("Devices");
        Button changeDetailsButton=new Button("Change Account Details");
        deviceButton.relocate(100,300);
        changeDetailsButton.relocate(200,300);
        logOutButton.relocate(400,300);
        paneAccount.getChildren().addAll(prem,logoMini,accountTitle,logOutButton,deviceButton,changeDetailsButton);
    }
}
