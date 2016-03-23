package Master.Working.player.logic;

import Master.Working.Common.database;
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
import javafx.scene.control.SelectionMode;
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
public class TrackTableController implements Initializable {

    @FXML
    public TableView<Tracks> table;
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
    public TextField searchField;

    private final ObservableList<Tracks> data = FXCollections.observableArrayList();

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

        updateTable();

    }

    public void updateTable() {
        try {

            database db = new database();
            ResultSet rs = db.makeQuery("SELECT * FROM TRACKS");

            while (rs.next()) {
                data.add(new Tracks(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION"),
                        rs.getString("GENRE")
                ));

                table.setItems(this.data);
                table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }

    @FXML
    private void searchFunction(KeyEvent event) {

        FilteredList<Tracks> filteredData = new FilteredList<>(data, e -> true);

        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Tracks>) tracks -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tracks.getID().contains(newValue)) {
                        return true;
                    } else if (tracks.getTrackName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    } else if (tracks.getArtist().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    } else if (tracks.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    }

                    return false;

                });
            });
        });
        SortedList<Tracks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    @FXML
    public void addToNowPlaying() {
        database db = new database();

        for (Tracks trackSelect : table.getSelectionModel().getSelectedItems()) {

            String nowPlaying = trackSelect.getTrackName();

            db.makeUpdate("INSERT INTO NOWPLAYING(TRACKNAME) " + "VALUES  ('" + nowPlaying + "');");
        }

    }

    @FXML
    private void onDeselect(MouseEvent event) {

        table.getSelectionModel().clearSelection();

    }

}
