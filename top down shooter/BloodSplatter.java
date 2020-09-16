import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BloodSplatter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BloodSplatter extends GameObject
{
    public BloodSplatter(int x, int y, int rot) {
        super(x,y);
        getImage().scale(140,120);
        setRotation(rot);
        
    }
    public void act() 
    {
        updateLocation();
         if (getImage().getTransparency() <= 5) {
            getWorld().removeObject(this);
        } else {
            getImage().setTransparency(getImage().getTransparency() - 5);
        }
    }    
}
