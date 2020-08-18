import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoxWall here.
 * 
 * @author Peter Jung
 * @version (a version number or a date)
 */
public class BoxWall extends GameObject
{
    private int bounds;
    
    public BoxWall() {
        super(200,200);
        bounds = 50;
        getImage().scale(100,100);       
    }
    
    public void act() 
    {
        updateLocation();
    }
    
    public int getColliderBounds() {
        return bounds;
    }
}
