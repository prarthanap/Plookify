/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Hamza
 */
public class window extends Application implements EventHandler<ActionEvent> {

    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Plookify");
        button = new Button();
        button.setText("Working");

        //This class will handle the button events
        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 1100, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //When button is clicked, handle() gets called
    //Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) 
            System.out.println("YES!");
    }

}