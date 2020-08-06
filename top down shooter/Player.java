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
    private double cameraBias; // a higher bias will mean the camera drifts further away;
    private boolean mouseDown;

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Player() {
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
        cameraBias = 0.3;
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
    
        if (mouseInfo!=null) {
            lookAtPosition (mouseInfo.getX(), mouseInfo.getY());
            updateCamPlayerOffset(mouseInfo.getX(), mouseInfo.getY());
       
        }
      
        if (Greenfoot.mousePressed(null)) {
            mouseDown = true;
            shoot();
        } else if (Greenfoot.mouseClicked(null)){
            mouseDown = false;
        }
        if (mouseDown) {
           // shoot();
        }   
           
        
        updateLocation();
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
        int barrelXOffset = 16;
        int barrelYOffset = 75;
        double alpha = 0;
        double theta = Math.toDegrees(Math.atan(barrelYOffset / barrelXOffset));
        double h = Math.sqrt(Math.pow(barrelXOffset, 2) + Math.pow(barrelYOffset, 2));
        alpha = getRotation() - theta;
        int worldXOffset = (int) Math.round(Math.cos(Math.toRadians(alpha)) * h);
        int worldYOffset = (int) Math.round(Math.sin(Math.toRadians(alpha)) * h);
        
       
        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90, 20, 40, 20);
        getWorld().addObject(bullet, 0, 0);
    }
}
