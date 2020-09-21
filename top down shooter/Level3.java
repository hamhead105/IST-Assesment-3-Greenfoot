import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * information for level 3
 * 
 * @author Josh Vos 
 * @version 1
 */
public class Level3 extends Level
{
    private int levelXOffset = -1;
    private int levelYOffset = -1;
    private int levelLength = 42;
    int[] levelStructure = {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,0,0,0,0,0,0,0,0,0,0,0,2,1,2,1,2,1,0,0,0,0,0,0,0,2,1,0,0,0,1,1,1,0,0,1,0,0,0,0,0,1,
            1,0,0,0,0,1,1,1,1,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,2,0,1,1,0,0,0,0,0,0,1,0,0,1,
            1,0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,1,0,0,0,0,1,1,1,1,
            1,0,0,1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,
            1,0,0,1,0,2,0,1,0,0,1,1,1,0,0,2,0,1,2,1,2,1,1,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,2,0,1,
            1,0,0,1,1,0,0,1,0,2,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,2,0,1,1,1,1,0,0,1,
            1,0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,1,2,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,1,1,1,1,1,1,0,0,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,2,1,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,0,1,0,0,0,2,0,0,1,1,1,0,0,0,1,1,0,0,0,0,1,
            1,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,1,
            1,0,1,0,0,0,1,1,1,1,0,0,1,2,0,1,0,0,0,0,0,0,1,0,2,0,0,2,2,1,0,0,0,1,1,1,1,1,0,1,1,1,
            1,0,1,1,0,0,1,1,1,1,0,0,0,1,0,1,0,0,1,1,0,1,1,0,0,0,0,0,0,1,0,2,0,0,0,0,0,0,0,0,0,1,
            1,0,1,1,0,0,0,1,1,0,0,0,0,0,0,1,0,0,1,0,0,2,1,0,0,0,2,0,0,1,0,0,0,0,0,1,1,0,0,0,0,1,
            1,0,1,1,0,2,0,0,0,0,1,1,1,0,0,0,0,0,0,0,4,0,0,0,2,0,0,0,2,1,0,0,0,0,0,1,1,0,0,2,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,2,0,0,1,1,1,1,1,1,1,1,0,1,1,0,0,1,1,0,0,0,0,1,
            1,0,0,0,1,0,0,1,0,1,1,0,1,1,1,0,0,0,1,1,0,1,1,2,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,1,1,
            1,0,2,1,1,1,0,1,0,1,1,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,1,1,1,
            1,0,0,1,1,1,1,1,0,1,1,0,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,0,0,0,1,0,0,0,1,1,1,
            1,0,0,0,1,1,1,1,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,1,1,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,1,0,0,2,0,0,1,0,1,0,2,0,0,0,0,0,1,1,1,0,1,0,0,0,1,
            1,0,1,0,0,1,1,1,0,1,1,1,0,1,0,0,0,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,1,1,1,1,0,1,0,2,0,1,
            1,0,1,0,0,1,1,1,0,1,1,0,0,1,1,0,0,0,0,1,0,0,0,0,0,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,
            1,0,1,0,0,1,2,1,0,0,1,0,1,1,1,1,0,0,0,1,0,0,2,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,1,0,1,
            1,0,1,0,0,1,0,1,1,0,1,0,0,1,1,1,1,0,0,0,0,0,1,1,1,0,1,1,1,0,1,0,0,1,0,0,0,1,0,0,0,1,
            1,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0,2,0,1,0,0,0,0,0,0,0,1,
            1,0,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,0,1,
            1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0,0,1,0,0,1,0,0,0,0,0,0,0,1,
            1,0,1,1,1,1,0,2,1,1,1,1,1,1,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0,1,0,0,1,1,1,1,1,0,0,0,1,
            1,0,0,0,0,1,1,1,1,0,0,2,1,1,0,1,1,1,1,0,2,0,1,1,1,0,1,1,1,0,1,0,0,0,0,0,0,0,2,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
        };    


    public Level3() {
        constructLevel(levelStructure, levelLength);
    }
}