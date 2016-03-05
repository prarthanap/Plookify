/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.logic.logic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class AccountLoginController implements Initializable {

    private logic logic1=new logic();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void loginPressed(ActionEvent event)
    {
        System.out.println("Pressed LOGIN!");
    }
    @FXML
    private void registerPressed(ActionEvent event)
    {
        System.out.println("Pressed REGISTER!");
    }
}
