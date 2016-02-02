package Master.Working.social.GUI;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author Hamza
 */
public class gui extends Application {
    
    Scene scene;
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        scene = new Scene(root, 300, 250);

        /**
         * Load CSS from a local file
         */
        Button file = new Button("Test");
        file.setOnAction(e -> {
            File f = new File("test.css");
            scene.getStylesheets().clear();
            scene.getStylesheets().add("DynamicCss.css");
        });
        
        /**
         * Load CSS from URL
         */
        Button url = new Button("Load From URL");
        url.setOnAction(e -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("http://www.jpedal.org/simon/dynamiccss/webcss.css");
        });
        
        /**
         * Remove all CSS
         */
        Button clear = new Button();
        clear.setText("Clear");
        clear.setOnAction(e -> {scene.getStylesheets().clear();});
        
        root.getChildren().addAll(file, url, clear);
        
        root.setAlignment(Pos.CENTER);
        root.getChildren().stream().forEach(btn->{((Button)btn).setPrefWidth(100);});
        
        primaryStage.setTitle("Dynamic CSS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
