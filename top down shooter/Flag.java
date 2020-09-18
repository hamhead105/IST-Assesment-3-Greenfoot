import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flag here.
 * 
 * @author Peter Jung
 * @version 1
 */
public class Flag extends GameObject
{
    public Flag(int x, int y) {
        super(x,y);
        GreenfootImage image = new GreenfootImage(50, 50);
        image.setColor(new Color(120,255,120));
        image.fillRect(0, 0, 50, 50);
        setImage(image);
    }
    public void act() 
    {
        updateLocation();
    }    
}
