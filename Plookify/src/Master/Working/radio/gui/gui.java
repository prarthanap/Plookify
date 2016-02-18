/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.gui;

import Master.Working.radio.logic.logic;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author ec14082
 */
public class gui extends Application {
    Button ViewRadio, goBack, savePlaylist;
    GridPane root, root2;
    Scene scene, scene2;
    Stage currentStage;
    
    @Override
    public void start(Stage primaryStage) {  
        currentStage = primaryStage;
        
        /////////////////////////Buttons/////////////////////////////////
        //Button to view Radio Playlist
        ViewRadio = new Button();
        ViewRadio.setText("View Radio Playlist");
        ViewRadio.setOnAction(e-> ButtonPressed(e));
        //Button to go back to Now Playing
        goBack = new Button();
        goBack.setText("<- Go Back");
        goBack.setOnAction(e-> ButtonPressed(e));
        //Button to Save As Playlist
        savePlaylist = new Button();
        savePlaylist.setText("Save Radio as Playlist");
        savePlaylist.setOnAction(e-> ButtonPressed(e));
        
        root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.add(ViewRadio, 50, 20);
        root2 = new GridPane();
        root2.setHgap(10);
        root2.setVgap(10);
        root2.setPadding(new Insets(10, 10, 10, 10));
        root2.add(goBack, 70, 2);
        root2.add(savePlaylist, 40, 40);
        
        scene = new Scene(root, 1100, 600);
        scene.getStylesheets().add("test.css");
        scene2 = new Scene(root2, 1100, 600);
        scene2.getStylesheets().add("test.css");
        
        primaryStage.setTitle("Plookify");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public void ButtonPressed(ActionEvent e) {
        if (e.getSource() == ViewRadio) {
            currentStage.setScene(scene2); 
        }
        else if (e.getSource() == goBack) {
            currentStage.setScene(scene); 
        }
        else {}
    }
    
    public static void main (String[] args) {
        launch(args);
        
        // Generate Radio Channel
        logic radioCH = new logic();
        String searchArtist = "";
        String radioTrack = "";
       /* for (int i = 0; i<=9; i++)
        {*/
            System.out.println(radioCH.randomArtist());
            //searchArtist = radioCH.randomArtist();
            //radioTrack = radioCH.randomTrack();
            //radioCH.addToRadio(track,i);
        //}
        
        //...
    }
}