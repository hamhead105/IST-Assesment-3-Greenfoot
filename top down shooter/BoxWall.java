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
    
    public BoxWall(int x, int y) {
        super(x,y);
        bounds = 65;
        getImage().scale(150,150);       
    }
    
    public void act() 
    {
        updateLocation();
    }
    
    public int getColliderBounds() {
        return bounds;
    }
}
