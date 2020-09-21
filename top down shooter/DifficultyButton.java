import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to toggle between difficulties
 * 
 * @author (Peter Jung) 
 * @version 1
 */
public class DifficultyButton extends Button
{
    private static int difficulty = 1;

    public DifficultyButton() {
        super(400, 100, 0, -50);
    }

    public void act() {
        Color currentColor = new Color(5,5,50);
        GameSettings.setDifficulty(difficulty);

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo != null) {
            if (wasClicked(mouseInfo.getX(), mouseInfo.getY())) {
                if (Greenfoot.mouseClicked(null)) {
                    cycleDifficulty();
                } else {
                    currentColor = new Color(100,100,150);
                }
            }  
        }
        String difficultyName = "";
        switch(difficulty) {
            case 1:
            difficultyName = "Easy";
            break;
            case 2:
            difficultyName = "Medium";
            break;
            case 3:
            difficultyName = "Hard";
            break;
        }
        GreenfootImage image = new GreenfootImage(1200, 1200);
        image.setColor(Color.WHITE);
        image.drawRect(399, 599, 401, 101);
        image.setColor(currentColor);
        image.fillRect(400, 600, 400, 100);

        image.drawImage(new GreenfootImage("Difficulty: " + difficultyName, 40, Color.WHITE, null), 440, 630);
        setImage(image);

    }

    public void cycleDifficulty() {
        difficulty++;
        if (difficulty > 3) difficulty = 1;
    }
}
