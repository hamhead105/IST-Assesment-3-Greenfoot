import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * static information about camera position.
 * 
 * @author Peter Jung
 * @version 1
 */
public class Camera extends Actor
{
    private static int camX;
    private static int camY;
    
    private static int targetCamX;
    private static int targetCamY;
    
    private static double cameraSpeed;
    
    public Camera (int camX, int camY) {
        this.camX = camX;
        this.camY = camY;
        
        cameraSpeed = 10;
    }
    
    public void act() 
    {
        // use proportional controller to move camera
        camX += (int) Math.round((targetCamX - camX) / cameraSpeed);
        camY += (int) Math.round((targetCamY - camY) / cameraSpeed);
    } 
    
    public static void setTargetCamX(int x) {
        targetCamX = x;
    }
    
    public static int getTargetCamX() {
        return targetCamX;
    }
    
    public static void setTargetCamY(int y) {
        targetCamY = y;
    }
    
    public static int getTargetCamY() {
        return targetCamY;
    }
    
    public static void setTargetCamPosition(int x, int y) {
        targetCamX = x;
        targetCamY = y;
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
