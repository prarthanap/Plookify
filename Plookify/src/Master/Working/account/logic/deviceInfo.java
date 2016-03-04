/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jll30
 */
public class deviceInfo {
    private SimpleIntegerProperty deviceID;
    private SimpleStringProperty deviceName;
    private SimpleStringProperty deviceType;
    private SimpleIntegerProperty deviceDate;
 
    public deviceInfo(int dID,String dName, String dType, int dDate) {
        this.deviceID = new SimpleIntegerProperty(dID);
        this.deviceName = new SimpleStringProperty(dName);
        this.deviceType = new SimpleStringProperty(dType);
        this.deviceDate = new SimpleIntegerProperty(dDate);
    }
    
    public int getDeviceID() {
        return deviceID.get();
    }
    public void setDeviceID(int d) {
        deviceID.set(d);
    }
 
    public String getDeviceName() {
        return deviceName.get();
    }
    public void setDeviceName(String d) {
        deviceName.set(d);
    }
        
    public String getDeviceType() {
        return deviceType.get();
    }
    public void setDeviceType(String d) {
        deviceType.set(d);
    }
    
    public int getDeviceDate() {
        return deviceDate.get();
    }
    public void setDeviceDate(int d) {
        deviceDate.set(d);
    }
        
}