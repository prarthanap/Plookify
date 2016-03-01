
package Master.Working.playlist.gui;
/**
 *
 * @author Edgar
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class gui extends Application implements EventHandler<ActionEvent>{
    
    Button newPlaylist;
    
    public static void main(String[] args){
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("playlist.fxml"));
        Scene scene = new Scene(root,1100,600);
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }

    @Override
    public void handle(ActionEvent t) {
        
    }
    
    
}
