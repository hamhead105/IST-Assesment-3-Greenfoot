import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * UI element for weapon statistics
 * 
 * @author Peter Jung
 * @version 1
 */
public class WeaponUI extends Actor
{
    private int currentAmmo;
    private int maxAmmo;

    public void setAmmoCount(int currentAmmo, int maxAmmo, int gunType) {
        // set weapon image and current ammo
        this.currentAmmo = currentAmmo;
        this.maxAmmo = maxAmmo;
        GreenfootImage image = new GreenfootImage(1200, 1200);
        image.setColor(Color.WHITE);
        image.drawRect(949, 729, 231, 151);
        image.setColor(new Color(100,100,150));
        image.fillRect(950, 730, 230, 150);
       
        image.drawImage(new GreenfootImage(currentAmmo + "/" + maxAmmo, 40, Color.WHITE, null), 970, 830);
        GreenfootImage gunIcon = null;
        if (gunType == 1) {
            gunIcon = new GreenfootImage("M4 Icon.png");
            gunIcon.scale(150,60);
            //gunIcon.setImage();
        }
        if (gunType == 2) {
            gunIcon = new GreenfootImage("Mk14 Icon.png");
            gunIcon.scale(200,60);
            //gunIcon.setImage();
        }
        image.drawImage(gunIcon, 970, 750);
        setImage(image);
    }
}
