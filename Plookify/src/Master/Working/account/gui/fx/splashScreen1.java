/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.gui.imageLib2;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jll30
 */
public class splashScreen1 extends Application {
    
    private Pane pane1;
    private Pane pane2;
    private Pane pane3;
    public Scene loginScreen;
    private PaneGen pG1=new PaneGen();
    @Override
    public void start(Stage splashStage) {
        pane1=pG1.splashPane();
        pane2=pG1.startPane();
        pane3=pG1.loginPane();
        Scene loadingSplash = new Scene(pane1, 300, 350);
        Scene startScreen = new Scene(pane2,400,200);
        loginScreen=new Scene(pane3,400,300);
        splashStage.setTitle("Plookify");
        splashStage.setScene(loadingSplash);
        splashStage.show();
        PauseTransition pause=new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event->splashStage.setScene(startScreen));
        pause.play();


    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
