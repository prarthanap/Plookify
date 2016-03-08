/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.logic.logic;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class AccountLoginController implements Initializable {
    private final logic logic1=new logic();
    private final int[] dumpster1={-500,500};
    private final int[] dumpster2={-1000,500};
    private int userID=9999;
    
    @FXML private Pane startPane;//400,200

    @FXML private Pane dialogPane;
    @FXML private Label loginDialogMessage;
    
    @FXML private Pane loginPane;//400,200
    @FXML private TextField loginUnameField;
    @FXML private PasswordField loginPassField;
    @FXML private Button regButton;
    @FXML private Button logButton;
    
    @FXML private Pane registerPane;//size 500,600
    @FXML private TextField unameFieldR;
    @FXML private TextField passFieldR;
    @FXML private TextField fnameR;
    @FXML private TextField lnameR;
    @FXML private TextField doorNoR;
    @FXML private TextField streetR;
    @FXML private TextField countyR;
    @FXML private TextField cityR;
    @FXML private TextField postcode1R;
    @FXML private TextField postcode2R;
    @FXML private TextField contactNoR;
    @FXML private Button backButtonR;
    @FXML private ComboBox subMonths;
    @FXML private Pane subDialogPane;
    @FXML private Pane dialogPaneReg;
    @FXML private Label regDialogMsg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void startLoginPressed(ActionEvent event)
    {
        System.out.println("Pressed LOGIN!");
        loginPane.relocate(0,0);
        startPane.relocate(0,0);
        
    }
    @FXML
    private void startRegisterPressed(ActionEvent event) throws IOException
    {
        Parent reg1 = FXMLLoader.load(getClass().getResource("screenRegister.fxml"));
        Scene regScene = new Scene(reg1);
        Stage regStage=new Stage();
        regStage.setScene(regScene);
        logButton.setDisable(true);
        regButton.setDisable(true);
        regStage.showAndWait();
        logButton.setDisable(false);
        regButton.setDisable(false);
        
    }
    @FXML
    private void dialogOkButton(ActionEvent event)
    {
        dialogPane.relocate(dumpster1[0],dumpster1[1]);
    }
    
    @FXML
    private void loginPressedSubmit(ActionEvent event)
    {
        userID=logic1.data.authCheckD(loginUnameField.getText(),loginPassField.getText());
        logic1.data.conClose();
        if(userID==9999)
        {
            loginDialogMessage.setText("Error! Incorrect Username and/or Password");
            loginPassField.setText("");
            dialogPane.relocate(100,150);
            dialogPane.toFront();
        }
        else
        {
            try {   int pass=userID;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("screenAccount.fxml"));     
                    
                    Parent root = (Parent)loader.load();    
                    Scene sceneA = new Scene(root);
                    Stage oldstage = (Stage) loginPane.getScene().getWindow();
                    Stage theStage = new Stage();
                    theStage.setScene(sceneA);
                    ScreenAccountController controller = loader.getController();
                    controller.setUser(pass);
                    controller.initVariables();
                    oldstage.close();
                    theStage.show();
                } catch (IOException ex)
                {
                Logger.getLogger(AccountLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    @FXML
     private void loginPressedReset()
     {
         loginUnameField.setText("");
         loginPassField.setText("");
     }
     @FXML
     private void loginPressedBack()
     {
         loginPane.relocate(dumpster1[0],dumpster1[1]);
         startPane.relocate(0,0);
     }
     
     @FXML
     private void unameTrim(KeyEvent ke)
     {
         if(unameFieldR.getText().length()>=12)
            {ke.consume();
            System.out.println("consumed");
            }
     }
     @FXML
     private void passTrim(KeyEvent ke)
     {
        if(passFieldR.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
           }
     }
     @FXML
     private void fnameTrim(KeyEvent ke)
     {
        if(fnameR.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
           }
     }
     @FXML
     private void lnameTrim(KeyEvent ke)
     {
        if(lnameR.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
           }         
     }
     @FXML
     private void doorNoTrim(KeyEvent ke)
     {
        if(doorNoR.getText().length()>=5)
           {
               ke.consume();
               System.out.println("consumed");
           }        
     }
     @FXML
     private void streetTrim(KeyEvent ke)
     {
        if(streetR.getText().length()>=16)
           {
               ke.consume();
               System.out.println("consumed");
           }         
     }
     @FXML
     private void countyTrim(KeyEvent ke)
     {
        if(countyR.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
           }         
     }
     @FXML
     private void cityTrim(KeyEvent ke)
     {
        if(cityR.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
           }         
     }
     @FXML
     private void postcodeTrim1(KeyEvent ke)
     {
        if(postcode1R.getText().length()>=4)
           {
               ke.consume();
               System.out.println("consumed @postcode2");
           }         
     }
     @FXML
     private void postcodeTrim2(KeyEvent ke)
     {
        if(postcode2R.getText().length()>=4)
           {
               ke.consume();
               System.out.println("consumed @postcode2");
           }         
     }
     @FXML
     private void contactNoTrim(KeyEvent ke)
     {
        contactNoR.setText(contactNoR.getText().replaceAll("[^\\d.]", "").trim());
        if(contactNoR.getText().length()>=11)
           {
               ke.consume();
               System.out.println("consumed @contactNo");
           }         
     }
     @FXML
     private void submitButtonRPressed(ActionEvent event)
     {
         if(unameFieldR.getText().trim().length()<6)
               {
                   loginDialogMessage.setText("Username too short!(minimum 7 characters)");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(logic1.duplicateCheck(unameFieldR.getText().trim(),"username","account"))
               {
                   loginDialogMessage.setText("Username already exists.");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(passFieldR.getText().trim().trim().length()<7)
               {
                   loginDialogMessage.setText("Password too short!(minimum 8 characters)");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(fnameR.getText().trim().length()<4)
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(lnameR.getText().trim().length()<4)
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(doorNoR.getText().trim().length()<1)
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(streetR.getText().trim().length()<6)
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(countyR.getText().trim().length()<4)
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(cityR.getText().trim().length()<4)
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if((postcode1R.getText().trim().length()<3)||(postcode2R.getText().trim().length()<3))
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else if(contactNoR.getText().replaceAll("[^\\d.]", "").trim().length()<11)//removes non numbers
               {
                   loginDialogMessage.setText("One or more Fields are incorrectly filled");
                   dialogPane.relocate(100,150);
                   dialogPane.toFront();
               }
               else
               {
                    String[] address={doorNoR.getText().trim(),streetR.getText().trim(),countyR.getText().trim(),cityR.getText().trim(),postcode1R.getText().trim()+" "+postcode2R.getText().trim(),contactNoR.getText()};
                    logic1.addAccount(unameFieldR.getText().trim(),fnameR.getText().trim(),lnameR.getText().trim(),passFieldR.getText().trim(),address);
                    fnameR.setText(""); 
                    lnameR.setText("");
                    doorNoR.setText("");
                    streetR.setText("");
                    countyR.setText("");
                    cityR.setText("");
                    postcode2R.setText("");
                    postcode1R.setText("");
                    contactNoR.setText("");
                    regDialogMsg.setText("Account Created!");
                    dialogPaneReg.relocate(100,150);
                    dialogPaneReg.toFront();
               }
               
     }
             
     @FXML
     private void resetButtonRPressed(ActionEvent event)
     {
        unameFieldR.setText("");
        passFieldR.setText("");
        fnameR.setText(""); 
        lnameR.setText("");
        doorNoR.setText("");
        streetR.setText("");
        countyR.setText("");
        cityR.setText("");
        postcode2R.setText("");
        postcode1R.setText("");
        contactNoR.setText("");
     }
             
     @FXML
     private void backButtonRPressed(ActionEvent event)
     {
         System.out.println("Pressed Back from register");
         Stage stage2 = (Stage) backButtonR.getScene().getWindow();
         stage2.close();
     }
     
     @FXML
     private void subConfirmPressed(ActionEvent event)
     {
         int idSub=logic1.data.authCheckD(unameFieldR.getText(), passFieldR.getText());
         int subVal=Integer.parseInt((String)subMonths.getValue());
         logic1.newSubscribe(idSub, subVal);
         subDialogPane.relocate(dumpster1[0],dumpster1[1]);
         unameFieldR.setText("");
         passFieldR.setText("");
         Stage stage2 = (Stage) backButtonR.getScene().getWindow();
         stage2.close();
     }
     @FXML
     private void regDialogOkButton(ActionEvent event)
     {
         dialogPaneReg.relocate(dumpster1[0],dumpster1[1]);
         subDialogPane.relocate(95,290);
     }
             
}
