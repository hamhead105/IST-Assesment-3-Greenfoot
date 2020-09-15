import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameEndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameEndScreen extends World
{

    /**
     * Constructor for objects of class GameEndScreen.
     * 
     */
    public GameEndScreen(boolean winState, int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1);
        if (winState) { 
            getBackground().drawImage(new GreenfootImage("You Win", 64, null, null), 480, 40);
        } else {
            getBackground().drawImage(new GreenfootImage("You Lost", 64, null, null), 480, 40);
        }
        getBackground().drawImage(new GreenfootImage("Score: " + score, 32, null, null), 550, 120);
        ReturnButton returnButton = new ReturnButton();
        addObject(returnButton, 600, 350);

    }
}
