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
    private final SimpleStringProperty user;
    
    public Users(String user) {
        this.user = new SimpleStringProperty(user);
        
    }

    Users(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUsername() {
        return user.get();
    }


    public void setID(String Username) {
        user.set(Username);
    }

}
