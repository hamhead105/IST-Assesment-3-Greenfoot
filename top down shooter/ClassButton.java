import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to change between classes in game
 * 
 * @author Peter Jung
 * @version 1
 */
public class ClassButton extends Button
{
    private static int playerClass = 1;
    
    public ClassButton() {
        super(400, 100, 0, -50);
    }
    
    public void act() {
        GameSettings.setPlayerClass(playerClass);
        Color currentColor = new Color(5,5,50);
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
          if (mouseInfo != null) {
            if (wasClicked(mouseInfo.getX(), mouseInfo.getY())) {
                if (Greenfoot.mouseClicked(null)) {
                    cycleClass();
                } else {
                    currentColor = new Color(100,100,150);
                }
            }  
        }
        String className = "";
        switch(playerClass) {
            case 1:
            className = "Assault";
            break;
            case 2:
            className = "Recon";
            break;
        }
        GreenfootImage image = new GreenfootImage(1200, 1200);
        image.setColor(Color.WHITE);
        image.drawRect(399, 599, 401, 101);
        image.setColor(currentColor);
        image.fillRect(400, 600, 400, 100);
       
        image.drawImage(new GreenfootImage("Class: " + className, 40, Color.WHITE, null), 440, 630);
        setImage(image);
    }
    
    public void cycleClass() {
        playerClass++;
        if (playerClass > 2) playerClass = 1;
    }
}
