import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Base class for buttons, contains information for if mouse is hovering over the button
 * 
 * @author Peter Jung
 * @version 1
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
    
    // is the mouse hovering over the button
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
