/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class TsController implements Initializable {

    @FXML
    private Pane Player;
    @FXML
    private Pane Playlist;
    @FXML
    private Pane NavigationPane;
    @FXML
    private Button account;
    @FXML
    private Button radio;
    @FXML
    private Pane rightSidePane;
    @FXML
    private Pane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
