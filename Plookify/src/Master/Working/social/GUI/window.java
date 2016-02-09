package Master.Working.social.GUI;

import java.awt.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Hamza
 */
public class window extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Button 1
        Button label1 = new Button("Welcome to the first scene!");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().add(button1);
      
        scene1 = new Scene(layout1, 600, 300);
        


        //Button 2
        Button button2 = new Button("This sucks, go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }

}