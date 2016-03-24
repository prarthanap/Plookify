/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class PlaylistListController implements Initializable {

    @FXML
    private Button createPlaylistBTN;
    @FXML
    private TextField newPlaylistName;
    @FXML
    private TableView<?> playlistsTable;
    @FXML
    private TableColumn<?, ?> tPlaylists;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createPlaylist(ActionEvent event) {
    }

    @FXML
    private void switchPlaylists(MouseEvent event) {
    }
    
}
