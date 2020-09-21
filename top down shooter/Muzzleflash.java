import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * particle effects
 * 
 * @author Peter Jung
 * @version 1
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
