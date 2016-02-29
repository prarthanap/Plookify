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
public class User {

    private final SimpleStringProperty User;
    private final SimpleStringProperty Followers;
    
    public User(String User, String Followers) {
        this.User = new SimpleStringProperty(User);
        this.Followers = new SimpleStringProperty(Followers);
        
    }

    public String getUsername() {
        return User.get();
    }

    public String getFollowers() {
        return Followers.get();
    }


    public void setID(String Username) {
        User.set(Username);
    }

    public void setTrackName(String Follower) {
        Followers.set(Follower);
    }


}
