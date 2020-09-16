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
        super(1200, 700, 1);
        getBackground().setColor(new Color(5,5,50));
        getBackground().fillRect(0, 0, 1200, 700);
        if (winState) { 
            GreenfootImage titleImage = new GreenfootImage("You Win", 64, Color.WHITE, null);
            getBackground().drawImage(titleImage, (getWidth() / 2) - (titleImage.getWidth() / 2), 40);
        } else {
            GreenfootImage titleImage = new GreenfootImage("You Lost", 64, Color.WHITE, null);
            getBackground().drawImage(titleImage, (getWidth() / 2) - (titleImage.getWidth() / 2), 40);
        }
        if (GameSettings.checkScore()) {
            GreenfootImage scoreImage = new GreenfootImage("NEW HIGH SCORE: " + GameSettings.getCurrentScore(), 32, Color.WHITE, null);
            getBackground().drawImage(scoreImage, (getWidth() / 2) - (scoreImage.getWidth() / 2), 160);
        } else {
            GreenfootImage scoreImage = new GreenfootImage("Score: " + GameSettings.getCurrentScore(), 32, Color.WHITE, null);
            getBackground().drawImage(scoreImage, (getWidth() / 2) - (scoreImage.getWidth() / 2), 120);
            GreenfootImage textImage = new GreenfootImage("High Score: " + GameSettings.getHighScore(), 32, Color.WHITE, null);
            getBackground().drawImage(textImage, (getWidth() / 2) - (textImage.getWidth() / 2), 160);
        }
        ReturnButton returnButton = new ReturnButton();
        addObject(returnButton, 600, 350);
        if (GameSettings.getCurrentScore() > GameSettings.getHighScore()) {

        }
    }
}
