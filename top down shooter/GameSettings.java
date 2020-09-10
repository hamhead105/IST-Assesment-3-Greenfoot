import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameSettings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameSettings extends Actor
{
    private static int difficulty;
    private static int playerClass;
    private static int highScore;
    private static int currentScore;
    
    public GameSettings() {
        difficulty = 2;
    }
    
    public void act() 
    {
        System.out.println(difficulty);
    }    
    
    public static void setDifficulty(int newDifficulty) {
        difficulty = newDifficulty;
    }
    
    public static int getDifficulty() {
        return difficulty;
    }
    
    public static void setPlayerClass(int newClass) {
        playerClass = newClass;
    }
    
    public static int getPlayerClass() {
        return playerClass;
    }
    
    public static void setHighScore(int newHighScore) {
        highScore = newHighScore;
    }
    
    public static int getHighScore() {
        return highScore;
    }
    
     public static void setCurrentScore(int newCurrentScore) {
        currentScore = newCurrentScore;
    }
    
    public static int getCurrentScore() {
        return currentScore;
    }
}
