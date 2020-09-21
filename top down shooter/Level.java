import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Creates level based on an array, iterates through each number in the array and places it into the world.
 * 
 * @author Peter Jung 
 * @version 1
 */
public class Level extends World
{
    private int levelXOffset = -1;
    private int levelYOffset = -1;
    private int levelLength;
    int[] levelStructure;
    
    ArrayList<BoxWall> boxWalls = new ArrayList<BoxWall>();
    ArrayList<NPC> npcs = new ArrayList<NPC>();
    ArrayList<Flag> flags = new ArrayList<Flag>();
    ArrayList<NavPoint> navPoints = new ArrayList<NavPoint>();
    
    public Level()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1, false);   
        getBackground().setColor(new Color(5,5,50));
        getBackground().fillRect(0, 0, 1200, 700);
    }
    
    public void constructLevel(int[] levelStructure, int levelLength) {
        this.levelStructure = levelStructure;
        this.levelLength = levelLength;
        createLevel();     
        HealthBar healthBar = new HealthBar();
        addObject(healthBar, 600, 400);
        WeaponUI weaponUI = new WeaponUI();
        addObject(weaponUI, 600, 400);
        Scoreboard scoreBoard = new Scoreboard();
        addObject(scoreBoard, 300, -120);
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
        createGameObjects();
    }
    
    public void addGameObject(int objectType, int x, int y) {
        if (objectType == 1) {
            boxWalls.add(new BoxWall(x,y));
        }
        else if (objectType == 2) {
            npcs.add(new NPC(x,y));
        }
        else if (objectType == 3) {
            navPoints.add(new NavPoint(x,y));
        }
        else if (objectType == 4) {
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
