import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameSettings here.
 * 
 * @author Peter Jung
 * @version 1
 */
public class GameSettings extends Actor
{
    private static int difficulty;
    private static int playerClass;
    private static int highScore;
    private static int currentScore;

    public void updateGameSettings() {
        if (UserInfo.isStorageAvailable()) {
            UserInfo info = UserInfo.getMyInfo();
            highScore = info.getScore();
        }
    }

    public GameSettings() {
        difficulty = 2;
    }

    public void act() 
    {
        //System.out.println(difficulty);
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
        if (UserInfo.isStorageAvailable()) {
            UserInfo info = UserInfo.getMyInfo();
            return info.getScore();
        }
        else { 
            return 15;
        }
    }

    public static void setCurrentScore(int newCurrentScore) {
        currentScore = newCurrentScore;
    }
    
    public static void addCurrentScore(int value) {
        currentScore += value;
    }

    public static int getCurrentScore() {
        return currentScore;
    }

    public static boolean checkScore() {
        UserInfo info = UserInfo.getMyInfo();
        if (UserInfo.isStorageAvailable()) {
            if (currentScore > info.getScore()) {
                //System.out.println("new score: " + info.getScore() + " to " + currentScore);
                info.setScore(currentScore);
                return true;
            }
            else {
                //System.out.println("old score");
                return false;
            }

        }
        else {
            System.out.println("unavailable");
            return false;
        }
    }
}

