import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Blood particle
 * 
 * @author Peter Jung
 * @version 1
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
