import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameObject here.
 * 
 * @author Peter Jung
 * @version 1
 */
public class GameObject extends Actor
{
    private int fieldX;
    private int fieldY;
    
    public GameObject(int x, int y) {
        this.fieldX = x;
        this.fieldY = y;
    }
  
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
    
    public void setFieldPosition(int x, int  y) {
        fieldX = x;
        fieldY = y;
    }
}
