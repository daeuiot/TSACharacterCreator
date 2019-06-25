/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daeuiot
 * 
 * This class is used to hold all of the data for any given character
 */
public class PlayerCharacter {
    private String fileName;
    private CharacterBackgroundLocation background;
    private PlayerSkill[] skills;
    private List<List<Talent>> talents;
    
    public PlayerCharacter(String fileName, Skill[] skills)
    {
        this.fileName = fileName;
        background = null;
        this.skills = new PlayerSkill[skills.length];
        for(int i=0;i<skills.length;++i)
        {
            this.skills[i] = new PlayerSkill(skills[i]);
        }
        talents = new ArrayList<>(5);
        for(int i=0;i<5;++i)
        {
            talents.add(new ArrayList<>());
        }
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public CharacterBackgroundLocation getBackground()
    {
        return background;
    }
    
    public PlayerSkill[] getSkills()
    {
        return skills;
    }
    
    public void setBackground(CharacterBackgroundLocation background)
    {
        this.background = background;
    }
    
    public void increaseSkill(PlayerSkill skill)
    {
        if(skill.getPurchasedRanks() >= 0 && skill.getPurchasedRanks() < 5)
        {
            //xp cost stuff
            skill.increasePurchasedRank();
        }
    }
    
    public void decreaseSkill(PlayerSkill skill)
    {
        if(skill.getPurchasedRanks() > 0 && skill.getPurchasedRanks() <= 5)
        {
            //xp refund stuff
            skill.decrementPurchasedRank();
        }
    }
    
    public List<List<Talent>> getTalents()
    {
        return talents;
    }
    
    public List<Talent> getTalents(int tier)
    {
        return talents.get(tier);
    }
}