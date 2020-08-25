import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        Player player = new Player(0,0);
        addObject(player, 0, 0);
        Camera camera = new Camera(0,0);
        addObject(camera, 0, 0);
        BoxWall wall1 = new BoxWall(200,200);
        addObject(wall1, 0, 0);
        BoxWall wall2 = new BoxWall(290,200);
        addObject(wall2, 0, 0);
        BoxWall wall3 = new BoxWall(200,800);
        addObject(wall3, 0, 0);
        BoxWall wall4 = new BoxWall(290,800);
        addObject(wall4, 0, 0);
        NPC enemy1 = new NPC(250, 500);
        addObject(enemy1, 0, 0);
        //Bullet bullet = new Bullet(0, 0, 20, 5, 40, 0);
        //addObject(bullet, 0, 0);
    }
}
