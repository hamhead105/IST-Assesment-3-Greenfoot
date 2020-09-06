import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Image;
/**
 * Write a description of class LevelButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelButton extends Button
{
    private int level;
    public LevelButton(int level) {
        this.level = level;
        setImage(new GreenfootImage("level" + level + ".png"));
    }
        
    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null)) {
            if (wasClicked(mouseInfo.getX(), mouseInfo.getY())) {
                loadMyLevel();
            }
        }  
    }
    
    public void loadMyLevel() {       
        if (level == 1) {
            Level1 level1 = new Level1();
            Greenfoot.setWorld(level1);
        }
    }
}
