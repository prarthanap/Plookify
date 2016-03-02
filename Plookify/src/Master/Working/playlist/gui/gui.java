
package Master.Working.playlist.gui;
/**
 *
 * @author Edgar
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import static javafx.application.Application.launch;

public class gui extends Application{
    
    
    public static void main(String[] args){
    launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("playistScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       
    } 
}
