/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.pls;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hamza
 */
public class Users {
    private final SimpleStringProperty username;
    
    public Users(String user) {
        this.username = new SimpleStringProperty(user);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String u) {
        username.set(u);
    }

}
