import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author Peter Jung
 * @version 1
 */
public class Level1 extends Level
{
    
    private int levelLength = 20;
    int[] levelStructure = {
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
        1,0,0,1,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1,1,1,
        1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,1,
        1,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,1,0,0,1,
        1,0,0,1,0,0,0,1,1,0,2,0,1,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,
        1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,
        1,0,1,1,1,0,0,1,1,0,0,0,0,0,1,0,0,0,2,1,
        1,0,0,1,0,0,0,1,1,0,0,0,0,0,1,0,0,1,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,1,
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };    
    
    public Level1() {
        constructLevel(levelStructure, levelLength);
    }
}
