import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends Level
{
    private int levelXOffset = -1;
    private int levelYOffset = -1;
    private int levelLength = 20;
    int[] levelStructure = {
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
        1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1,1,1,
        1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,1,
        1,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,
        1,0,1,1,1,0,0,1,1,0,0,0,0,0,1,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };    
    
    public Level1() {
        constructLevel(levelStructure);
    }
}
