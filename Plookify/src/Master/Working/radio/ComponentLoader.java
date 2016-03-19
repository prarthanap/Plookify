/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samad
 */
public class ComponentLoader extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {  
        Parent root = FXMLLoader.load(getClass().getResource("/Master/Working/radio/gui/radioMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}

