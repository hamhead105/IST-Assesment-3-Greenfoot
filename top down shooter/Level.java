import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{
    private int levelXOffset = -1;
    private int levelYOffset = -1;
    private int levelLength = 20;
    int[] levelStructure;
    
    ArrayList<BoxWall> boxWalls = new ArrayList<BoxWall>();
    ArrayList<NPC> npcs = new ArrayList<NPC>();
    ArrayList<Flag> flags = new ArrayList<Flag>();
    ArrayList<NavPoint> navPoints = new ArrayList<NavPoint>();
    
    public Level()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1, false);   
    }
    
    public void constructLevel(int[] levelStructure) {
        this.levelStructure = levelStructure;
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
        //Border border = new Border();
        //addObject(border, 600, 350);
        createGameObjects();
    }
    
    public void addGameObject(int objectType, int x, int y) {
        if (objectType == 1) {
            boxWalls.add(new BoxWall(x,y));
        }
        if (objectType == 2) {
            npcs.add(new NPC(x,y));
        }
        if (objectType == 3) {
            navPoints.add(new NavPoint(x,y));
        }
        if (objectType == 4) {
            flags.add(new Flag(x,y));
        }
    }
    
    public void createGameObjects() {
        for (BoxWall boxWall : boxWalls) {
            addObject(boxWall, 0, 0);
        }
        for (NPC npc : npcs) {
            addObject(npc, 0, 0);
        }
        for (Flag flag : flags) {
            addObject(flag, 0, 0);
        }
        for (NavPoint navPoint : navPoints) {
            addObject(navPoint, 0, 0);
        }
    }
}
