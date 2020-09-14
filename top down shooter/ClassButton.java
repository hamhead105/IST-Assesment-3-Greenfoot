import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class difficultyButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClassButton extends Button
{
    private int playerClass;
    
    public ClassButton() {
        super(400, 100, 0, -50);
        playerClass = 1;
    }
    
    public void act() {
        GameSettings.setDifficulty(playerClass);
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null)) {
            if (wasClicked(mouseInfo.getX(), mouseInfo.getY())) {
                cycleClass();
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
        image.setColor(Color.BLACK);
        image.drawRect(399, 599, 401, 101);
        image.setColor(Color.WHITE);
        image.fillRect(400, 600, 400, 100);
       
        image.drawImage(new GreenfootImage("Class: " + className, 40, null, null), 440, 630);
        setImage(image);
    }
    
    public void cycleClass() {
        playerClass++;
        if (playerClass > 2) playerClass = 1;
    }
}
