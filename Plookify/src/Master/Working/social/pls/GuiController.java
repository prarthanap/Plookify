/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import Master.Working.social.Logic.logic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }  
    
    @FXML  //delete friend dialog
    private void launchDialog(MouseEvent event) {
        confirmDialog.setVisible(true);
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
        if(premium.premCheck(4)==2)
        {
            privateDialog.setVisible(true);
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
        if(premium.premCheck(4)==2)
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
        friendAddedDialog.setVisible(false);
    }
    
    @FXML
    private void closeUpgrade(MouseEvent event)
    {
        upgradeDialog.setVisible(false);
    }
    
}
