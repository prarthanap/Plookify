/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.logic.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private Button hand;
    
    @FXML private Button premStatusButton;
    @FXML private Label titleName;
    @FXML private Button deviceButton;
    @FXML private Button accDelButton;
    @FXML private Button logOutButton;
    @FXML private Pane accountPane;
    @FXML private TableView deviceTable;
    @FXML private Button delete1;
    @FXML private Button delete2;
    @FXML private Button delete3;      
    @FXML private Button delete4;
    @FXML private Button delete5;
    @FXML private Button addDevice;
    @FXML private Pane accountDialog;
    @FXML private Label deviceDialogMsg;
    @FXML private Button deviceDialogOK;
    @FXML private Pane addingDeviceDialog;
    @FXML private Button addingDeviceDialogOK;
    @FXML private TextField deviceNameField;
    @FXML private ComboBox deviceTypeCombo;
    @FXML private Pane accDelDialog;
    @FXML private PasswordField accDelPassField;
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }
    public void initVariables()
    {
        deviceTable.setVisible(false);
        String fullname=logicA.stringGet(ID,"ID","ACCOUNT", "FIRSTNAME")+" "+logicA.stringGet(ID,"ID","ACCOUNT", "LASTNAME");
        titleName.setText(fullname);
        tableInfo=logicA.makeTableInfo(ID);
        TableColumn col1 = new TableColumn("Device Name");col1.setMinWidth(120);col1.setCellValueFactory(new PropertyValueFactory<>("deviceName"));
        TableColumn col2 = new TableColumn("Device Type");col2.setMinWidth(110);col2.setCellValueFactory(new PropertyValueFactory<>("deviceType"));
        TableColumn col3 = new TableColumn("Days since added"); col3.setMinWidth(150);col3.setCellValueFactory(new PropertyValueFactory<>("deviceDate"));
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
        buttonCheck();
        
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
    public void pressedDelAcc(ActionEvent event)
    {
        accDelDialog.toFront();
        accDelDialog.relocate(0,80);
    }
    @FXML
    public void pressedLogOut(ActionEvent event) throws IOException
    {
        Stage backToStart=new Stage();
        Parent start1 = FXMLLoader.load(getClass().getResource("/Master/Working/account/gui/fx/screenStart.fxml"));
        Scene scene2 = new Scene(start1);
        backToStart.setScene(scene2);
        backToStart.show();
        logicA.data.makeUpdate("DELETE FROM NOWPLAYING");
        Stage oldy=(Stage)accountPane.getScene().getWindow();
        oldy.close();
        oldy=(Stage)hand.getScene().getWindow();
        oldy.close();
        ID=9999;//resets ID to default
    }
    
    @FXML
    public void deleteDevice(ActionEvent event)
    {
       Button btn=(Button)event.getSource();
       String btnText=btn.getText().substring(btn.getText().length()-1);
       int deviceListNo=Integer.parseInt(btnText);
       if(tableInfo.get(deviceListNo-1).getDeviceDate()>30)//if device is added more than 30 days ago
       {
           int dId=tableInfo.get(deviceListNo-1).getDeviceID();
           logicA.deleteDevice(dId);
           refreshTable();
       }
       else
       {
          deviceDialogMsg.setText("Can not delete Device. Less than 30days old");
            accountDialog.relocate(150,100);
       }
    }
    
    @FXML
    public void deviceAddPressed(ActionEvent event)
    {
        int count=0;
        for(int j=0;j<4;j++)
        {
            if(tableInfo.get(j).getDeviceDate()!=-1)
            {
                count=count+1;
            }
        }
        if (count==5)
        {
            deviceDialogMsg.setText("Too many devices added. Please delete a device first.");
            accountDialog.relocate(150,100);
        }
        else{addingDeviceDialog.relocate(150,100);}
    }
    @FXML
    public void deviceDialogOkPressed(ActionEvent event)
    {
        accountDialog.relocate(dumpster1[0],dumpster1[1]);
    }
    @FXML
    public void addingDeviceSubmit(ActionEvent event)
    {
        if(deviceNameField.getText().length()>4)
        {
            logicA.addDevice(ID, deviceNameField.getText(), (String)deviceTypeCombo.getValue());
            addingDeviceDialog.relocate(dumpster1[0],dumpster1[1]);
            refreshTable();
        }
        else
        {
            deviceDialogMsg.setText("Devicename needs to be at least 4 characters long");
            accountDialog.relocate(150,100);
        }
        
    }
    @FXML
    public void deviceNameTrim(KeyEvent ke)
    {
        if(deviceNameField.getText().length()>=12)
           {
               ke.consume();
               System.out.println("consumed");
               deviceDialogMsg.setText("Devicename max 12 characters long");
               accountDialog.relocate(150,100);
           }
    }
    
    public void refreshTable()
    {
        tableInfo=logicA.makeTableInfo(ID);
        deviceTable.setItems(tableInfo);
    }
    @FXML
    public void delConfirm(ActionEvent event) throws SQLException, IOException
    {
        String pCheck=accDelPassField.getText();
        System.out.println(pCheck);
        String uname = logicA.data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='"+ID+"'").getString(1);
        logicA.data.conClose();
        System.out.println(uname);
        String pswd=logicA.data.makeQuery("SELECT PASSWORD FROM ACCOUNT WHERE USERNAME='"+uname+"'").getString(1);
        logicA.data.conClose();
        System.out.println(pswd);
        if (pCheck.equals(pswd))
                {
                    logicA.deleteAccount(ID);
                    System.out.println("Account deleted");
                    Stage backToStart=new Stage();
                    Parent start1 = FXMLLoader.load(getClass().getResource("/Master/Working/account/gui/fx/screenStart.fxml"));
                    Scene scene2 = new Scene(start1);
                    backToStart.setScene(scene2);
                    backToStart.show();
                    Stage oldy=(Stage)accountPane.getScene().getWindow();
                    oldy.close();
                    oldy=(Stage)hand.getScene().getWindow();
                    oldy.close();
                }
        else{System.out.println("incorrect password");}
    }
    @FXML
    public void delBack(ActionEvent event)
    {
         accDelDialog.relocate(dumpster1[0],dumpster1[1]);
    }
    @FXML
    public void pressedPremButton(ActionEvent event)
    {
        if (premStatusButton.getText().equals("Subscribe"))
        {
            logicA.extendPrem(ID);
            buttonCheck();
        }
    }
    public void getWindowHandle(Button p)
    {
        this.hand=p;
    }
    public void buttonCheck()
    {
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
        else if(check==-1)
        {premStatusButton.setText("Subscribe");
        deviceDialogMsg.setText("Your account has been downgraded due to subscription expiring. Subscribe to regain premium status.");
        accountDialog.relocate(150,100);}
        else{premStatusButton.setText("Subscribe");}
    }
}
