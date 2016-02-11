/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Hamza
 */
public class menu extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Plookify");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Username:");
        nameLabel.setId("bold-label");
        GridPane.setConstraints(nameLabel, 20, 20);

        //Name Input
        TextField nameInput = new TextField("Hamza");
        nameInput.setPromptText("name");
        GridPane.setConstraints(nameInput, 21, 20);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 20, 21);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 21, 21);

        //Login
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 21, 22);
        
        
        //Sign up
        Button signUpButton = new Button("Sign Up");
        signUpButton.getStyleClass().add("button-blue");
        GridPane.setConstraints(signUpButton, 21, 23);

        //Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, signUpButton);

        Scene scene = new Scene(grid, 1100, 600);
        scene.getStylesheets().add("test.css");
        window.setScene(scene);
        window.show();
    }


}
