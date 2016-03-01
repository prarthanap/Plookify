/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

import Master.Working.player.gui.Tracks;
import Master.Working.social.Logic.User;
import Master.Working.social.Logic.logic;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;




/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class SocialController implements Initializable {
@FXML
private Button premium;
@FXML
private TableView<User> table;
@FXML
private TableColumn User;
@FXML
private TableColumn Followers;
//@FXML
//private Button addFriend;
//@FXML
//private Button removeFriend;
//@FXML
//private Button FriendList;
//@FXML
//private Button makePrivate;





@FXML
private TextField searchField;

private final ObservableList<User> data = FXCollections.observableArrayList();


    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        User.setCellValueFactory(new PropertyValueFactory("User"));
        Followers.setCellValueFactory(new PropertyValueFactory("Followers"));
        
        updateTable();
    }    
    
    
     @FXML
    private void searchFunction(KeyEvent event) {

        FilteredList<User> filteredData = new FilteredList<>(data, e -> true);

        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super User>) user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user.getUsername().contains(newValue)) {
                        return true;
                    }

                    return false;

                });
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    public void updateTable() {
        try {

            Master.Working.Common.database db = new Master.Working.Common.database();
            ResultSet rs = db.makeQuery("SELECT USERNAME FROM ACCOUNT");
            ResultSet frs = db.makeQuery("SELECT * FROM FRIENDLIST");

            while (rs.next()) {
                data.add(new User(
//                        rs.getString(1),
//                        frs.getString(2)
                        "Test",
                        "Test1"
                        
                ));

                table.setItems(this.data);
                table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }
   
//    @FXML
//    public void onPrivate()
//    {
//        logic premium = new logic();
//        if(premium.premCheck(1)==2){
//            JOptionPane.showMessageDialog(null,"You are now switching to private mode");
//        }else{
//            JOptionPane.showMessageDialog(null,"This function cannot be used. You are not a premium member.");
//        }
//    
//    }
    
//    @FXML
//    public void friendAdded(String uname, String fname)
//    {
//        logic add = new logic();
//        add.add(uname, fname);
//        JOptionPane.showMessageDialog(null, "Friend added");
//            
//    }
            
}

