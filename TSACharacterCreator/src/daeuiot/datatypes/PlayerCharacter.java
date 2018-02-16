/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.datatypes;

/**
 *
 * @author Daeuiot
 * 
 * This class is used to hold all of the data for any given character
 */
public class PlayerCharacter {
    private String fileName;
    private CharacterBackground background;
    
    public PlayerCharacter()
    {
        fileName = "char01";
        background = null;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public CharacterBackground getBackground() {
        return background;
    }
    
    public void setBackground(CharacterBackground background)
    {
        this.background = background;
    }
}
