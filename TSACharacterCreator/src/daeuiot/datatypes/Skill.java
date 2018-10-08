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
public class Skill extends CharacterDataType{
    private String type;
    private String attribute;

    public Skill(String type, String attribute, String key, String name, String description) {
        super(key, name, description);
        this.type = type;
        this.attribute = attribute;
    }

    public Skill(String name, String type, String attribute, String description) {
        super("NULLKEY", name, description);
        this.type = type;
        this.attribute = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
