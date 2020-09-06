import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private int clickBoundaryX;
    private int clickBoundaryY;
    
    public Button() {
        clickBoundaryX = 200;
        clickBoundaryY = 100;
    }
    public void act() 
    {
        // Add your action code here.
    }
    
    public boolean wasClicked(int x, int y) {
        if (x > getX() - (clickBoundaryX / 2) &&
            x < getX() + (clickBoundaryX / 2) &&
            y > getY() - (clickBoundaryY / 2) &&
            y < getY() + (clickBoundaryY / 2)) {
            return true;
        }
        else {
            return false;
        }
    }
}
