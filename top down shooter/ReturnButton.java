import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Image;
/**
 * Write a description of class ReturnButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReturnButton extends Button
{
    private int level;
    public ReturnButton() {
        super(400, 100, 0, -50);
    }

    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null) && mouseInfo != null) {
            if (wasClicked(mouseInfo.getX(), mouseInfo.getY())) {
                returnToMenu();
            }
        }
         GreenfootImage image = new GreenfootImage(1200, 1200);
        image.setColor(Color.BLACK);
        image.drawRect(399, 599, 401, 101);
        image.setColor(Color.WHITE);
        image.fillRect(400, 600, 400, 100);
       
        image.drawImage(new GreenfootImage("Return to menu", 40, null, null), 440, 630);
        setImage(image);
    }

    public void returnToMenu() {      
        MainMenu main = new MainMenu();
        Greenfoot.setWorld(main);
    }
}
