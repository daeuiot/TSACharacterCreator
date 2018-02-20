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
    private PlayerSkill[] skills;
    
    public PlayerCharacter(String fileName, Skill[] skills)
    {
        this.fileName = fileName;
        background = null;
        this.skills = new PlayerSkill[skills.length];
        for(int i=0;i<skills.length;++i)
        {
            this.skills[i] = new PlayerSkill(skills[i]);
        }
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

class PlayerSkill {
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
    
    //used for the total rank of a skill calculated
    //by purchased and archetypes etc.
    public int getRank()
    {
        return purchasedRanks;
    }
    
}
