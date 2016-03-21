/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.gui.fx.ScreenAccountController;
import Master.Working.social.pls.GuiController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class TsController implements Initializable {

    private int UserID=4;
    private GuiController gController;        
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            FXMLLoader radioLoader = new FXMLLoader(getClass().getResource("/Master/Working/radio/gui/radio.fxml")); 
            Pane root = (Pane)radioLoader.load();
            radioPane.getChildren().add(root);
            Pane pBar=(Pane)FXMLLoader.load(getClass().getResource("/Master/Working/player/gui/playbar.fxml"));
            Player.getChildren().add(pBar);
            Pane tTable=(Pane)FXMLLoader.load(getClass().getResource("/Master/Working/player/gui/TrackTable.fxml"));
            mainPane.getChildren().add(tTable);
            FXMLLoader playlistLoader = new FXMLLoader(getClass().getResource("/Master/Working/playlist/gui/mainScreen.fxml"));
            Pane playList=(Pane)playlistLoader.load();
            Playlist.getChildren().add(playList);
            //FXMLLoader socialLoader = new FXMLLoader(getClass().getResource("/Master/Working/social/pls/newGui.fxml"));
            //Pane socialP=(Pane)socialLoader.load();
            //gController=socialLoader.getController();
            //gController.setUser(4);
            //rightSidePane.getChildren().add(socialP);

        }catch(Exception e){}
    }    

    @FXML
    private void pressAccount(ActionEvent event) throws IOException
    {
        Stage accManager = new Stage();
        FXMLLoader accLoader = new FXMLLoader(getClass().getResource("/Master/Working/Account/gui/fx/screenAccount.fxml"));
        Pane root = (Pane)accLoader.load();
        ScreenAccountController controllerA = accLoader.getController();
        controllerA.setUser(UserID);
        controllerA.initVariables();
        Scene scene = new Scene(root);
        accManager.setScene(scene);
        accManager.setResizable(false);
        accManager.show();
    }

    @FXML
    private void pressRadio(ActionEvent event)
    {
        radioPane.relocate(150,85);
        
    }
    
}
