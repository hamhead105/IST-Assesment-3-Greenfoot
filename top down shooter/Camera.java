import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Camera here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Camera extends Actor
{
    private static int camX;
    private static int camY;
    
    public Camera (int camX, int camY) {
        this.camX = camX;
        this.camY = camY;
    }
    
    public void act() 
    {
        /* basic camera moving
        if (Greenfoot.isKeyDown("down")) {
            camY -= 5;
        }
        if (Greenfoot.isKeyDown("up")) {
            camY += 5;
        }
         if (Greenfoot.isKeyDown("right")) {
            camX += 5;
        }
        if (Greenfoot.isKeyDown("left")) {
            camX -= 5;
        }
        */
    } 
    
    public static void setCamX(int x) {
        camX = x;
    }
    
    public static int getCamX() {
        return camX;
    }
    
    public static void setCamY(int y) {
        camY = y;
    }
    
    public static int getCamY() {
        return camY;
    }
    
    public static void setCamPosition(int x, int y) {
        camX = x;
        camY = y;
    }
}
