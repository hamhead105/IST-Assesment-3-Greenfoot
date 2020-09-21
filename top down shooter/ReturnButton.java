import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Image;
/**
 * On click, returns to main menu
 * 
 * @author Peter Jung
 * @version 1
 */
public class ReturnButton extends Button
{
    public ReturnButton() {
        super(400, 100, 0, -50);
    }

    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        Color currentColor = new Color(5,5,50);
        if (mouseInfo != null) {
            if (wasClicked(mouseInfo.getX(), mouseInfo.getY())) {
                if (Greenfoot.mouseClicked(null)) {
                    returnToMenu();
                } else {
                    currentColor = new Color(100,100,150);
                }
            }  
        }
        GreenfootImage image = new GreenfootImage(1200, 1200);
        image.setColor(Color.WHITE);
        image.drawRect(399, 599, 401, 101);
        image.setColor(currentColor);
        image.fillRect(400, 600, 400, 100);
       
        image.drawImage(new GreenfootImage("Return to menu", 40, Color.WHITE, null), 440, 630);
        setImage(image);
    }

    public void returnToMenu() {      
        MainMenu main = new MainMenu();
        Greenfoot.setWorld(main);
    }
}
