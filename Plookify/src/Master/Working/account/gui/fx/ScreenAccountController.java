/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.logic.deviceInfo;
import Master.Working.account.logic.logic;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author jll30
 */
public class ScreenAccountController implements Initializable {

    private int ID=9999;
    private final int[] dumpster1={-500,500};
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
    @FXML private Button addDevice;
    @FXML private Pane deviceDialog;
    @FXML private Label deviceDialogMsg;
    @FXML private Button deviceDialogOK;
    @FXML private Pane addingDeviceDialog;
    @FXML private Button addingDeviceDialogOK;
    @FXML private TextField deviceNameField;
    @FXML private ComboBox deviceTypeCombo;
    

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
                String exp="Next Due : "+logicA.stringGet(ID,"USERID","SUBSCRIPTION","DUEDATE");
                Label expiry=new Label(exp);
                expiry.setStyle("-fx-text-fill: red;");
                expiry.relocate(440,75);
                accountPane.getChildren().add(expiry);
            }
        else if(check==0)
            {premStatusButton.setText("error");}
        else{premStatusButton.setText("Subscribe");}
        String fullname=logicA.stringGet(ID,"ID","ACCOUNT", "FIRSTNAME")+" "+logicA.stringGet(ID,"ID","ACCOUNT", "LASTNAME");
        titleName.setText(fullname);
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
        addDevice.setVisible(false);
        logicA.data.conClose();
        
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
        addDevice.setVisible(true);
        
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
    
    @FXML
    public void deviceAddPressed(ActionEvent event)
    {
        int count=0;
        for(int j=0;j<5;j++)
        {
            if(tableInfo.get(j).getDeviceDate()!=-1)
            {
                count=count+1;
            }
        }
        if (count==5)
        {
            deviceDialogMsg.setText("Too many devices added. Please delete a device first.");
            deviceDialog.relocate(150,100);
        }
        addingDeviceDialog.relocate(150,100);
    }
    @FXML
    public void deviceDialogOkPressed(ActionEvent event)
    {
        deviceDialog.relocate(dumpster1[0],dumpster1[1]);
    }
    @FXML
    public void addingDeviceSubmit(ActionEvent event)
    {
        logicA.addDevice(ID, deviceNameField.getText(), (String)deviceTypeCombo.getValue());
        addingDeviceDialog.relocate(dumpster1[0],dumpster1[1]);
        refreshTable();
        
    }
    @FXML
    public void deviceNameTrim(KeyEvent ke)
    {
        if(deviceNameField.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
           }
    }
    
    public void refreshTable()
    {
        tableInfo=logicA.makeTableInfo(ID);
        deviceTable.setItems(tableInfo);
    }
}
