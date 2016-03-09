/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import Master.Working.social.pls.Users;
import Master.Working.social.Logic.logic;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
private Button removeFriend;
@FXML
private Button addFriend;
@FXML
private Button makePrivate;
@FXML
private Button yesConfirm;
@FXML
private Button noConfirm;
@FXML
private Button yesPrivate;
@FXML
private Button noPrivate;
@FXML
private Button upgradeClose;

@FXML
private TextField searchField;

@FXML
private TableView<friendPlaylist> fPlaylist;
@FXML
private TableColumn playlist;

@FXML
private TableView showUsers;

@FXML
private ListView listFriends;

@FXML
private AnchorPane friendView;
@FXML
private AnchorPane displayFriendResults;
@FXML
private AnchorPane confirmDialog;
@FXML
private AnchorPane privateDialog;
@FXML
private AnchorPane friendAddedDialog;
@FXML
private AnchorPane upgradeDialog;
@FXML
private AnchorPane friendPlaylist;

private int ID = 9999;
private final logic accLogic=new logic();
    
private ObservableList<Users> userData = FXCollections.observableArrayList();
    
private final ObservableList options = FXCollections.observableArrayList();

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       displayFriendResults.setVisible(false);
       confirmDialog.setVisible(false);
       privateDialog.setVisible(false);
       friendAddedDialog.setVisible(false);
       upgradeDialog.setVisible(false);
       
       
       TableColumn col1 = new TableColumn("username");
       col1.setCellValueFactory(new PropertyValueFactory<>("username"));
       showUsers.getColumns().add(col1);
       
       
       
       listFriends.getSelectionModel().selectedItemProperty().addListener(
                         new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                
            }
        });
       
       
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
        delete.deleteFriend("hq300");
        confirmDialog.setVisible(false);
    }
    
    @FXML
    private void noDelete(MouseEvent event)
    {
        confirmDialog.setVisible(false);
    }
    
    @FXML
    private void launchPrivate(MouseEvent event)
    {
        logic premium = new logic();
        int prem = 2;
        if(prem==2)
        {
            privateDialog.setVisible(true);
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
    }
    
    @FXML
    private void goPrivate(MouseEvent event)
    {
        logic becomePrivate = new logic();
        becomePrivate.publicity(3);
        privateDialog.setVisible(false);
    }

    @FXML
    private void stayPublic(MouseEvent event)
    {
        privateDialog.setVisible(false);
    }
    
    @FXML
    private void launchAdded(MouseEvent event)
    {
        logic premium = new logic();
        int prem = 0;
        
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
    private void acceptDialog(MouseEvent event)
    {
        int prem = 1;
        if(prem==1)
        {
            friendAddedDialog.setVisible(false);
        }
        else
        {
            upgradeDialog.setVisible(true);
        }
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
        ResultSet rs = accLogic.data.makeQuery("SELECT USERNAME FROM ACCOUNT");
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
            else{}
            
        }
        showUsers.setItems(userData);
        
    }
    
    
}
