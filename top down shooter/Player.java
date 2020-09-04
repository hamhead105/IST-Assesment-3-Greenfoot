 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends GameObject
{
    private int speed;
    private double cameraBias; // a higher bias will mean the camera drifts further away;
    private boolean mouseDown;    
    private int health;
    private int colliderRadius = 30;
    private int fireRate;
    private int nextShotAvailable;
    
    //gun stats
    private int spreadCurrent;
    private int spreadMin;
    private int spreadMax;
    private int spreadShotGain;
    private int spreadRecover;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Player(int x, int y) {
        super(x,y);
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
        cameraBias = 0.35;
        health = 100;
        fireRate = 100;
        nextShotAvailable = (int) System.currentTimeMillis();
   
        spreadMin = 1;
        spreadMax = 15;
        spreadShotGain = 10; 
        spreadRecover = 8;
        spreadCurrent = spreadMin;
    }
    
    public void act() 
    {        
        if (health >= 0) {           
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
           
            if (Greenfoot.isKeyDown("W") && !collisionCheck(1)) {
                moveY(-speed);
            }
            if (Greenfoot.isKeyDown("S") && !collisionCheck(3)) {
                moveY(speed);
            }
            if (Greenfoot.isKeyDown("A") && !collisionCheck(4)) {
                 moveX(-speed);
            }
            if (Greenfoot.isKeyDown("D") && !collisionCheck(2)) {
                moveX(speed);
            }
            if(Greenfoot.isKeyDown("right")) {
                turn(5);
            }
            if(Greenfoot.isKeyDown("left")) {
                turn(-5);
            }
        
            if (mouseInfo!=null) {
                lookAtPosition (mouseInfo.getX(), mouseInfo.getY());
                updateCamPlayerOffset(mouseInfo.getX(), mouseInfo.getY());           
            }         
            if (Greenfoot.mousePressed(null)) {
                mouseDown = true;
                //shoot();
            } else if (Greenfoot.mouseClicked(null)){
                mouseDown = false;
            }
            if (mouseDown && weaponReady()) {
                shoot();
            }
        } else {
            fadeAway();
        }
        updateLocation();
        updateWeaponControl();
    }
        
    public void lookAtPosition(int x, int y) {
        setRotation((int) Math.round(Math.toDegrees((Math.atan2(y + Camera.getCamY() - getFieldY(), x + Camera.getCamX() - getFieldX())))) + 90); 
        /* if (x - fieldX > 0 && y - fieldY > 0) {  // sector 1
           setRotation((int) Math.round(Math.atan2(fieldY - y, fieldX - x))); 
        }
        */
    }
    
    public void updateCamPlayerOffset(int x, int y) {
        Camera.setTargetCamX((int) Math.round(this.getFieldX() + ((x - getWorld().getWidth() / 2) * cameraBias) - (getWorld().getWidth() / 2)));
        Camera.setTargetCamY((int) Math.round (this.getFieldY() + ((y - getWorld().getHeight() / 2) * cameraBias) - (getWorld().getHeight() / 2)));
        //Camera.setCamPosition((int) Math.round(this.getFieldX() + (x * cameraBias) - (getWorld().getWidth() / 2)), (int) Math.round (this.getFieldY() + (y * cameraBias) - getWorld().getHeight()));
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void shoot() {
        Greenfoot.playSound("HK416.mp3");
        int barrelXOffset = 16;
        int barrelYOffset = 75;
        double alpha = 0;
        double theta = Math.toDegrees(Math.atan(barrelYOffset / barrelXOffset));
        double h = Math.sqrt(Math.pow(barrelXOffset, 2) + Math.pow(barrelYOffset, 2));
        alpha = getRotation() - theta;
        int worldXOffset = (int) Math.round(Math.cos(Math.toRadians(alpha)) * h);
        int worldYOffset = (int) Math.round(Math.sin(Math.toRadians(alpha)) * h);
       
        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + ((spreadCurrent / 2) - Greenfoot.getRandomNumber(spreadCurrent)), 50, 40, 20);
        getWorld().addObject(bullet, 0, 0);
        spreadCurrent += spreadShotGain;
    }
    
    public void hit(int damage) {
        health -= damage;
    }
    
    public int getColliderRadius() {
        return colliderRadius;
    }
    
    public void fadeAway() {
        getImage().setTransparency(getImage().getTransparency() - 30);
        if (getImage().getTransparency() <= 30) {
            getWorld().removeObject(this);
        }
    }
    
    private boolean collisionCheck(int direction) {
        // 1 - up
        // 2 - right
        // 3 - down
        // 4 - left
        List<BoxWall> boxWalls = getWorld().getObjects(BoxWall.class);
        if (direction == 1) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldY() - 5 < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldY() - 5 > boxWall.getFieldY() - boxWall.getColliderBounds() && getFieldX() < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldX() > (boxWall.getFieldX() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else if (direction == 2) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldX() + 5 > boxWall.getFieldX() - boxWall.getColliderBounds() && getFieldX() + 5 < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldY() < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldY() > (boxWall.getFieldY() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else if (direction == 3) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldY() + 5 > boxWall.getFieldY() - boxWall.getColliderBounds() && getFieldY() + 5 < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldX() < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldX() > (boxWall.getFieldX() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
       else if (direction == 4) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldX() - 5 < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldX() - 5 > boxWall.getFieldX() - boxWall.getColliderBounds() && getFieldY() < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldY() > (boxWall.getFieldY() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else {
            return false;
        }
    }
    
    public boolean weaponReady() {
        if ((int)System.currentTimeMillis() >= nextShotAvailable) {
            nextShotAvailable = (int) System.currentTimeMillis() + fireRate;
            return true;
        } else {
            return false;
        }
    }
    
    public void updateWeaponControl() {
        if (spreadCurrent > spreadMax) spreadCurrent = spreadMax;
        spreadCurrent += (int) Math.round((spreadMin - spreadCurrent) / spreadRecover);
    }
}
