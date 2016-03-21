package Master.Working.player.gui;

import Master.Working.player.logic.PlaybarController;
import Master.Working.player.logic.TrackTableController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author prarthana
 */
public class BorderController implements Initializable {

    @FXML
    private Pane bar;
    @FXML
    private Pane main;
    @FXML
    private Button loadMenu;
    private TrackTableController table;
    private PlaybarController bar2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            FXMLLoader tracksLoader = new FXMLLoader(getClass().getResource("/Master/Working/player/gui/TrackTable.fxml"));
            Pane root1 = (Pane) tracksLoader.load();
            table = tracksLoader.getController();

            main.getChildren().add(root1);
            root1.toBack();

            FXMLLoader playbarLoader = new FXMLLoader(getClass().getResource("/Master/Working/player/gui/playbar.fxml"));
            Pane root = (Pane) playbarLoader.load();
            bar2 = playbarLoader.getController();

            bar.getChildren().add(root);

        } catch (Exception e) {
        }
    }

    @FXML
    private void loadNowPlaying(ActionEvent event) {
        table.addToNowPlaying();
        bar2.initialize(null, null);

    }

}
