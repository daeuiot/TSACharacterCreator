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
public class Talent extends CharacterDataType {
    private int tier;
    private String trigger; //for now lets just have the trigger as a string
    private boolean ranked;

    public Talent(int tier, String trigger, boolean ranked, String key, String name, String description) {
        super(key, name, description);
        this.tier = tier;
        this.trigger = trigger;
        this.ranked = ranked;
    }

    public Talent(int tier, String trigger, boolean ranked, String name, String description) {
        super("NULLKEY", name, description);
        this.tier = tier;
        this.trigger = trigger;
        this.ranked = ranked;
    }

    public int getTier() {
        return tier;
    }

    public String getTrigger() {
        return trigger;
    }

    public boolean isRanked() {
        return ranked;
    }
}

//Change to this alter
enum Trigger{
    PASSIVE(),
    ACTIVE();
    
    private 
    
    Trigger()
    {
        
    }
}
