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
    private World world;
    private int damage;
    private long cullTime = System.currentTimeMillis()+400;
    
    public Bullet(int x, int y, int direction, int speed, int size, int damage) {
        setRotation(direction);
        GreenfootImage image = getImage();
        image.scale(size,size / 10);
        setFieldPosition(x, y);
        this.speed = speed;
        this.damage = damage;
    }
    
    public void act() 
    {
        timedDestroy();
        updateLocation();
        moveX((int) Math.round(Math.cos(Math.toRadians(getRotation())) * speed));
        moveY((int) Math.round(Math.sin(Math.toRadians(getRotation())) * speed));
    }
    
    public void timedDestroy() {
        if (System.currentTimeMillis() > cullTime) {
            getWorld().removeObject(this);
        }
    }
}
