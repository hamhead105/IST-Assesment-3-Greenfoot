import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * wall for game, enemies and players cannot walk through this
 * 
 * @author Peter Jung
 * @version 1
 */
public class BoxWall extends GameObject
{
    private int bounds;
    
    public BoxWall(int x, int y) {
        super(x,y);
        bounds = 60;
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
