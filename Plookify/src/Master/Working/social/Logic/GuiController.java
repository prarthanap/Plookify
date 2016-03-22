package Master.Working.social.Logic;

import Master.Working.Common.database;
import Master.Working.player.logic.Tracks;
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
    public Button removeFriend, addFriend, yesConfirm, noConfirm, upgradeClose;

    @FXML
    public Slider PublicOrPrivate;

    @FXML
    public TextField searchField;

    @FXML
    public ListView<String> fPlaylist;
    private ObservableList<String> friendPlayList = FXCollections.observableArrayList();

    @FXML
    public ListView<String> showUsers;
    private ObservableList<String> userData = FXCollections.observableArrayList();

    @FXML
    public TableView<Tracks> FriendPlaylistTable;
    @FXML
    public TableColumn IDCol;
    @FXML
    public TableColumn trackNameCol;
    @FXML
    public TableColumn artistCol;
    @FXML
    public TableColumn timeCol;
    @FXML
    public TableColumn genreCol;

    @FXML
    public AnchorPane friendView;
    @FXML
    public AnchorPane displayFriendResults;
    @FXML
    public AnchorPane confirmDialog;
    @FXML
    public AnchorPane friendAddedDialog;
    @FXML
    public AnchorPane upgradeDialog;
    @FXML
    public AnchorPane friendPlaylist;
    @FXML
    public AnchorPane FriendPlaylistDialog;

    @FXML
    public ListView<String> ViewFriends;
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
    
    }
    
    public void IDintialize()
    {
        confirmDialog.setVisible(false);
        friendAddedDialog.setVisible(false);
        upgradeDialog.setVisible(false);
        //Making user public or private (setting slider to position last placed in)
        PublicOrPrivate.setValue(sliderValue);
        displayFriendResults.setVisible(false);
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
            friendTest=FXCollections.observableArrayList();
            ResultSet rs = data.makeQuery("SELECT FRIENDID FROM FRIENDLIST where OWNERID='"+ID+"' and ADDED=1");
            
            ArrayList<String> namesList=new ArrayList<>();
            while (rs.next())//for every matching record a username is gotten
            {
                namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='"+rs.getInt(1)+"'").getString(1));
            }
            System.out.println(namesList.size()+" friends");
            for (String a : namesList)
            {
                friendTest.add(a);
                ViewFriends.setItems(friendTest);
            }
            rs.close();
            data.conClose();

//            while (rs.next()) {
//
//                ViewFriends.setItems(friendTest);
//                friendTest.add(rs.getString("FRIENDID"));
//            }
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
                try {
                    FriendPlaylistDialog.setVisible(true);
                    int rs1 = data.makeQuery("SELECT ID FROM ACCOUNT WHERE USERNAME='"+ViewFriends.getSelectionModel().getSelectedItem()+"'").getInt("ID");
                    data.conClose();
                    ResultSet rs = data.makeQuery("SELECT * FROM PLAYLIST WHERE PLAYLISTOWNER='" + rs1 + "'");
                    while (rs.next()) {
                        fPlaylist.setItems(friendPlayList);
                        friendPlayList.add(rs.getString("PLAYLISTNAME"));
                    }
                    rs.close();
                    data.conClose();
                    
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
    public void launchDialog(MouseEvent event) {

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
    public void yesDelete(MouseEvent event) throws SQLException {
        
        int a = data.makeQuery("SELECT ID FROM ACCOUNT WHERE USERNAME='"+ViewFriends.getSelectionModel().getSelectedItem()+"'").getInt("ID");
        data.conClose();
        String delFriend="DELETE FROM FRIENDLIST WHERE OWNERID='"+ID+"' AND FRIENDID='"+a+"'";
        data.makeUpdate(delFriend);
        data.conClose();
        confirmDialog.setVisible(false);
        friendss();
    }

    @FXML
    public void noDelete(MouseEvent event) {
        confirmDialog.setVisible(false);
    }

    @FXML
    public void launchPrivate() {
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
    public void launchAdded(MouseEvent event) throws SQLException {
        int prem = 1;
        String uname=showUsers.getSelectionModel().getSelectedItem();
        if (prem == 1) {
            int temp;
            temp = data.makeQuery("SELECT ID FROM ACCOUNT WHERE USERNAME='"+uname+"'").getInt(1);
            data.conClose();
            data.makeUpdate("INSERT INTO FRIENDLIST (OWNERID,FRIENDID,ADDED)VALUES('" + ID + "','" + temp + "','1')");
            data.conClose();
            friendss();
            friendAddedDialog.setVisible(true);
        } else {
            upgradeDialog.setVisible(true);
        }
    }

    @FXML
    public void acceptDialog(MouseEvent event) throws SQLException {
        friendAddedDialog.setVisible(false);
    }

    @FXML
    public void closeUpgrade(MouseEvent event) {
        upgradeDialog.setVisible(false);
    }

    public void searchingC(String bar) {
        try {
            displayFriendResults.setVisible(true);
            System.out.println("runs2");
            ResultSet pubID2 = data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM='1' and PUBLICITY='0.0'");
            userData.clear();
            
            ArrayList<String> namesList=new ArrayList<>();
            ArrayList<Integer> idList=new ArrayList<>();
            while (pubID2.next())//for every matching record a username is gotten
            {
                idList.add(pubID2.getInt(1));
            }
            pubID2.close();
            for(Integer b: idList)
            {
                namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='"+b+"'").getString(1));
                data.conClose();
            }
            System.out.println(namesList.size());
            for (String a : namesList)
            {
                if(a.startsWith(bar))
                {
                    userData.add(a);
                    showUsers.setItems(userData);
                }
            }
            data.conClose();
            
        } catch (Exception e2) {
            System.err.println(e2);
        }
    }

    @FXML
    public void searching(KeyEvent event) {
        try {
            FriendPlaylistDialog.setVisible(false);
            ViewFriends.setVisible(true);
            ResultSet pubID = data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM='1' and PUBLICITY='0.0'");
            String searchF = searchField.getText();
            userData.clear();
            
            ArrayList<String> namesList=new ArrayList<>();
            while (pubID.next())//for every matching record a username is gotten
            {
                namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='"+pubID.getInt(1)+"'").getString(1));
            }
            data.conClose();
            for (String a : namesList)
            {
                if(a.startsWith(searchF))
                {
                    userData.add(a);
                    showUsers.setItems(userData);
                }
            }
//            while (pubID.next())//for every matching record a username is gotten
//            {
//                
//                    showUsers.setItems(userData);
//                    userData.add(pubID.getString("USERID"));
//
//            }
        } catch (Exception e2) {
            System.err.println(e2);
        }
    }

    public void deselect(MouseEvent event) {
        showUsers.getSelectionModel().clearSelection();
        fPlaylist.getSelectionModel().clearSelection();
        FriendPlaylistTable.getSelectionModel().clearSelection();
//        ViewFriends.getSelectionModel().clearSelection();
    }
}