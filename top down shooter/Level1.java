import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{
    private int levelXOffset = -1;
    private int levelYOffset = -1;
    private int levelLength = 20;
    int[] levelStructure = {
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
        1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };    
    
    ArrayList<BoxWall> boxWalls = new ArrayList<BoxWall>();
    
    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1);
        createLevel();        
    }
    
    public void createLevel() {
        int i = 0;
        for (int object : levelStructure) {
            addGameObject(object, ((i % levelLength) * 90) + (90 * levelXOffset), ((int) Math.floor(i / levelLength) * 90) + (90 * levelYOffset));
            i++;
        }
        
        Player player = new Player(0,0);  
        addObject(player,0,0);
        Camera camera = new Camera(0,0);
        addObject(camera,0,0);
        Border border = new Border();
        addObject(border, 600, 350);
        createGameObjects();
    }
    
    public void addGameObject(int objectType, int x, int y) {
        if (objectType == 1) {
            boxWalls.add(new BoxWall(x,y));
        }
    }
    
    public void createGameObjects() {
        for (BoxWall boxWall : boxWalls) {
            //System.out.println(boxWall  + " " + boxWall.getFieldX() + " " + boxWall.getFieldY());
            addObject(boxWall, 0, 0);
        }
    }
}
