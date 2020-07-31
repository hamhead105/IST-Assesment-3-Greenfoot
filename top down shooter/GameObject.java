import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameObject extends Actor
{
    private int fieldX;
    private int fieldY;

    /**
     * Act - do whatever the GameObject wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
  
    public void act() 
    {
        // setLocation (fieldX - Camera.getCamX(), fieldY - Camera.getCamY());
    }    
    
    public void updateLocation() {
       setLocation (fieldX - Camera.getCamX(), fieldY - Camera.getCamY());
    }
    
    public int getFieldX() {
        return fieldX;
    }
    
    public void setFieldX(int x) {
        fieldX = x;
    }
    
     public int getFieldY() {
        return fieldY;
    }
    
    public void setFieldY(int y) {
        fieldY = y;
    }
    
    public void moveY(int y) {
        fieldY += y;
    }
    
    public void moveX(int x) {
        fieldX += x;
    }
}
