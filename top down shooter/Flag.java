import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flag extends GameObject
{
    public Flag(int x, int y) {
        super(x,y);
        GreenfootImage image = getImage();
        image.scale(100,100);
    }
    public void act() 
    {
        updateLocation();
    }    
}
