/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.datatypes;

/**
 * Backgrounds are stored in this object
 * Need to change background over to having a culture cause multiple backgrounds have the same culture
 * 
 * @author Daeuiot
 */
public class CharacterBackground {    
    private String culture;
    private String location;
    private String cultureDescription;
    private String locationDescription;

    public CharacterBackground(String backgroundCulture, String backgroundLocation, String backgroundCultureDescription, String backgroundLocationDescription) {
        this.culture = backgroundCulture;
        this.location = backgroundLocation;
        this.cultureDescription = backgroundCultureDescription;
        this.locationDescription = backgroundLocationDescription;
    }

    public String getCulture() {
        return culture;
    }

    public String getLocation() {
        return location;
    }

    public String getCultureDescription() {
        return cultureDescription;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCultureDescription(String cultureDescription) {
        this.cultureDescription = cultureDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    @Override
    public String toString()
    {
        return location;
    }
}

//Make an actual class?
class Culture{
    private String name;
    private String description;

    public Culture(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

//Make it a class or is the location the actualy background?
class Location{
    private Culture culture;
    private String name;
    private String description;

    public Location(Culture culture, String name, String description) {
        this.culture = culture;
        this.name = name;
        this.description = description;
    }

    public Culture getCulture() {
        return culture;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
