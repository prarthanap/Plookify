import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TrackTable extends Application
{
  public static void main(String[] args) {
    launch(args);

   }


   @Override
  public void start(Stage primaryStage) {

	TableView trackTable = new TableView();
	trackTable.setEditable(true);

TableColumn trackNameCol = new TableColumn("Track name");
TableColumn timeCol = new TableColumn("Duration");
TableColumn artistCol = new TableColumn("Artist");
TableColumn albumCol = new TableColumn("Album");
TableColumn genreCol = new TableColumn("Genre");

trackTable.getColumns().addAll(trackNameCol, timeCol, artistCol, albumCol, genreCol);


	StackPane root = new StackPane();
    root.getChildren().add(trackTable);
    primaryStage.setScene(new Scene(root, 200, 250));
    primaryStage.show();
  }
}


