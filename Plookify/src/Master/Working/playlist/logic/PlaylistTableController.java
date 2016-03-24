/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.playlist.logic;

import Master.Working.playlist.gui.Songs;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class PlaylistTableController implements Initializable {

    @FXML
    public TableView<Songs> table;
    @FXML
    public TableColumn<?, ?> tSong;
    @FXML
    public TableColumn<?, ?> tArtist;
    @FXML
    public TableColumn<?, ?> tDuration;
    @FXML
    public TableView<Songs> searchTable;
    @FXML
    public TableColumn<?, ?> tsSong;
    @FXML
    public TableColumn<?, ?> tsArtist;
    @FXML
    public TableColumn<?, ?> tsDuration;
    @FXML
    public Button search;
    @FXML
    public TextField searchBar;
    @FXML
    public Label label;
    @FXML
    public ComboBox<String> pType;
    @FXML
    public Button nowPlayingBTN;
    @FXML
    public Label playlistLabel;
    @FXML
    public Button removeSongBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addToPlaylist(MouseEvent event) {
    }

    @FXML
    private void searchSongs(ActionEvent event) {
    }

    @FXML
    private void changePlaylistType(ActionEvent event) {
    }

    @FXML
    private void addToNowPlaying(ActionEvent event) {
    }

    @FXML
    private void removeSong(ActionEvent event) {
    }
    
}
