/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.GUI;

import Master.Working.Common.database.*;
import Master.Working.social.GUI.Person;
//import static Database.Database.searchFriendbyEmail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.When;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class FriendListBuilderController implements Initializable {
    @FXML
    private TableView<Person> friendTable;
    
    @FXML
    private Label Fnamelabel;
    @FXML
    private Label Snamelabel;
    @FXML
    private Label Elabel;
    @FXML
    private TextField addbyEmail;
    @FXML
    private Button add_button;
    @FXML
    private TextField searchbyEmail;
    @FXML
    private TextField deletebyEmail;
    @FXML
    private Button delete_button;
    @FXML
    private Button add_search;
    @FXML
    private Button search_button;
    @FXML
    private GridPane Fdeatil_grid;
    @FXML
    private AnchorPane Fdeatil_pane;
    @FXML
    private AnchorPane addFriend_pane;
    @FXML
    private TextArea addMesg;
    @FXML
    private Button send_add;
    @FXML
    private Button cancle_add;
    @FXML
    private AnchorPane deleteFriend_pane;
    @FXML
    private TextArea deleteMsg;
    @FXML
    private Button delete_remove;
    @FXML
    private Button cancle_delete;
    @FXML
    private Button deleteFSButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Fdeatil_pane.setVisible(true);
        addFriend_pane.setVisible(false);
        deleteFriend_pane.setVisible(false);
    }    

    //Link to Database
    /*@FXML
    private void searchDB(MouseEvent event) throws SQLException {
         String name = searchFriendbyEmail.getText();
                  
         String email = songViaGenre();
         
         String songID = null; 
         
         FnameL.setText(Firstname);
         SnameL.setText(Surname);
    }*/
        
    @FXML
    private void addIconClick(MouseEvent event) {
        Fdeatil_pane.setVisible(false);
        addFriend_pane.setVisible(true);
        deleteFriend_pane.setVisible(false);
    }

    @FXML
    private void removeIconClick(MouseEvent event) {
        Fdeatil_pane.setVisible(false);
        addFriend_pane.setVisible(false);
        deleteFriend_pane.setVisible(true);
        /**    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
        } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
        }**/
    }

    @FXML
    private void sendClick(MouseEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Friend request has been sent!");

        alert.showAndWait();
        Fdeatil_pane.setVisible(true);
        addFriend_pane.setVisible(false);
        deleteFriend_pane.setVisible(false);
    }

    @FXML
    private void cancleClick(MouseEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancle?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Fdeatil_pane.setVisible(true);
            addFriend_pane.setVisible(false);        
            deleteFriend_pane.setVisible(false);
        } else {
            
        }
    }
    @FXML
    private void deleteFriendClick(MouseEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Friend has been deleted!");

        alert.showAndWait();
        Fdeatil_pane.setVisible(true);
        addFriend_pane.setVisible(false);
        deleteFriend_pane.setVisible(false);
    }
    
    @FXML
    private void cancleDeleteClick(MouseEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancle?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Fdeatil_pane.setVisible(true);
            addFriend_pane.setVisible(false);        
            deleteFriend_pane.setVisible(false);
        } else {
            
        }
    }

    @FXML
    private void searchFieldClick(MouseEvent event) {
        searchbyEmail.setText("");
    }

    @FXML
    private void addSearchFieldClick(MouseEvent event) {
        addbyEmail.setText("");
    }

    @FXML
    private void addMesgFieldClick(MouseEvent event) {
        addMesg.setText("");
    }

    @FXML
    private void deleteSearchFieldClick(MouseEvent event) {
        deletebyEmail.setText("");
    }

    @FXML
    private void addFrinedSBClick(MouseEvent event) {
    }

    @FXML
    private void deleteSBClick(MouseEvent event) {
    }
    
}

/*
 //Update method
    public void updateStudent(StudentPojo pojo){
        try {
            connected();
            String sql = "Update student set name='"+pojo.getName()+"',surname = '"+pojo.getSurname()+"',age = "+pojo.getAge()+",email = '"+pojo.getEmail()+"' Where id = "+pojo.getId();
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e) {
        }
        finally{
        closed();
        }
    }
 //Delete Method.
    public void deleteStudent(int id){
        try {
            connected();
            statement.executeUpdate("Delete from student where id = "+id);
        } catch (Exception e) {
        }finally{
           closed();
        }
}
*/
