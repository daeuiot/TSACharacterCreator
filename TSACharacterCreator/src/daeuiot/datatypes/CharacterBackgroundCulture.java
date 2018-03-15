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
public class CharacterBackgroundCulture { 
    private String culture;
    private String cultureDescription;

    public CharacterBackgroundCulture(String culture, String cultureDescription) {
        this.culture = culture;
        this.cultureDescription = cultureDescription;
    }

    public String getCulture() {
        return culture;
    }

    public String getCultureDescription() {
        return cultureDescription;
    }
}
