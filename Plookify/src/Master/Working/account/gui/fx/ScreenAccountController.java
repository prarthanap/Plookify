/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.logic.deviceInfo;
import Master.Working.account.logic.logic;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class ScreenAccountController implements Initializable {

    private int ID=9999;
    private final logic logicA=new logic();
    private ObservableList<deviceInfo> tableInfo;
    
    @FXML private Button premStatusButton;
    @FXML private Label titleName;
    @FXML private Button deviceButton;
    @FXML private Button changeDetailsButton;
    @FXML private Button logOutButton;
    @FXML private Pane accountPane;
    @FXML private TableView deviceTable;
    @FXML private Button delete1;
    @FXML private Button delete2;
    @FXML private Button delete3;      
    @FXML private Button delete4;
    @FXML private Button delete5;
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }
    public void initVariables()
    {
        deviceTable.setVisible(false);
        int check=logicA.premCheck(ID);
        System.out.println(check);
        if (check==2)
            {
                premStatusButton.setText("Premium");
                Label expiry=new Label("Next Due : "+logicA.stringGet(ID,"USERID","SUBSCRIPTION","DUEDATE"));
                expiry.setStyle("-fx-text-fill: red;");
                expiry.relocate(440,75);
                accountPane.getChildren().add(expiry);
            }
        else if(check==0)
            {premStatusButton.setText("error");}
        else{premStatusButton.setText("Subscribe");}
        titleName.setText(logicA.stringGet(ID,"ID","ACCOUNT", "FIRSTNAME")+" "+logicA.stringGet(ID,"ID","ACCOUNT", "LASTNAME"));
        tableInfo=logicA.makeTableInfo(ID);
        TableColumn col1 = new TableColumn("Device Name");col1.setMinWidth(150);col1.setCellValueFactory(new PropertyValueFactory<>("deviceName"));
        TableColumn col2 = new TableColumn("Device Type");col2.setMinWidth(100);col2.setCellValueFactory(new PropertyValueFactory<>("deviceType"));
        TableColumn col3 = new TableColumn("Days since added"); col3.setMinWidth(130);col3.setCellValueFactory(new PropertyValueFactory<>("deviceDate"));
        deviceTable.getColumns().addAll(col1,col2,col3);
        deviceTable.setPrefSize(385,180);
        deviceTable.setItems(tableInfo);
        delete1.setVisible(false);
        delete2.setVisible(false);
        delete3.setVisible(false);
        delete4.setVisible(false);
        delete5.setVisible(false);
        
    }
    
    public void setUser(int pass)
    {
        this.ID=pass;
    }
    public int getUser()
    {
        return this.ID;
    }
    
    @FXML
    public void pressedDevice(ActionEvent event)
    {
        delete1.setVisible(true);
        delete2.setVisible(true);
        delete3.setVisible(true);
        delete4.setVisible(true);
        delete5.setVisible(true);
        deviceTable.setVisible(true);
        deviceButton.setVisible(false);
        
    }
    @FXML
    public void pressedChangeDetails(ActionEvent event)
    {
        
    }
    @FXML
    public void pressedLogOut(ActionEvent event)
    {
        
    }
    
    @FXML
    public void deleteDevice(ActionEvent event)
    {
       Button btn=(Button)event.getSource();
       String btnText=btn.getText().substring(btn.getText().length()-1);
       int deviceListNo=Integer.parseInt(btnText);
       if(tableInfo.get(deviceListNo-1).getDeviceDate()>30)//if device is added more than 30 days ago
       {
           System.out.println("can delete");
       }
       else
       {
           System.out.println("cannot delete");
       }
    }
    
    

}
