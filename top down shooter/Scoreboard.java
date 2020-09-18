import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scoreboard here.
 * 
 * @author Peter Jung
 * @version 1
 */
public class Scoreboard extends Actor
{
    private int score;
    
    public void act() 
    {
        score = GameSettings.getCurrentScore();
        GreenfootImage image = new GreenfootImage(1600, 1200);
        image.setColor(Color.WHITE);
        image.drawRect(949, 729, 401, 61);
        image.setColor(new Color(100,100,150));
        image.fillRect(950, 730, 400, 60);
       
        image.drawImage(new GreenfootImage("score: " + Integer.toString(score), 40, Color.WHITE, null), 970, 740);
        setImage(image);
    }
    
}
