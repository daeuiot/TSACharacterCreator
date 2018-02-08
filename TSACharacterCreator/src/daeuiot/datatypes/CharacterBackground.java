/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.datatypes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Backgrounds are stored in this object
 * 
 * @author Daeuiot
 */
public class CharacterBackground {    
    //Need to add culture description, not just location description
    private String culture;
    private String location;
    private String description;

    public CharacterBackground(String backgroundCulture, String backgroundLocation, String backgroundDescription) {
        this.culture = backgroundCulture;
        this.location = backgroundLocation;
        this.description = backgroundDescription;
    }

    public String getCulture() {
        return culture;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return location;
    }
    
    /*private StringProperty culture;
    private StringProperty location;
    private StringProperty description;

    public CharacterBackground(String culture, String location, String description) {
        this.culture = new SimpleStringProperty(culture);
        this.location = new SimpleStringProperty(location);
        this.description =  new SimpleStringProperty(description);
    }
    
    public String getCulture()
    {
        return culture.get();
    }
    
    public String getLocation()
    {
        return location.get();
    }
    
    public String getDescription()
    {
        return description.get();
    }
    
    public void setCulture(String culture)
    {
        this.culture.set(culture);
    }
    
    public void setLocation(String location)
    {
        this.location.set(location);
    }
    
    public void setDescription(String description)
    {
        this.description.set(description);
    }
    
    public StringProperty getCultureProperty() {
        return culture;
    }

    public StringProperty getLocationProperty() {
        return location;
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }*/
}
