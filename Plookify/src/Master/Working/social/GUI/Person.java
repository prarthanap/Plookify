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
 * @author jerry
 */
public class Person {
    private final StringProperty Fname;
    private final StringProperty Sname;
    private final StringProperty Email;
    //private final IntegerProperty postalCode;
    //private final StringProperty city;
    //private final ObjectProperty<LocalDate> birthday;
    
    public Person() {
        this(null, null);
    }
    
    public Person(String Fname, String Sname) {
        this.Fname = new SimpleStringProperty(Fname);
        this.Sname = new SimpleStringProperty(Sname);
        this.Email = new SimpleStringProperty("email");
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
    
    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String Email) {
        this.Email.set(Email);
    }

    public StringProperty EmailProperty() {
        return Email;
    }
        
}
