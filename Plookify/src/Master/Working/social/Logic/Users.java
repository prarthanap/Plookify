/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hamza
 */
public class Users {
    private final SimpleStringProperty user;
    
    public Users(String user) {
        this.user = new SimpleStringProperty(user);
        
    }

    public String getUsername() {
        return user.get();
    }

    public void setID(String Username) {
        user.set(Username);
    }

}
