package Master.Working.social.pls;

import Master.Working.Common.database;
import Master.Working.player.logic.Tracks;
import Master.Working.social.pls.Users;
import Master.Working.social.Logic.logic;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
private TableView<friendPlaylist> fPlaylist;
@FXML
private TableColumn friendPlaylistCol;

@FXML
private TableView<Users> showUsers;

@FXML
private TableView<Friends> ViewFriends;
@FXML
private TableColumn friendCol;

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


private int ID = 9999;
private final logic accLogic=new logic();

private ObservableList<Users> userData = FXCollections.observableArrayList();

private ObservableList<friendPlaylist> plData = FXCollections.observableArrayList();

private ObservableList<Friends> lists = FXCollections.observableArrayList();

private ObservableList<Tracks> FriendsTracks = FXCollections.observableArrayList();

checkPublic checkPublicObj = new checkPublic(ID);
private double sliderValue = checkPublicObj.checkPublicity();


public database data=new database();

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
       
       TableColumn col1 = new TableColumn("Username");
       col1.setMinWidth(150);
       col1.setCellValueFactory(new PropertyValueFactory<>("Username"));
       showUsers.getColumns().add(col1); 
       
       friendCol.setCellFactory(new PropertyValueFactory("friends"));
       friendPlaylistCol.setCellFactory(new PropertyValueFactory("playlist"));
       
       IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
       trackNameCol.setCellValueFactory(new PropertyValueFactory("trackName"));
       artistCol.setCellValueFactory(new PropertyValueFactory("artist"));
       timeCol.setCellValueFactory(new PropertyValueFactory("time"));
       genreCol.setCellValueFactory(new PropertyValueFactory("genre"));
       updateTable();
       playlistNames();
       addedFriendsList();
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
    
    public void addedFriendsList()
    {
        try {
            ResultSet rs = data.makeQuery("SELECT * FROM FRIENDLIST");

            while (rs.next()) {
                lists.add(new Friends(
                        rs.getString("FRIENDID")
                ));
                
                ViewFriends.setItems(this.lists);
                ViewFriends.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }

        } catch (Exception e2) {
            System.err.println(e2);

        }
    }
        
    public void playlistNames()
    {
        try{
        ResultSet rs = data.makeQuery("SELECT * FROM PLAYLIST");

        while(rs.next())
        {
           plData.add(new friendPlaylist(
                   rs.getString("PLAYLISTID")
            ));
        }
        fPlaylist.setItems(this.plData);
        }
        catch(Exception e2)
        {
           System.err.println(e2);
        }
    }
       
    public void setUser(int pass)
    {
        this.ID=pass;
    }
    public int getUser()
    {
        return this.ID;
    }
        
    @FXML  //delete friend dialog
    private void launchDialog(MouseEvent event) {
        
        int prem = 1;
//        String uname = unameField.getText();
//        ID=accLogic.data.authCheckD(uname);
        if(prem==1)
        {
            confirmDialog.setVisible(true);
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void yesDelete(MouseEvent event)
    {
        logic delete = new logic();
        
        delete.deleteFriend(ID);
        confirmDialog.setVisible(false);
    }
    
    @FXML
    private void noDelete(MouseEvent event)
    {
        confirmDialog.setVisible(false);
    }
    
    @FXML
    private void launchPrivate()
    {
        sliderValue = PublicOrPrivate.getValue();
        if(sliderValue == 100)
        {
            String privateUpdate = "UPDATE SUBSCRIPTION set PUBLICITY = '100' where USERID='"+ID+"'";
            data.makeUpdate(privateUpdate);
            data.conClose();
            System.out.println("Private");
        }
        else
        {
            String privateUpdate = "UPDATE SUBSCRIPTION set PUBLICITY = '0' where USERID='"+ID+"'";
            data.makeUpdate(privateUpdate);
            data.conClose();
            System.out.println("Public");
        }
    }
    
    
    @FXML
    private void launchAdded(MouseEvent event) throws SQLException
    {
        int prem = 1;
        if(prem==1)
        {
            friendAddedDialog.setVisible(true);    
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void acceptDialog(MouseEvent event) throws SQLException
    {
        int temp = 1;
        int tempAdd = 1;
        
        showUsers.getSelectionModel().getSelectedItem();
        
        data.makeUpdate("INSERT INTO FRIENDLIST (OWNERID,FRIENDID,ADDED)VALUES('"+ID+"','"+temp+"','"+tempAdd+"')");
        data.conClose();
        friendAddedDialog.setVisible(false); 
    }
    
    @FXML
    private void closeUpgrade(MouseEvent event)
    {
        upgradeDialog.setVisible(false);
    }
    
    @FXML
    private void searching(KeyEvent event) throws SQLException
    {
        userData = FXCollections.observableArrayList();
        String searchF=searchField.getText();
        ResultSet rs = data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM='1' and PUBLICITY='0.0'");
//        ResultSet rs = accLogic.data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID="+ID+";");
        
        int i=0;
        while(rs.next())
        {
            if(rs.getString(1).startsWith(searchF))
            {
                Users u1 = new Users(rs.getString(1));
                userData.add(u1);
                System.out.println(userData.get(i).getUsername());
                i++;
            }
        }
        showUsers.setItems(userData);
        showUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//        try {
//            ResultSet ab = accLogic.data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM=1 and PUBLICITY=0.0");
//            ResultSet rs = accLogic.data.makeQuery("SELECT * from ACCOUNT WHERE ID ='"+ab+"'");
//
//            while (rs.next()) {
//                userData.add(new Users(
//                        rs.getString("USERNAME")
//                ));
//                showUsers.setItems(this.userData);
//                showUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//            }
//
//        } catch (Exception e2) {
//            System.err.println(e2);
//        }
    }
    
    
    public void deselect(MouseEvent event) {

        showUsers.getSelectionModel().clearSelection();
        fPlaylist.getSelectionModel().clearSelection();
        FriendPlaylistTable.getSelectionModel().clearSelection();
        
    }
    
}
