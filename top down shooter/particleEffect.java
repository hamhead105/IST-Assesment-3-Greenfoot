import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class particleEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class particleEffect extends GameObject
{
    public particleEffect(int x, int y, int rot) {
        super(x,y);       
        setRotation(rot);
    }

    public void act() 
    {
        System.out.println("act");
        updateLocation();
    }    
}
