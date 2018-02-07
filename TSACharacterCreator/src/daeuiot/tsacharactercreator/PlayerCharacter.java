/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsacharactercreator;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Daeuiot
 * 
 * This class is used to hold all of the data for any given character
 */
public class PlayerCharacter {
    //private SimpleObjectProperty<CharacterBackground> backgroundProperty;
    private CharacterBackground background;
    
    public PlayerCharacter()
    {
        //backgroundProperty = new SimpleObjectProperty<>(new CharacterBackground("NULL", "NULL", "NULL"));
        background = new CharacterBackground("NULL", "NULL", "NULL");
    }

    /*
    public CharacterBackground getBackground() {
        return backgroundProperty.get();
    }

    public void setBackground(CharacterBackground background) {
        this.backgroundProperty.set(background);
    }
    
    public SimpleObjectProperty<CharacterBackground> getBackgroundProperty()
    {
        return backgroundProperty;
    }
    */
    
    public CharacterBackground getBackground() {
        return background;
    }
    
    public void setBackground(CharacterBackground background)
    {
        this.background = background;
    }
}
