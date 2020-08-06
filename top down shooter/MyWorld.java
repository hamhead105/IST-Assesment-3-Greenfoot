import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        Camera camera = new Camera(0,0);
        addObject(camera, 0, 0);
        Bullet bullet = new Bullet(0, 0, 20, 5, 40, 0);
        addObject(bullet, 0, 0);
    }
}
