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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jll30
 */
public class startRun extends Application 
{
    imageLib2 images=new imageLib2();
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent splash = FXMLLoader.load(getClass().getResource("acc.fxml"));
        Scene scene1 = new Scene(splash);
        Parent start1 = FXMLLoader.load(getClass().getResource("screenStart.fxml"));
        Scene scene2 = new Scene(start1);
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();
        PauseTransition loading=new PauseTransition(Duration.millis(1000));
        loading.setOnFinished(event->stage.setScene(scene2));
        loading.play();
        
    }

    public static void main(String[] args)
    {
        launch(args); 
    }
}