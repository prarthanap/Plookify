package Master.Working.social.GUI;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class gui extends Application implements EventHandler<ActionEvent> {
    
    Scene scene;
    Button close;
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        scene = new Scene(root, 1100, 600);

        close = new Button("Close");        
        close.setOnAction(this);
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
        
        root.getChildren().addAll(close, file, url, clear);
        
        root.setAlignment(Pos.CENTER);
        root.getChildren().stream().forEach(btn->{((Button)btn).setPrefWidth(100);});
        
        primaryStage.setTitle("Plookify");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==close)
        {
          System.out.println("Closing app");
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
