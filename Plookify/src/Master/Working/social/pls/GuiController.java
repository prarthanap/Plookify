/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
private TextField searchField;
@FXML
private TableView<Friends> friendList;
@FXML
private TableView<Users> users;
@FXML
private AnchorPane friendView;
@FXML
private AnchorPane confirmDialog;
@FXML
private AnchorPane privateDialog;
@FXML
private AnchorPane friendAddedDialog;



    
    
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       confirmDialog.setVisible(false);
       privateDialog.setVisible(false);
       friendAddedDialog.setVisible(false);
    }  
    
    @FXML
    private void launchDialog(MouseEvent event) {
        confirmDialog.setVisible(true);
    }
    
    @FXML
    private void yesDelete(MouseEvent event)
    {
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
        privateDialog.setVisible(true);
    }
    
    @FXML
    private void goPrivate(MouseEvent event)
    {
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
        friendAddedDialog.setVisible(true);    
    }
    
    @FXML
    private void acceptDialog(MouseEvent event)
    {
        friendAddedDialog.setVisible(false);
    }
    
}
