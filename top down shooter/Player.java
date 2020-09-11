import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends GameObject
{
    private int speed;
    private double cameraBias; // a higher bias will mean the camera drifts further away;
    private boolean mouseDown;    
    private int health;
    private int colliderRadius = 30;
    private int fireRate;
    private int nextShotAvailable;

    //gun stats
    private int spreadCurrent;
    private int spreadMin;
    private int spreadMax;
    private int spreadShotGain;
    private int spreadRecover;

    private int maxAmmo;
    private int currentAmmo;
    private long reloadFinishTime;
    private boolean isReloading;
    private int reloadTime;
    private int gunType;

    private int FOV;

    public Player(int x, int y) {
        super(x,y);
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 2;
        cameraBias = 0.35;
        health = 100;
        fireRate = 120;
        nextShotAvailable = (int) System.currentTimeMillis();

        spreadMin = 1;
        spreadMax = 15;
        spreadShotGain = 10; 
        spreadRecover = 8;
        spreadCurrent = spreadMin;

        isReloading = false;        
        maxAmmo = 30;
        currentAmmo = maxAmmo;
        reloadFinishTime = System.currentTimeMillis(); 
        reloadTime = 3000;     
        gunType = 1;
        FOV = 110;
    }

    public void act() 
    {        
        if (health >= 0) {           
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();

            if (Greenfoot.isKeyDown("W") && !collisionCheck(1)) {
                moveY(-speed);
            }
            if (Greenfoot.isKeyDown("S") && !collisionCheck(3)) {
                moveY(speed);
            }
            if (Greenfoot.isKeyDown("A") && !collisionCheck(4)) {
                moveX(-speed);
            }
            if (Greenfoot.isKeyDown("D") && !collisionCheck(2)) {
                moveX(speed);
            }
            if (Greenfoot.isKeyDown("R") && !isReloading) {
                Greenfoot.playSound("AR-15 Reload.wav");
                isReloading = true;
                reloadFinishTime = System.currentTimeMillis()+reloadTime;
            }
            if(Greenfoot.isKeyDown("right")) {
                turn(5);
            }
            if(Greenfoot.isKeyDown("left")) {
                turn(-5);
            }
            if (System.currentTimeMillis() > reloadFinishTime && isReloading) {
                isReloading = false;
                currentAmmo = maxAmmo;
                updateAmmoCount();
            }
            if (mouseInfo!=null) {
                lookAtPosition (mouseInfo.getX(), mouseInfo.getY());
                updateCamPlayerOffset(mouseInfo.getX(), mouseInfo.getY());           
            }         
            if (Greenfoot.mousePressed(null)) {
                mouseDown = true;
                //shoot();
            } else if (Greenfoot.mouseClicked(null)){
                mouseDown = false;
            }
            if (mouseDown && weaponReady()) {
                if (!isReloading && currentAmmo > 0) {
                    shoot();
                }
            }
        } else {
            fadeAway();
        }
        updateLocation();
        updateWeaponControl();    
        updateAmmoCount();
        updateHealthBar();
        updateEnemyVisibility(100);
        List<Flag> flags = getWorld().getObjects(Flag.class);
        for (Flag flag : flags) {
            if (Math.sqrt(Math.pow(flag.getFieldX() - this.getFieldX(), 2) + Math.pow(flag.getFieldY() - this.getFieldY(), 2)) <= 100) {
                winLevel();
            }      
        }
    }

    public void lookAtPosition(int x, int y) {
        setRotation((int) Math.round(Math.toDegrees((Math.atan2(y + Camera.getCamY() - getFieldY(), x + Camera.getCamX() - getFieldX())))) + 90); 
        /* if (x - fieldX > 0 && y - fieldY > 0) {  // sector 1
        setRotation((int) Math.round(Math.atan2(fieldY - y, fieldX - x))); 
        }
         */
    }

    public void updateCamPlayerOffset(int x, int y) {
        Camera.setTargetCamX((int) Math.round(this.getFieldX() + ((x - getWorld().getWidth() / 2) * cameraBias) - (getWorld().getWidth() / 2)));
        Camera.setTargetCamY((int) Math.round (this.getFieldY() + ((y - getWorld().getHeight() / 2) * cameraBias) - (getWorld().getHeight() / 2)));
        //Camera.setCamPosition((int) Math.round(this.getFieldX() + (x * cameraBias) - (getWorld().getWidth() / 2)), (int) Math.round (this.getFieldY() + (y * cameraBias) - getWorld().getHeight()));
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void shoot() {
        Greenfoot.playSound("HK416.mp3");
        currentAmmo--;
        updateAmmoCount();
        int barrelXOffset = 16;
        int barrelYOffset = 75;
        double alpha = 0;
        double theta = Math.toDegrees(Math.atan(barrelYOffset / barrelXOffset));
        double h = Math.sqrt(Math.pow(barrelXOffset, 2) + Math.pow(barrelYOffset, 2));
        alpha = getRotation() - theta;
        int worldXOffset = (int) Math.round(Math.cos(Math.toRadians(alpha)) * h);
        int worldYOffset = (int) Math.round(Math.sin(Math.toRadians(alpha)) * h);

        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + ((spreadCurrent / 2) - Greenfoot.getRandomNumber(spreadCurrent)), 50, 40, 20);
        getWorld().addObject(bullet, 0, 0);
        spreadCurrent += spreadShotGain;
    }

    public void hit(int damage) {
        health -= damage;
        updateHealthBar();
    }

    public void updateHealthBar() {
        List<HealthBar> healthBars = getWorld().getObjects(HealthBar.class);
        for (HealthBar healthBar : healthBars) {
            healthBar.setHealth(health);
        }
    }

    public void updateAmmoCount() {
        List<WeaponUI> weaponUIs = getWorld().getObjects(WeaponUI.class);
        for (WeaponUI weaponUI : weaponUIs) {
            weaponUI.setAmmoCount(currentAmmo,maxAmmo,gunType);
        }
    }

    public int getColliderRadius() {
        return colliderRadius;
    }

    public void fadeAway() {
        getImage().setTransparency(getImage().getTransparency() - 30);
        if (getImage().getTransparency() <= 30) {
            //getWorld().removeObject(this);
            getWorld().getBackground().drawImage(new GreenfootImage("You Lost", 64, null, null), 550, 40);
            Greenfoot.delay(120);
            MainMenu menu = new MainMenu();
            Greenfoot.setWorld(menu);      
        }
    }

    private boolean collisionCheck(int direction) {
        // 1 - up
        // 2 - right
        // 3 - down
        // 4 - left
        List<BoxWall> boxWalls = getWorld().getObjects(BoxWall.class);
        if (direction == 1) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldY() - 5 < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldY() - 5 > boxWall.getFieldY() - boxWall.getColliderBounds() && getFieldX() < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldX() > (boxWall.getFieldX() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else if (direction == 2) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldX() + 5 > boxWall.getFieldX() - boxWall.getColliderBounds() && getFieldX() + 5 < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldY() < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldY() > (boxWall.getFieldY() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else if (direction == 3) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldY() + 5 > boxWall.getFieldY() - boxWall.getColliderBounds() && getFieldY() + 5 < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldX() < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldX() > (boxWall.getFieldX() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else if (direction == 4) {
            if (boxWalls != null) {
                for (BoxWall boxWall : boxWalls) {
                    if (getFieldX() - 5 < boxWall.getFieldX() + boxWall.getColliderBounds() && getFieldX() - 5 > boxWall.getFieldX() - boxWall.getColliderBounds() && getFieldY() < boxWall.getFieldY() + boxWall.getColliderBounds() && getFieldY() > (boxWall.getFieldY() - boxWall.getColliderBounds())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        else {
            return false;
        }
    }

    public boolean weaponReady() {
        if ((int)System.currentTimeMillis() >= nextShotAvailable) {
            nextShotAvailable = (int) System.currentTimeMillis() + fireRate;
            return true;
        } else {
            return false;
        }
    }

    public void updateWeaponControl() {
        if (spreadCurrent > spreadMax) spreadCurrent = spreadMax;
        spreadCurrent += (int) Math.round((spreadMin - spreadCurrent) / spreadRecover);
    }

    public void winLevel() {
        getWorld().getBackground().drawImage(new GreenfootImage("You win", 64, null, null), 550, 40);
        Greenfoot.delay(120);
        MainMenu menu = new MainMenu();
        Greenfoot.setWorld(menu);      
    }

    public void updateEnemyVisibility(int range) {
        double checkRotationRadians = 0;
        List<NPC> npcs = getWorld().getObjects(NPC.class);
        for (NPC npc : npcs) {
            boolean isVisible = true;
            checkRotationRadians = Math.atan2(npc.getFieldY() - this.getFieldY(), npc.getFieldX() - this.getFieldX()) + Math.toRadians(90);        
            int yDifference = npc.getFieldY() - this.getFieldY();
            int xDifference = npc.getFieldX() - this.getFieldX();
            int distanceToNPC = (int) Math.round(Math.sqrt(Math.pow(yDifference,2) + Math.pow(xDifference,2)));
            int checkDist = 0;
            int[] playerPosition;
            int rotationToNPC = (int) Math.round (Math.toDegrees(Math.atan2(npc.getFieldY() - this.getFieldY(), npc.getFieldX() - this.getFieldX())));           
            List<BoxWall> walls = getWorld().getObjects(BoxWall.class);
            if (walls != null) {                  
                for (BoxWall boxWall : walls) {
                    int[][] cornerPositions = new int[4][2];
                    cornerPositions[0][0] = boxWall.getFieldX() - boxWall.getColliderBounds(); //top left
                    cornerPositions[0][1] = boxWall.getFieldY() + boxWall.getColliderBounds();
                    cornerPositions[1][0] = boxWall.getFieldX() + boxWall.getColliderBounds(); //top right
                    cornerPositions[1][1] = boxWall.getFieldY() + boxWall.getColliderBounds();
                    cornerPositions[2][0] = boxWall.getFieldX() + boxWall.getColliderBounds(); // bottom right
                    cornerPositions[2][1] = boxWall.getFieldY() - boxWall.getColliderBounds();
                    cornerPositions[3][0] = boxWall.getFieldX() - boxWall.getColliderBounds(); // bottom left
                    cornerPositions[3][1] = boxWall.getFieldY() - boxWall.getColliderBounds();

                    int[] rotationCorners = new int[4];
                    rotationCorners[0] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[0][1] - this.getFieldY(), cornerPositions[0][0] - this.getFieldX())));
                    rotationCorners[1] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[1][1] - this.getFieldY(), cornerPositions[1][0] - this.getFieldX())));
                    rotationCorners[2] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[2][1] - this.getFieldY(), cornerPositions[2][0] - this.getFieldX())));
                    rotationCorners[3] = (int) Math.round (Math.toDegrees(Math.atan2(cornerPositions[3][1] - this.getFieldY(), cornerPositions[3][0] - this.getFieldX())));
                    //System.out.println("corner 1: " + rotationCorners[0] + " corner 2: " + rotationCorners[1] + " corner 3: " + rotationCorners[2] + " corner 4: " + rotationCorners[3]);
                    int largest = rotationCorners[0];
                    int smallest = rotationCorners[0];
                    int i;
                    for (i=1;i<rotationCorners.length; i++) {
                        if (rotationCorners[i] > largest) largest = rotationCorners[i];
                    }
                    for (i=1;i<rotationCorners.length; i++) {
                        if (rotationCorners[i] < smallest) smallest = rotationCorners[i];
                    }
                    double distanceToWall = Math.sqrt((Math.pow(boxWall.getFieldX() - this.getFieldX(), 2) + (Math.pow(boxWall.getFieldY() - this.getFieldY(), 2))));
                    //System.out.println("smallest: " + smallest + " | largest: " + largest + " | player: " + rotationToPlayer);
                    //TODO find shortest rotation instead of subtracting
                    if (distanceToWall < distanceToNPC) {
                        if (smallest < 0 && largest > 0) {
                            //System.out.println("smallest: " + smallest + " largest: " + largest + " " + "caseB");
                            //System.out.println("cross 0");
                            if (smallest < -90) {
                                if (rotationToNPC < smallest && rotationToNPC >= -180 || rotationToNPC > largest && rotationToNPC <= 180) {
                                    //System.out.println("caseA");
                                    isVisible = false;
                                }
                            } else if (smallest > -90) {
                                if (rotationToNPC > smallest && rotationToNPC <= 0 || rotationToNPC < largest && rotationToNPC >= 0) {
                                    //System.out.println("caseB");
                                    isVisible = false;
                                }
                            }
                        }
                        else if (rotationToNPC > smallest && rotationToNPC < largest && distanceToWall < distanceToNPC) {
                            //System.out.println("caseC");
                            isVisible = false; 
                        }
                    }
                }               
            }

            //System.out.println("myRotation " + getRotation() + " NPCrotation " + (rotationToNPC));
            if (rotationToNPC > 180 && getRotation() - 90 < 0) {
                if ((getRotation() - 90) + (360 - rotationToNPC) > (FOV/2)) {
                    //System.out.println("case1");
                    isVisible = false;
                }
            }
            else if (rotationToNPC < 0 && getRotation() - 90 > 180) {
                if ((rotationToNPC - 90) + (360 - getRotation()) > (FOV/2)) {
                    //System.out.println("case2");
                    isVisible = false;
                }
            }
            else if (getRotation() - 90 > rotationToNPC + (FOV/2) || getRotation() - 90 < rotationToNPC - (FOV/2)) {
                //System.out.println("case3");
                isVisible = false;
            }

            npc.setVisible(isVisible);
        }

    }
}
