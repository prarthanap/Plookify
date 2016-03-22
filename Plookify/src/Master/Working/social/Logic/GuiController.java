package Master.Working.social.Logic;

import Master.Working.Common.database;
import Master.Working.player.logic.Tracks;
import Master.Working.social.Logic.Users;
import Master.Working.social.Logic.logic;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
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
    private Button removeFriend, addFriend, yesConfirm, noConfirm, upgradeClose;

    @FXML
    private Slider PublicOrPrivate;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> fPlaylist;
    private ObservableList<String> friendPlayList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> showUsers;
    private ObservableList<String> userData = FXCollections.observableArrayList();

    @FXML
    private TableView<Tracks> FriendPlaylistTable;
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
    private AnchorPane friendView;
    @FXML
    private AnchorPane displayFriendResults;
    @FXML
    private AnchorPane confirmDialog;
    @FXML
    private AnchorPane friendAddedDialog;
    @FXML
    private AnchorPane upgradeDialog;
    @FXML
    private AnchorPane friendPlaylist;
    @FXML
    private AnchorPane FriendPlaylistDialog;

    @FXML
    private ListView<String> ViewFriends;
    private ObservableList<String> friendTest = FXCollections.observableArrayList();

    private int ID = 9999;
    private final logic accLogic = new logic();

//    private ObservableList<Users> userData = FXCollections.observableArrayList();
    private ObservableList<friendPlaylist> plData = FXCollections.observableArrayList();

    private ObservableList<Friends> lists = FXCollections.observableArrayList();

    private ObservableList<Tracks> FriendsTracks = FXCollections.observableArrayList();

    checkPublic checkPublicObj = new checkPublic(ID);
    private double sliderValue = checkPublicObj.checkPublicity();

    public database data = new database();

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        confirmDialog.setVisible(false);
        friendAddedDialog.setVisible(false);
        upgradeDialog.setVisible(false);

        //Making user public or private (setting slider to position last placed in)
        PublicOrPrivate.setValue(sliderValue);

        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        trackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
        artistCol.setCellValueFactory(new PropertyValueFactory("artist"));
        timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        genreCol.setCellValueFactory(new PropertyValueFactory("genre"));
        updateTable();
        playlistNames();
        friendss();
    }

    @FXML
    public void friendss() {
        try {
            int userID = ID;
            ResultSet rs = data.makeQuery("SELECT * FROM FRIENDLIST where OWNERID="+ID+" and ADDED=1");
            while (rs.next()) {

                ViewFriends.setItems(friendTest);
                friendTest.add(rs.getString("Username"));
            }
        } catch (Exception e2) {
            System.err.println(e2);
        }
    }

    public void updateTable() {
        try {
            ResultSet rs = data.makeQuery("SELECT * FROM TRACKS");
            while (rs.next()) {
                FriendsTracks.add(new Tracks(
                        rs.getString("TRACKID"),
                        rs.getString("TRACKNAME"),
                        rs.getString("ARTIST"),
                        rs.getString("DURATION"),
                        rs.getString("GENRE")
                ));
                FriendPlaylistTable.setItems(this.FriendsTracks);
                FriendPlaylistTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }
        } catch (Exception e2) {
            System.err.println(e2);
        }
    }

    @FXML
    public void playlistNames() {
        ViewFriends.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                friendPlayList.clear();
                try {
                    ResultSet rs = data.makeQuery("SELECT PLAYLISTNAME FROM PLAYLIST WHERE PLAYLISTOWNER=" + ViewFriends.getSelectionModel().getSelectedItem() + "");
                    while (rs.next()) {
                        fPlaylist.setItems(friendPlayList);
                        friendPlayList.add(rs.getString("PLAYLISTNAME"));
                    }
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        });
    }

    public void setUser(int pass) {
        this.ID = pass;
    }

    public int getUser() {
        return this.ID;
    }

    @FXML  //delete friend dialog
    private void launchDialog(MouseEvent event) {

        int prem = 1;
//        String uname = unameField.getText();
//        ID=accLogic.data.authCheckD(uname);
        if (prem == 1) {
            confirmDialog.setVisible(true);
        } else {
            upgradeDialog.setVisible(true);
        }
    }

    @FXML
    private void yesDelete(MouseEvent event) throws SQLException {
        
       String delAcc="DELETE FROM FRIENDLIST WHERE FRIENDID='"+ViewFriends.getSelectionModel().getSelectedItem()+"'";
        Statement statementD;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db")) {
            statementD = conn.createStatement();
            statementD.setQueryTimeout(10);
            statementD.execute("PRAGMA foreign_keys = ON");
            statementD.execute(delAcc);
        }
        confirmDialog.setVisible(false);
    }

    @FXML
    private void noDelete(MouseEvent event) {
        confirmDialog.setVisible(false);
    }

    @FXML
    private void launchPrivate() {
        sliderValue = PublicOrPrivate.getValue();
        if (sliderValue == 100) {
            String privateUpdate = "UPDATE SUBSCRIPTION set PUBLICITY = '100.0' where USERID='" + ID + "'";
            data.makeUpdate(privateUpdate);
            data.conClose();
            System.out.println("Private");
        } else {
            String privateUpdate = "UPDATE SUBSCRIPTION set PUBLICITY = '0.0' where USERID='" + ID + "'";
            data.makeUpdate(privateUpdate);
            data.conClose();
            System.out.println("Public");
        }
    }

    @FXML
    private void launchAdded(MouseEvent event) throws SQLException {
        int prem = 1;
        if (prem == 1) {
            int temp = data.makeQuery("SELECT ID FROM ACCOUNT WHERE USERNAME='"+ ViewFriends.getSelectionModel().getSelectedItem()+"'").getInt(1);
            int tempAdd = 1;
            data.makeUpdate("INSERT INTO FRIENDLIST (OWNERID,FRIENDID,ADDED)VALUES('" + ID + "','" + temp + "','" + tempAdd + "')");
            data.conClose();
            
            friendAddedDialog.setVisible(true);
        } else {
            upgradeDialog.setVisible(true);
        }
    }

    @FXML
    private void acceptDialog(MouseEvent event) throws SQLException {
        friendAddedDialog.setVisible(false);
    }

    @FXML
    private void closeUpgrade(MouseEvent event) {
        upgradeDialog.setVisible(false);
    }

    //For combined work
    public void searching(String x) throws SQLException {
        try {
            ResultSet pubID = data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM=1 and PUBLICITY='0.0'");
            ArrayList<String> namesList = new ArrayList<>();
            while (pubID.next())//for every matching record a username is gotten
            {
                namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='" + pubID.getInt(1) + "'").getString(1));
            }
            for (int i = 0; i < namesList.size(); i++) {
                userData.clear();
                if (namesList.get(i).startsWith(x)) {
                    userData.add(namesList.get(i));
                }
            }
            showUsers.setItems(userData);
        } catch (Exception e2) {
            System.err.println(e2);
        }

    }

    @FXML
    private void searching(KeyEvent event) {
        try {
            ResultSet pubID = data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM=1 and PUBLICITY='0.0'");
            String searchF = searchField.getText();
            ArrayList<String> namesList = new ArrayList<>();
            while (pubID.next())//for every matching record a username is gotten
            {
                namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='" + pubID.getInt(1) + "'").getString(1));
            }
            for (int i = 0; i < namesList.size(); i++) {
                userData.clear();
                if (namesList.get(i).startsWith(searchF)) {
                    userData.add(namesList.get(i));
                }
            }
            showUsers.setItems(userData);
        } catch (Exception e2) {
            System.err.println(e2);
        }
    }

    public void deselect(MouseEvent event) {

        showUsers.getSelectionModel().clearSelection();
        fPlaylist.getSelectionModel().clearSelection();
        FriendPlaylistTable.getSelectionModel().clearSelection();
        ViewFriends.getSelectionModel().clearSelection();
    }

}
