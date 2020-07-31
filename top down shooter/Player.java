import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends GameObject
{
    private int speed;

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Player() {
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
    }
    
    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
       
        if (Greenfoot.isKeyDown("W")) {
            moveY(-speed);
        }
        if (Greenfoot.isKeyDown("S")) {
            moveY(speed);
        }
        if (Greenfoot.isKeyDown("A")) {
             moveX(-speed);
        }
        if (Greenfoot.isKeyDown("D")) {
            moveX(speed);
        }
        if(Greenfoot.isKeyDown("right")) {
            turn(5);
        }
        if(Greenfoot.isKeyDown("left")) {
            turn(-5);
        }
        // setLocation (fieldX - Camera.getCamX(), fieldY - Camera.getCamY());
        // if (mouseInfo!=null) setRotation (mouseInfo.getX());
        if (mouseInfo!=null) lookAtPosition (mouseInfo.getX(), mouseInfo.getY());
        updateLocation();
    }
    
    public void lookAtPosition(int x, int y) {
        setRotation((int) Math.round(Math.toDegrees((Math.atan2(y - getFieldY(), x - getFieldX())))) + 90); 
        /* if (x - fieldX > 0 && y - fieldY > 0) {  // sector 1
           setRotation((int) Math.round(Math.atan2(fieldY - y, fieldX - x))); 
        }
        */
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
