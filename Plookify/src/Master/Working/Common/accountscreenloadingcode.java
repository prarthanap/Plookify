/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.gui.fx.ScreenAccountController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jll30
 * 
 * to store account screen loading code, not for running directly
 */
public class accountscreenloadingcode
{
    int ID=9999;//ignore this, the screen controller should have its own ID variable
    
    public void loading() throws IOException//put this code into the account button onaction handler
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("screenAccount.fxml"));         
        Parent root = (Parent)loader.load();    
        Scene sceneA = new Scene(root);
        Stage accStage = new Stage();
        accStage.setScene(sceneA);
        ScreenAccountController controller = loader.getController();
        controller.setUser(ID);
        controller.initVariables();
        accStage.show();
    }
        
}
