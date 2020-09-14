import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
         getBackground().drawImage(new GreenfootImage("Operation Firestorm", 64, null, null), 400, 40);
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
