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
public class CharacterBackgroundLocation extends CharacterDataType{
    private String cultureKey;
    
    public CharacterBackgroundLocation(String key, String name, String description, String cultureKey) {
        super(key, name, description);
        this.cultureKey = cultureKey;
    }

    public String getCultureKey() {
        return cultureKey;
    }
}
