/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.gui;

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
    @Override
    public void start(Stage primaryStage) {  
        
        /////////////////////////Buttons/////////////////////////////////
        //Button to view Radio Playlist
        Button ViewRadio = new Button();
        ViewRadio.setText("View Radio Playlist");
        //Button to go back to Now Playing
        Button goBack = new Button();
        goBack.setText("<- Go Back");
        //Button to Save As Playlist
        Button savePlaylist = new Button();
        savePlaylist.setText("Save Radio as Playlist");
        
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(0, 10, 0, 10));
        root.add(ViewRadio, 50, 20);
        GridPane root2 = new GridPane();
        root2.setHgap(10);
        root2.setVgap(10);
        root2.setPadding(new Insets(0, 10, 0, 10));
        root2.add(goBack, 100, 2);
        root2.add(savePlaylist, 80, 40);
        
        Scene scene = new Scene(root, 1100, 600);
        scene.getStylesheets().add("test.css");
        Scene scene2 = new Scene(root2, 1100, 600);
        scene2.getStylesheets().add("test.css");
        
        ViewRadio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    primaryStage.setScene(scene2); 
            }
        });
        
        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    primaryStage.setScene(scene); 
            }
        });
        
        primaryStage.setTitle("Plookify");
        primaryStage.setScene(scene);
        primaryStage.show();
     
    }
    
    
    public static void main (String[] args) {
        launch(args);
        
        // Generate Radio Channel
       /* RadioChannel radioCH = new RadioChannel(Track);
        String searchArtist = "";
        Track radioTrack = new Track()
        for (int i = 0; i<=9; i++)
        {
            searchArtist = radioCH.randomArtist();
            radioTrack = radioCH.randomTrack();
            radioCH.addToRadio(track,i);
        }
        */
        //...
    }
}