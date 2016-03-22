/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.Logic;

/**
 *
 * @author Hamza
 */

import javafx.beans.property.SimpleStringProperty;

public class Friends {
    private final SimpleStringProperty friends;
    
    public Friends(String friends)
    {
        this.friends = new SimpleStringProperty(friends);   
    }
    
    public String getFriends() {
        return friends.get();
    }

    public void setID(String Friends) {
        friends.set(Friends);
    }
    
}
