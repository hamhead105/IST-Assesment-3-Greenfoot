import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private int health;
    private int traceHealth;
    
    public HealthBar() {
        health = 100;
        traceHealth = 100;
    }
    
    public void act() 
    {
         GreenfootImage image = new GreenfootImage(800, 800);
         image.setColor(Color.BLACK);
         image.drawRect(99, 649, 601, 31);
         image.setColor(Color.BLACK);
         image.fillRect(100, 650, 600, 30);
         image.setColor(Color.RED);
         image.fillRect(100, 650, traceHealth * 6, 30);
         image.setColor(Color.WHITE);
         image.fillRect(100, 650, health * 6, 30);
         setImage(image);
         if (traceHealth > health) {
             traceHealth -= 1;
         }
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
}
