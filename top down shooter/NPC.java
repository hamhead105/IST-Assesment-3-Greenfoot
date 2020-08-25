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
       
        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + shootSpread / 2 - Greenfoot.getRandomNumber(shootSpread) , 50, 40, 0);
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
            int rotationToPlayer = (int) Math.round (Math.toDegrees(Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX())));           
            List<BoxWall> walls = getWorld().getObjects(BoxWall.class);
            if (walls != null) {                  
                for (BoxWall boxWall : walls) {
                    int[][] cornerPositions = new int[4][2];
                    cornerPositions[0][0] = boxWall.getFieldX() - boxWall.getColliderBounds(); //top left
                    cornerPositions[0][1] = boxWall.getFieldY() + boxWall.getColliderBounds();
                    cornerPositions[1][0] = boxWall.getFieldX() + boxWall.getColliderBounds(); //top right
                    cornerPositions[1][1] = boxWall.getFieldY() + boxWall.getColliderBounds();
                    cornerPositions[2][0] = boxWall.getFieldX() + boxWall.getColliderBounds(); // bottom right
                    cornerPositions[2][1] = boxWall.getFieldY() - boxWall.getColliderBounds();
                    cornerPositions[3][0] = boxWall.getFieldX() - boxWall.getColliderBounds(); // bottom left
                    cornerPositions[3][1] = boxWall.getFieldY() - boxWall.getColliderBounds();
                    
                    int[] rotationCorners = new int[4];
                    rotationCorners[0] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[0][1] - this.getFieldY(), cornerPositions[0][0] - this.getFieldX())));
                    rotationCorners[1] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[1][1] - this.getFieldY(), cornerPositions[1][0] - this.getFieldX())));
                    rotationCorners[2] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[2][1] - this.getFieldY(), cornerPositions[2][0] - this.getFieldX())));
                    rotationCorners[3] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[3][1] - this.getFieldY(), cornerPositions[3][0] - this.getFieldX())));
                    System.out.println("corner 1: " + rotationCorners[0] + " corner 2: " + rotationCorners[1] + " corner 3: " + rotationCorners[2] + " corner 4: " + rotationCorners[3]);
                    int largest = rotationCorners[0];
                    int smallest = rotationCorners[0];
                    int i;
                    for (i=1;i<rotationCorners.length; i++) {
                        if (rotationCorners[i] > largest) largest = rotationCorners[i];
                    }
                    for (i=1;i<rotationCorners.length; i++) {
                        if (rotationCorners[i] < smallest) smallest = rotationCorners[i];
                    }
                    double distanceToWall = Math.sqrt((Math.pow(boxWall.getFieldX() - this.getFieldX(), 2) + (Math.pow(boxWall.getFieldY() - this.getFieldY(), 2))));
                    //System.out.println("smallest: " + smallest + " | largest: " + largest + " | player: " + rotationToPlayer);
                    if (rotationToPlayer > smallest && rotationToPlayer < largest && distanceToWall < distanceToPlayer) {
                        return false;   
                    }
                }
                return true;
            }
        }
        return true;
       
    }
}
