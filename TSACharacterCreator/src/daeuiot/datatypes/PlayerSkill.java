/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.datatypes;

import java.io.Serializable;

/**
 *
 * @author Daeuiot
 */
public class PlayerSkill implements Serializable{
    Skill skill;
    int purchasedRanks;

    public PlayerSkill(Skill skill) {
        this.skill = skill;
        purchasedRanks = 0;
    }
    
    public void increasePurchasedRank()
    {
        purchasedRanks++;
    }
    
    public void decrementPurchasedRank()
    {
        purchasedRanks--;
    }
    
    public int getPurchasedRanks()
    {
        return purchasedRanks;
    }

    public String getName() {
        return skill.getName();
    }

    public String getType() {
        return skill.getType();
    }

    public String getAttribute() {
        return skill.getAttribute();
    }
    
    //used for the total rank of a skill calculated
    //by purchased and archetypes etc.
    public int getRank()
    {
        return purchasedRanks;
    }
}
