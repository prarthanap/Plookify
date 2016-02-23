/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.radio.gui;

import Master.Working.radio.logic.logic;
import static java.lang.Math.E;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;

/**
 *
 * @author ec14082
 */
public class gui extends Application {
    Button ViewRadio, goBack, savePlaylist;
    GridPane root, root2;
    Scene scene, scene2;
    Stage currentStage;
    
    static String[] radioCh;
    static String[] artist = new String[10];
    
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
        
        //////////////////////TableForRadio////////////////////////////////
        TableView<String> tv = new TableView(FXCollections.observableArrayList(
                new ArrayList<>(Arrays.asList(radioCh))));
        TableColumn<String, String> tc = new TableColumn<>("Track Name");
        tc.setCellValueFactory((p) -> {
            return new ReadOnlyStringWrapper(p.getValue());
        });
        TableView<String> tv2 = new TableView(FXCollections.observableArrayList(
                new ArrayList<>(Arrays.asList(artist))));
        TableColumn<String, String> tc2 = new TableColumn<>("Artist");
        tc2.setCellValueFactory((p) -> {
            return new ReadOnlyStringWrapper(p.getValue()); });
        tv.getColumns().addAll(tc,tc2);
        tv2.getColumns().add(tc2);
        tv.setFixedCellSize(25);
        tv.prefHeightProperty().bind(Bindings.size(tv.getItems()).multiply(tv.getFixedCellSize()).add(30));
        ///////////////////////////////////////////////////////////////////
        
        root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 0, 10, 10));
        root.add(ViewRadio, 50, 20);
        root2 = new GridPane();
        root2.setHgap(10);
        root2.setVgap(10);
        root2.setPadding(new Insets(10, 0, 10, 10));
        root2.add(goBack, 70, 2);
        root2.add(tv, 30, 10);
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
        // Generate Radio Channel
        logic test = new logic();
        String searchArtist = "";
        String radioTrack = "";
        for (int i = 0; i<=9; i++)
        {
            searchArtist = test.randomArtist();
            radioTrack = test.randomTrack();
            artist[i] = searchArtist;
            test.addToRadio(radioTrack,i);
        }
        radioCh = test.getRadio();
        test.printRadio();
        
        launch(args);
    }
}