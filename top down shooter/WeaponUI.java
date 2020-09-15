import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WeaponUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeaponUI extends Actor
{
    private int currentAmmo;
    private int maxAmmo;
    
    public void act() 
    {
         
    }
    
    public void setAmmoCount(int currentAmmo, int maxAmmo, int gunType) {
        this.currentAmmo = currentAmmo;
        this.maxAmmo = maxAmmo;
        GreenfootImage image = new GreenfootImage(1200, 1200);
        image.setColor(Color.BLACK);
        image.drawRect(949, 729, 231, 151);
        image.setColor(Color.WHITE);
        image.fillRect(950, 730, 230, 150);
       
        image.drawImage(new GreenfootImage(currentAmmo + "/" + maxAmmo, 40, null, null), 970, 830);
        GreenfootImage gunIcon = null;
        if (gunType == 1) {
            gunIcon = new GreenfootImage("M4 icon.jpg");
            gunIcon.scale(150,60);
            //gunIcon.setImage();
        }
        if (gunType == 2) {
            gunIcon = new GreenfootImage("M4 icon.jpg");
            gunIcon.scale(150,60);
            //gunIcon.setImage();
        }
        image.drawImage(gunIcon, 980, 750);
        setImage(image);
    }
}
