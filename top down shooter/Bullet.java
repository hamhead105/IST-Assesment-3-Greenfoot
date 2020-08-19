import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
        super(x,y);
        setRotation(direction);
        GreenfootImage image = getImage();
        image.scale(size,size / 10);
        this.speed = speed;
        this.damage = damage;
    }
    
    public void act() 
    {
        collisionCheck();
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
    
    private void collisionCheck() {
        boolean willRemove = false;
        List<NPC> npcs = getWorld().getObjects(NPC.class);
        for (NPC npc : npcs) {
            if (Math.sqrt(Math.pow(npc.getFieldX() - this.getFieldX(), 2) + Math.pow(npc.getFieldY() - this.getFieldY(), 2)) <= npc.getColliderRadius()) {
                npc.hit(damage);
                willRemove = true;
            }      
        }
        
        List<Player> players = getWorld().getObjects(Player.class);
        if (players != null) {
            for (Player player : players) {
                if (Math.sqrt(Math.pow(player.getFieldX() - this.getFieldX(), 2) + Math.pow(player.getFieldY() - this.getFieldY(), 2)) <= player.getColliderRadius()) {
                    player.hit(damage);
                    willRemove = true;
                }      
            }
        }
        List<BoxWall> boxWalls = getWorld().getObjects(BoxWall.class);
        if (boxWalls != null) {
            for (BoxWall boxWall : boxWalls) {
                if (getFieldX() > boxWall.getFieldX() - boxWall.getColliderBounds() && getFieldX() < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldY() > boxWall.getFieldY() - boxWall.getColliderBounds() && getFieldY() < boxWall.getFieldY() + boxWall.getColliderBounds()) {
                    willRemove = true;
                }
            }       
        }
        if (willRemove) {
             getWorld().removeObject(this);
        }
    }
}
