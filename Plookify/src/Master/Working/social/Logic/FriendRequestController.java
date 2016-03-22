/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class FriendRequestController implements Initializable {

    @FXML
    private AnchorPane requestDialogue;
    @FXML
    private Button acceptButton;
    @FXML
    private Button declineButton;
    
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        requestDialogue.setVisible(true);
    }    
    
    @FXML
    public void decline(MouseEvent event)
    {
        System.exit(0);
    }
    
}
