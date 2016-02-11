import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.*;
import java.sql.*;


public class NewClass extends Application {

    Stage window;
    TableView<Product> table;
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Plookify");

        
         ObservableList<Product> products = FXCollections.observableArrayList();
        
        //Name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Track Name");
       nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        
       TableColumn<Product, String> name1Column = new TableColumn<>("Track Name");
        name1Column.setMinWidth(200);
        name1Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Artist column
        TableColumn<Product, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(200);
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        //duration column
        TableColumn<Product, Double> durationColumn = new TableColumn<>("Duration");
        durationColumn.setMinWidth(100);
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        //genre column
        TableColumn<Product, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(200);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        //album column
        TableColumn<Product, String> albumColumn = new TableColumn<>("Album");
        albumColumn.setMinWidth(200);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));


        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn,  artistColumn, durationColumn, genreColumn, albumColumn);


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(50, 10, 10, 10));
        vBox.getChildren().addAll(table);

        Button load = new Button("Load Table");
        load.setOnAction(e -> {
            try{
                
                String query = "select * from TRACKS";
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    products.add(new Product(
                     rs.getString("TRACKNAME"),
                     rs.getString("ARTIST"),
                     rs.getString("DURATION"),
                     rs.getString("GENRE"),
                     rs.getString("ALBUM")
                    ));
                    
                    table.setItems(products);
                }
                pst.close();
                rs.close();
                
            }catch(Exception e2){
                System.err.println(e2);
            }
            
            
        });
        
        
        
        Scene scene = new Scene(vBox, 1100, 600);  

        scene.getStylesheets().add("test.css");
        window.setScene(scene);
        window.show();
    }

    //Get all of the products
  // public ObservableList<Product> getProduct(){
       // ObservableList<Product> products = FXCollections.observableArrayList();


        //products.add(new Product("hslo" , "jhj", " Neyo", 4.05, "R&B", "Years"));
        //products.add(new Product("pok", "hjhjk"," Neyo", 4.05, "R&B", "Years"));
        //return products;
       
    
//}

}