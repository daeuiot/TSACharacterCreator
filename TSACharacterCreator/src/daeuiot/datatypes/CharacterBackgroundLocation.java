/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.datatypes;

/**
 *
 * @author Daeuiot
 */
public class CharacterBackgroundLocation {
    private String location;
    private String locationDescription;

    public CharacterBackgroundLocation(String location, String locationDescription) {
        this.location = location;
        this.locationDescription = locationDescription;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationDescription() {
        return locationDescription;
    }
}
