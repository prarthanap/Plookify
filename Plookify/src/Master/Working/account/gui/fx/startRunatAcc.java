/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.gui.imageLib2;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jll30
 */
public class startRunatAcc extends Application 
{
    imageLib2 images=new imageLib2();
    @Override
    public void start(Stage stage) throws Exception 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("screenAccount.fxml"));            
        Parent root = (Parent)loader.load();    
        Scene sceneA = new Scene(root);
        stage.setScene(sceneA);
        ScreenAccountController controller = loader.getController();
        controller.setUser(5);
        controller.initVariables();
        stage.show();
        
    }

    public static void main(String[] args)
    {
        launch(args); 
    }
}
