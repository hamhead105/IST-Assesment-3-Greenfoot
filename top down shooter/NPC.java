import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NPC extends GameObject
{
    private int speed;
    private int colliderRadius = 30;
    private int health;
    
    private long currentTime = System.currentTimeMillis();
    private long nextShotDue;
    
    public NPC() {
        health = 100;
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
    }
    
    public void act() 
    {
        currentTime = System.currentTimeMillis();        
        updateLocation();
        if (health >= 0) {
            aimAtPlayer();
            if (currentTime > nextShotDue) {
                shoot();
                nextShotDue = currentTime + 400;
            }
        } else {
            fadeAway();
        }
    }
    
    public void hit(int damage) {
        health -= damage;
        getImage().setColor(Color.RED);
        //getImage().fill();
    }
    
    public void fadeAway() {
        getImage().setTransparency(getImage().getTransparency() - 30);
        if (getImage().getTransparency() <= 30) {
            getWorld().removeObject(this);
        }
    }
    
    public int getColliderRadius() {
        return colliderRadius;
    }
    
    public void aimAtPlayer() {
        List<Player> players = getWorld().getObjects(Player.class);
        if (players.size() > 0) {
            Player player = players.get(0);
            setRotation((int) Math.round (Math.toDegrees(Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX()))) + 90);
        }
    }
    
    public void shoot() {
        int barrelXOffset = 16;
        int barrelYOffset = 75;
        double alpha = 0;
        double theta = Math.toDegrees(Math.atan(barrelYOffset / barrelXOffset));
        double h = Math.sqrt(Math.pow(barrelXOffset, 2) + Math.pow(barrelYOffset, 2));
        alpha = getRotation() - theta;
        int worldXOffset = (int) Math.round(Math.cos(Math.toRadians(alpha)) * h);
        int worldYOffset = (int) Math.round(Math.sin(Math.toRadians(alpha)) * h);
       
        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90, 50, 40, 20);
        getWorld().addObject(bullet, 0, 0);
    }
}
