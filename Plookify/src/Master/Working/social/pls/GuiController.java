/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import Master.Working.social.Logic.User;
import Master.Working.social.Logic.logic;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class GuiController implements Initializable {

@FXML
private Button removeFriend;
@FXML
private Button addFriend;
@FXML
private Button makePrivate;
@FXML
private Button yesConfirm;
@FXML
private Button noConfirm;
@FXML
private Button yesPrivate;
@FXML
private Button noPrivate;
@FXML
private Button upgradeClose;

@FXML
private TextField searchField;
@FXML
private TableView<Friends> friendList;
@FXML
private TableColumn friends;
@FXML
private TableView<friendPlaylist> fPlaylist;
@FXML
private TableColumn playlist;
@FXML
private TableView<Users> users;
@FXML
private TableColumn user;
@FXML
private AnchorPane friendView;
@FXML
private AnchorPane confirmDialog;
@FXML
private AnchorPane privateDialog;
@FXML
private AnchorPane friendAddedDialog;
@FXML
private AnchorPane upgradeDialog;
@FXML
private AnchorPane friendPlaylist;

private int ID = 9999;
private final Master.Working.social.Logic.logic accLogic=new Master.Working.social.Logic.logic();
    
private final ObservableList<Users> data = FXCollections.observableArrayList();
    
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       user.setCellValueFactory(new PropertyValueFactory("User"));
       confirmDialog.setVisible(false);
       privateDialog.setVisible(false);
       friendAddedDialog.setVisible(false);
       upgradeDialog.setVisible(false);
       updateTable();
    }  
    
    @FXML  //delete friend dialog
    private void launchDialog(MouseEvent event) {
        
        int prem = 1;
//        String uname = unameField.getText();
//        ID=accLogic.data.authCheckD(uname);
        if(prem==1)
        {
            confirmDialog.setVisible(true);
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void yesDelete(MouseEvent event)
    {
        logic delete = new logic();
        delete.deleteFriend("hq300");
        confirmDialog.setVisible(false);
    }
    
    @FXML
    private void noDelete(MouseEvent event)
    {
        confirmDialog.setVisible(false);
    }
    
    @FXML
    private void launchPrivate(MouseEvent event)
    {
        logic premium = new logic();
        int prem = 2;
        if(prem==2)
        {
            privateDialog.setVisible(true);
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void goPrivate(MouseEvent event)
    {
        logic becomePrivate = new logic();
        becomePrivate.publicity(3);
        privateDialog.setVisible(false);
    }

    @FXML
    private void stayPublic(MouseEvent event)
    {
        privateDialog.setVisible(false);
    }
    
    @FXML
    private void launchAdded(MouseEvent event)
    {
        logic premium = new logic();
        int prem = 0;
        
        if(prem==1)
        {
            friendAddedDialog.setVisible(true);    
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void acceptDialog(MouseEvent event)
    {
        int prem = 1;
        if(prem==1)
        {
            friendAddedDialog.setVisible(false);
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void closeUpgrade(MouseEvent event)
    {
        upgradeDialog.setVisible(false);
    }
    
    @FXML
    private void userTyped(KeyEvent event)
    {
        FilteredList<Users> filteredData = new FilteredList<>(data, e -> true); 
        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Users>) user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user.getUsername().contains(newValue)) {
                        return true;
                    }
                    else if (user.getUsername().toLowerCase().contains(lowerCaseFilter))
                    {
                        return true;
                    }

                    return false;

                });
            });
        });

        SortedList<Users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(users.comparatorProperty());
        users.setItems(sortedData);
    }

    
    public void updateTable() {
        try {

            Master.Working.Common.database db = new Master.Working.Common.database();
            ResultSet rs = db.makeQuery("SELECT USERNAME FROM ACCOUNT");

            while (rs.next()) {              
                
                data.add(new Users(
                        rs.getString(1)
                        
                ));

                users.setItems(this.data);
                users.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }
    
}
