import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * constructor for the main menu
 * 
 * @author Peter Jung
 * @version 1
 */
public class MainMenu extends World
{

    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1);

        getBackground().setColor(new Color(5,5,50));
        getBackground().fillRect(0, 0, 1200, 700);

        GreenfootImage titleImage = new GreenfootImage("Operation Firestorm", 64, Color.WHITE, null);
        getBackground().drawImage(titleImage, (getWidth() / 2) - (titleImage.getWidth() / 2), 40);
        GreenfootImage textImage = new GreenfootImage("High Score: " + GameSettings.getHighScore(), 30, Color.WHITE, null);
        getBackground().drawImage(textImage, (getWidth() / 2) - (textImage.getWidth() / 2), 120);
        LevelButton levelButton1 = new LevelButton(1);
        addObject(levelButton1, 350, 250);
        LevelButton levelButton2 = new LevelButton(2);
        addObject(levelButton2, 600, 250);
        LevelButton levelButton3 = new LevelButton(3);
        addObject(levelButton3, 850, 250);
        DifficultyButton difficultyButton = new DifficultyButton();
        addObject(difficultyButton, 600, 350);
        ClassButton classButton = new ClassButton();
        addObject(classButton, 600, 470);

    }
}
