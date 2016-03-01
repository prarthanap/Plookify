/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 *
 * @author Hamza
 */
public class Run extends Application {

    public static Stage viewFriendStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        viewFriendStage = primaryStage;
        Parent root_friend = FXMLLoader.load(getClass().getResource("FriendListBuilder.fxml"));
        Scene friendListscene = new Scene(root_friend);
        viewFriendStage.setScene(friendListscene);
        viewFriendStage.setResizable(false);
        viewFriendStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
    //viewFriend Methods
    
}
