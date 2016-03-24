/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.gui.fx.ScreenAccountController;
import Master.Working.player.logic.PlaybarController;
import Master.Working.player.logic.TrackTableController;
import Master.Working.playlist.logic.PlaylistController;
import Master.Working.radio.logic.RadioController;
import Master.Working.social.Logic.GuiController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class TsController implements Initializable {

    private int UserID=3;   
    
    private TrackTableController table;
    private PlaybarController bar2;
    private RadioController rCon;
    private GuiController socialController;
    private TrackTableController tbc;
    private PlaylistController plc;
    private Stage accManager;
    
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
    @FXML
    private Pane radioPane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button tracksAdd;
    @FXML Button tracksButton;
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {}    
    
    public void init()
    {
        try{
            FXMLLoader radioLoader = new FXMLLoader(getClass().getResource("/Master/Working/radio/gui/radio.fxml"));
            Pane rPane =radioLoader.load();
            rCon=radioLoader.getController();
            rCon.setUser(UserID);
            rPane.relocate(120,0);
            radioPane.getChildren().add(rPane);
            FXMLLoader pBarLoader=new FXMLLoader(getClass().getResource("/Master/Working/player/gui/playbar.fxml"));
            Pane pBar=pBarLoader.load();
            Player.getChildren().add(pBar);
            bar2 = pBarLoader.getController();
            FXMLLoader tableLoader=new FXMLLoader(getClass().getResource("/Master/Working/player/gui/TrackTable.fxml"));
            Pane tTable=tableLoader.load();
            tbc=tableLoader.getController();
            tTable.setPrefSize(695, 430);
            table = tableLoader.getController();
            table.table.relocate(50,50);
            table.searchField.relocate(150,20);
            mainPane.getChildren().add(tTable);
            tracksAdd.toFront();
            FXMLLoader socialPaneLoader=new FXMLLoader(getClass().getResource("/Master/Working/social/GUI/mainSocialPane.fxml"));
            Pane socialP=(Pane)socialPaneLoader.load();
            socialController=socialPaneLoader.getController();
            socialController.setUser(UserID);
            socialController.IDintialize();
            rightSidePane.getChildren().add(socialP);
            radioPane.toBack();
            mainPane.toBack();
            
            
            accManager = new Stage();
            FXMLLoader accLoader = new FXMLLoader(getClass().getResource("/Master/Working/account/gui/fx/screenAccount.fxml"));
            Pane accPane = (Pane)accLoader.load();
            ScreenAccountController controllerA = accLoader.getController();
            controllerA.setUser(UserID);
            controllerA.initVariables();
            controllerA.getWindowHandle(radio);
            Scene scene = new Scene(accPane);
            accManager.setScene(scene);
            accManager.setResizable(false);

        }catch(IOException e){}
    }

    @FXML
    private void pressAccount(ActionEvent event) throws IOException
    {
        accManager.show();
    }

    @FXML
    private void pressRadio(ActionEvent event)
    {
        radioPane.setVisible(true);
        socialController.displayFriendResults.setVisible(false);
        radioPane.toFront();
        
    }
    @FXML
    private void pressTracks(ActionEvent event)
    {
        socialController.displayFriendResults.setVisible(false);
        radioPane.setVisible(false);
        mainPane.toFront();
        tracksAdd.toFront();
        
    }
    public void setID(int uID)
    {
        this.UserID=uID;
    }
    @FXML
    private void addTracks(ActionEvent event)
    {
        table.addToNowPlaying();
        bar2.initialize(null, null);
    }
    @FXML
    private void commonSearch(KeyEvent event)
    {
        socialController.searchingC(searchBar.getText());
        
    }
    @FXML
    private void pressSocial(ActionEvent event)
    {
        radioPane.setVisible(false);
        rightSidePane.toFront();
    }
}
