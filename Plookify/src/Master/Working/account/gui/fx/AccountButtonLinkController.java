/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class AccountButtonLinkController implements Initializable {

    private int userID=9999;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void pressToAccount()
    {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("screenAccount.fxml"));         
                    Parent root = (Parent)loader.load();    
                    Scene sceneA = new Scene(root);
                    Stage theStage = new Stage();
                    theStage.setScene(sceneA);
                    ScreenAccountController controller = loader.getController();
                    controller.setUser(userID);
                    controller.initVariables();
                    theStage.show();
                } catch (IOException ex)
                {
                Logger.getLogger(AccountLoginController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error @ loading account screen");
                }
    }
    
    public void setID(int value)
    {
        this.userID=value;
    }
    
}
