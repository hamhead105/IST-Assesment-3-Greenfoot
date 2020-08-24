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
    
    private int shootSpread;
    private int fireRate;
    
    public NPC(int x, int y) {
        super(x,y);
        shootSpread = 15;
        health = 100;
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
        fireRate = 200;
    }
    
    public void act() 
    {
        currentTime = System.currentTimeMillis();        
        updateLocation();
        if (health >= 0) {
            aimAtPlayer();
            if (currentTime > nextShotDue) {
                if (findTarget(300)) shoot();
                nextShotDue = currentTime + fireRate;
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
       
        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + shootSpread / 2 - Greenfoot.getRandomNumber(shootSpread) , 50, 40, 5);
        getWorld().addObject(bullet, 0, 0);
    }
    
    public boolean findTarget(int range) {
        double checkRotationRadians = 0;
        List<Player> players = getWorld().getObjects(Player.class);
        Player player = null;
        if (players.size() > 0) {
            player = players.get(0);
            checkRotationRadians = Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX()) + Math.toRadians(90);        
            int yDifference = player.getFieldY() - this.getFieldY();
            int xDifference = player.getFieldX() - this.getFieldX();
            int distanceToPlayer = (int) Math.round(Math.sqrt(Math.pow(yDifference,2) + Math.pow(xDifference,2)));
            int checkDist = 0;
            int[] playerPosition;
            playerPosition = new int[2];
            playerPosition[0] = player.getFieldX();
            playerPosition[1] = player.getFieldY();
            
            // y = mx + b
            double m = 0;
            if (xDifference != 0) {
                m = (double) yDifference/xDifference; // find gradient
            }
            System.out.println("fieldX: " + (player.getFieldX() - this.getFieldX()) + " fieldY: " + (player.getFieldY() - this.getFieldY()));
            System.out.println(this.getFieldY() + " - ( ( " + this.getFieldX() + " ) " + " * " +  m + " ) ");
            double b = (double) this.getFieldY() - (this.getFieldX() * m); // find y intercept
            System.out.println("NPC to player gradient: " + this.getFieldY() + " = " + m + " * " + (this.getFieldX()) + " + " + b);
            List<BoxWall> walls = getWorld().getObjects(BoxWall.class);
            if (walls != null) {  
                for (BoxWall boxWall : walls) {
                    System.out.println("wallY: " + boxWall.getFieldY() + " == " + (m*boxWall.getFieldX() + b));
                    if (boxWall.getFieldY() >= ((m*boxWall.getFieldX()) + b) - (boxWall.getColliderBounds() * 3) && 
                        boxWall.getFieldY() <= ((m*boxWall.getFieldX()) + b) + (boxWall.getColliderBounds() * 3) &&
                        boxWall.getFieldX() >= ((boxWall.getFieldY() - b) / m) - (boxWall.getColliderBounds() * 3) && 
                        boxWall.getFieldX() <= ((boxWall.getFieldY() - b) / m) + (boxWall.getColliderBounds() * 3)){
                            //System.out.println("wall in way");
                            System.out.println("Wall gradient: " + boxWall.getFieldY() + " = " + m + " * " +  + boxWall.getFieldX() + " + " + b + " obstruction");
                            return false;
                    } else {
                        System.out.println("Wall gradient: " + boxWall.getFieldY() + " = " + m + " * " +  + boxWall.getFieldX() + " + " + b + " not-obstructing");
                    }
                }
                return true;
            }
        }
        return true;
        
            /*
            int checkX = getFieldX();
        int checkY = getFieldY();
        
        while (checkDist <= range) {
            checkX = checkDist * (int) Math.round(Math.cos(checkRotationRadians));
            checkY = checkDist * (int) Math.round(Math.sin(checkRotationRadians));
            List<BoxWall> walls = getWorld().getObjects(BoxWall.class);
            if (walls != null) {
                for (BoxWall boxWall : walls) {
                    if (checkX > boxWall.getFieldX() - boxWall.getColliderBounds() && checkX < boxWall.getFieldX() + boxWall.getColliderBounds() && checkY > boxWall.getFieldY() - boxWall.getColliderBounds() && checkY < boxWall.getFieldY() + boxWall.getColliderBounds()) {
                        return false;
                    }   
                }      
            }
            if (player != null) {
                if (checkX > player.getFieldX() - player.getColliderRadius() && checkX < player.getFieldX() + player.getColliderRadius() && checkY > player.getFieldY() - player.getColliderRadius() && checkY < player.getFieldY() + player.getColliderRadius()) {
                    return true;
                }
            } 
            checkDist++;
        }
        */
       
    }
}
