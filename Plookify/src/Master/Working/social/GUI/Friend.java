/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Hamza
 */
public class Friend {
    private final StringProperty Fname;
    private final StringProperty Sname;
    
    public Friend() {
        this(null, null);
    }
    
    public Friend(String Fname, String Sname) {
        this.Fname = new SimpleStringProperty(Fname);
        this.Sname = new SimpleStringProperty(Sname);
    }

    public String getFname() {
        return Fname.get();
    }

    public void setFname(String Fname) {
        this.Fname.set(Fname);
    }

    public StringProperty FnameProperty() {
        return Fname;
    }

    public String getSname() {
        return Sname.get();
    }

    public void setSname(String Sname) {
        this.Sname.set(Sname);
    }

    public StringProperty SnameProperty() {
        return Sname;
    }       
}
