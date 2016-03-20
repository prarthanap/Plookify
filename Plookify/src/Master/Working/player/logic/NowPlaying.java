import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author prarthana
 */
public class NowPlaying extends Application {

    
    BorderPane mainLayout;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NowPlaying.class.getResource("player.fxml"));
        BorderPane mainItems = loader.load();
        mainLayout.setCenter(mainItems);
        
      /*  Parent root = FXMLLoader.load(getClass().getResource("player.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/

    }

    public static void main(String[] args) {

        launch(args);
    }
}
