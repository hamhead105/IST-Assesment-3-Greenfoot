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
    private int clickYOffset;
    private int clickXOffset;
    
    public Button(int x, int y, int xOffset, int yOffset) {
        this.clickBoundaryX = x;
        this.clickBoundaryY = y;
        this.clickXOffset = xOffset;
        this.clickYOffset = yOffset;
    }
    public void act() 
    {
        // Add your action code here.
    }
    
    public boolean wasClicked(int x, int y) {
        if (x + clickXOffset > getX() - (clickBoundaryX / 2) &&
            x + clickXOffset < getX() + (clickBoundaryX / 2) &&
            y + clickYOffset > getY() - (clickBoundaryY / 2) &&
            y + clickYOffset < getY() + (clickBoundaryY / 2)) {
            return true;
        }
        else {
            return false;
        }
    }
}
