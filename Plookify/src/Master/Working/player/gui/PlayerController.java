/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.player.gui;

import Master.Working.Common.database;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author prarthana
 */
public class PlayerController implements Initializable {
    @FXML
    private TableView<Tracks> table;
    @FXML
    private TableColumn IDCol;
    @FXML
    private TableColumn trackNameCol;
    @FXML
    private TableColumn artistCol;
    @FXML
    private TableColumn timeCol;
    @FXML
    private TableColumn genreCol;
    @FXML
    private TableColumn albumCol;
    @FXML
    private Button play;
    @FXML
    private Slider slider;
    @FXML
    private TextField searchField;
    @FXML
    private Label duration;
    
    private final ObservableList<Tracks> data  = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        trackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        artistCol.setCellValueFactory(new PropertyValueFactory("artist"));
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        genreCol.setCellValueFactory(new PropertyValueFactory("genre"));
        albumCol.setCellValueFactory(new PropertyValueFactory("album"));

        IDCol.setMinWidth(100);
        trackNameCol.setMinWidth(200);
        artistCol.setMinWidth(200);
        timeCol.setMinWidth(100);
        genreCol.setMinWidth(100);
        albumCol.setMinWidth(200);
        
        
        
    }    
    
    
        
    

    @FXML
    private void playTrack(MouseEvent event) {
    }

    @FXML
    private void onPlay(ActionEvent event) {
    }

    @FXML
    private void onPause(ActionEvent event) {
    }

    @FXML
    private void searchFunction(KeyEvent event) {
    }
    
    public void upDate(){
     try {

            database db = new database();
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS");

            while (rs.next()) {
                data.add(new Tracks(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION"),
                        rs.getString("GENRE"),
                        rs.getString("ALBUM")
                ));

                table.setItems(this.data);

            }

        } catch (Exception e2) {
            System.err.println(e2);

}
    }
}
    

