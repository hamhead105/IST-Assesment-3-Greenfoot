import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NPC extends GameObject
{
    private int speed;
    private int colliderRadius = 20;
    
    public NPC() {
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
    }
    
    public void act() 
    {
        updateLocation();
        hit();
    }
    
    public void hit() {
       getImage().setColor(Color.RED);
       //getImage().fill();
    }
    
    public int getColliderRadius() {
        return colliderRadius;
    }
}
