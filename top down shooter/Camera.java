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
        // Add your action code here.
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
}
