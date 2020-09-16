import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Muzzleflash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Muzzleflash extends GameObject
{
    public Muzzleflash(int x, int y, int rot) {
        super(x,y);
        updateLocation();
        getImage().scale(100,80);
        setRotation(rot);               
    }
    public void act() 
    {
        updateLocation();
         if (getImage().getTransparency() <= 45) {
            getWorld().removeObject(this);
        } else {
            getImage().setTransparency(getImage().getTransparency() - 45);
        }
    }    
}
