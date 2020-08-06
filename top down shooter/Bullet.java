import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends GameObject
{
    private int speed;
    
    public Bullet(int x, int y, int direction, int speed, int size, int damage) {
        setRotation(direction);
        GreenfootImage image = getImage();
        image.scale(size,size / 10);
        setFieldPosition(x, y);
        this.speed = speed;
    }
    
    public void act() 
    {
        updateLocation();
        moveX((int) Math.round(Math.cos(Math.toRadians(getRotation())) * speed));
        moveY((int) Math.round(Math.sin(Math.toRadians(getRotation())) * speed));
    }    
}
