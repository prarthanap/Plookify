/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import Master.Working.social.Logic.logic;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class LogOnController implements Initializable {
    private final logic logic1=new logic();
    private final int[] dumpster1={-500,500};
    private final int[] dumpster2={-1000,500};
    private int userID=9999;
    
    
    @FXML private Pane startPane;
    @FXML private Button logButton;
    
    @FXML private Pane loginPane;
    @FXML private Button submitButton, backButton, resetButton;
    @FXML private TextField loginUnameField;
    @FXML private PasswordField loginPassField;

    @FXML private Pane dialogPane;
    @FXML private Label loginDialogMessage;
    @FXML private Button loginOkButton;   
    
    /**
     * Initialises the controller class.
     */
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
            System.out.println("pass");
            try {   int pass=userID;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));     
                    
                    Parent root = (Parent)loader.load();    
                    Scene sceneA = new Scene(root);
                    Stage oldstage = (Stage) loginPane.getScene().getWindow();
                    Stage theStage = new Stage();
                    theStage.setScene(sceneA);
                    theStage.setResizable(false);
                    GuiController controller = loader.getController();
                    controller.setUser(pass);
                    oldstage.close();
                    theStage.show();
                } catch (IOException ex)
                {
                Logger.getLogger(LogOnController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void dialogOkButton(ActionEvent event)
    {
        dialogPane.relocate(dumpster1[0],dumpster1[1]);
    } 
    
}
