import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * information for level1
 * 
 * @author Peter Jung
 * @version 1
 */
public class Level1 extends Level
{
    private int levelXOffset = -1;
    private int levelYOffset = -1;
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
        constructLevel(levelStructure, levelLength, levelXOffset, levelYOffset);
    }
}
