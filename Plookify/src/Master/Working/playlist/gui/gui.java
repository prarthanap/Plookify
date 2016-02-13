
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

public class gui extends Application implements EventHandler<ActionEvent>{
    
    Button newPlaylist;
    
    public static void main(String[] args){
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Playlist");
        newPlaylist = new Button();
        newPlaylist.setText("New Playlist");
        
        newPlaylist.setOnAction(this);
    
        StackPane layout = new StackPane();
        layout.getChildren().add(newPlaylist);
        
        Scene scene = new Scene(layout,1100,600);
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }

    @Override
    public void handle(ActionEvent t) {
        //
    }
    
    
}
