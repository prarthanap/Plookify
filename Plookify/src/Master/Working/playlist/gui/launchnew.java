/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Edgar
 */
public class launchnew extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("playlist.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    } 
    
    public static void main(String[] args){
    launch(args);
    }
    
    
}
