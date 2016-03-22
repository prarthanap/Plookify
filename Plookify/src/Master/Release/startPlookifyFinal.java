/*
 * java file to start it all!
 */
package Master.Release;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jer Le
 */
public class startPlookifyFinal extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent splash = FXMLLoader.load(getClass().getResource("/Master/Working/account/gui/fx/acc.fxml"));
        Scene scene1 = new Scene(splash);
        Parent start1 = FXMLLoader.load(getClass().getResource("/Master/Working/account/gui/fx/screenStart.fxml"));
        Scene scene2 = new Scene(start1);
        stage.setScene(scene1);
        stage.setTitle("Plookify");
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