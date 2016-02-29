/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

import Master.Working.player.gui.Tracks;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;




/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class SocialController implements Initializable {
@FXML
private Button premium;
@FXML
private TableView<Account> table;
@FXML
private TableColumn User;
@FXML
private TableColumn Followers;




@FXML
private TextField searchField;

private final ObservableList<Account> data = FXCollections.observableArrayList();


    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        User.setCellValueFactory(new PropertyValueFactory("User"));
//        Followers.setCellValueFactory(new PropertyValueFactory("Followers"));
        
        updateTable();
    }    
    
     @FXML
    private void searchFunction(KeyEvent event) {

        FilteredList<Account> filteredData = new FilteredList<>(data, e -> true);

        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Account>) tracks -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tracks.getUsername().contains(newValue)) {
                        return true;
                    } else if (tracks.getFollowers().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;

                });
            });
        });

        SortedList<Account> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    public void updateTable() {
        try {

            Master.Working.Common.database db = new Master.Working.Common.database();
            ResultSet rs = db.makeQuery("SELECT USERNAME FROM ACCOUNT");
            ResultSet frs = db.makeQuery("SELECT FRIENDID FROM FRIENDLIST");

            while (rs.next()) {
                data.add(new Account(
                        rs.getString("USERNAME"),
                        frs.getString("FRIENDID")
                ));

                table.setItems(this.data);
                table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }
    
}
