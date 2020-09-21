import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Image;
/**
 * On click, creates the desired level
 * 
 * @author Peter Jung
 * @version 1
 */
public class LevelButton extends Button
{
    private int level;
    public LevelButton(int level) {
        super(200, 100, 0, 0);
        this.level = level;
        setImage(new GreenfootImage("level" + level + ".png"));
    }
        
    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null) && mouseInfo != null) {
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
        if (level == 2) {
            Level2 level2 = new Level2();
            Greenfoot.setWorld(level2);
        }
        if (level == 3) {
            Level3 level3 = new Level3();
            Greenfoot.setWorld(level3);
        }
    }
}
