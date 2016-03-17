/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.social.pls.GuiController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class PlookifyController implements Initializable {
    
    private int UserID = 1;
    
    
    @FXML
    private Pane Navigation, Social, player, tracklist, Radio;
    @FXML
    private Button radio, account;
    
    @FXML
    private Pane changePane;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Master/Working/radio/gui/radio.fxml")); 
            Parent root = (Parent)loader.load();
            GuiController controller = loader.getController();
            controller.setUser(UserID);
            Social.getChildren().add(root);
        }catch(Exception e){
            
        }
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Master/Working/playlist/gui/mainScreen.fxml")); 
            Parent root = (Parent)loader.load();
            GuiController controller = loader.getController();
            controller.setUser(UserID);
            Social.getChildren().add(root);
        }catch(Exception e){
            
        }
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newgui.fxml")); 
            Parent root = (Parent)loader.load();
            GuiController controller = loader.getController();
            controller.setUser(UserID);
            Social.getChildren().add(root);
        }catch(Exception e){
            
        }
        
        
        
        
 
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newgui.fxml")); 
            Parent root = (Parent)loader.load();
            GuiController controller = loader.getController();
            controller.setUser(UserID);
            Social.getChildren().add(root);
        }catch(Exception e){
            
        }
    }    
  
    
    @FXML
    private void pressAccount() throws IOException
    {
        Stage accManager = new Stage();  
        Parent root = FXMLLoader.load(getClass().getResource("/Master/Working/Account/gui/fx/screenAccount.fxml"));
        Scene scene = new Scene(root);
        accManager.setScene(scene);
        accManager.setResizable(false);
        accManager.show();
        
    
    }
    
    @FXML
    private void pressRadio() throws IOException
    {
        
        Pane pane1 = FXMLLoader.load(getClass().getResource("/Master/Working/radio/gui/radio.fxml"));
        
        changePane.getChildren().add(pane1);
    
    }
}
