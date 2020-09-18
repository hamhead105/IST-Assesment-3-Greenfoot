import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class NPC here.
 * 
 * @author Peter Jung
 * @version 1
 */
public class NPC extends GameObject
{
    private int speed;
    private int colliderRadius = 30;
    private int health;

    private long currentTime = System.currentTimeMillis();
    private long nextShotDue;

    private int shootSpread;
    private int fireRate;
    private int difficulty;

    private int direction;
    private int FOV;

    private int reactionTime;
    private long reactionDue;
    private int turnSpeed;
    private boolean hasSeenPlayer;
    private long relaxTime;

    private int damage;
    private boolean alerted;
    private int checkFrame = 0;

    public NPC(int x, int y) {
        super(x,y);
        health = 100;
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 1;

        direction = 4;
        FOV = 120;
        turnSpeed = 5;
        difficulty = GameSettings.getDifficulty();
        switch (difficulty) {
            case 1:
            difficulty = 1;
            reactionTime = 1000;
            damage = 10;
            fireRate = 150;
            shootSpread = 35;
            break;
            case 2:
            difficulty = 2;
            reactionTime = 500;
            damage = 25;
            fireRate = 150;
            shootSpread = 27;
            break;
            case 3:
            difficulty = 3;
            reactionTime = 100;
            damage = 35;
            fireRate = 20;
            shootSpread = 15;
            break;

        }
    }

    public void act() 
    {
        getWorld().setPaintOrder(NPC.class);
        currentTime = System.currentTimeMillis();        
        updateLocation();
        switch(difficulty) {
            case 1:
            runDifficultyEasy();
            break;
            case 2:
            runDifficultyMedium();
            break;
            case 3:
            runDifficultyHard();
            break;
        }
        checkFrame++;
        if (checkFrame == 10) checkFrame = 1;
    }

    public void runDifficultyEasy() {
        if (health >= 0) {
            if (hasSeenPlayer || alerted) {
                aimAtPlayer();
                if (currentTime > relaxTime) {
                    alerted = false;
                }
            } else if (currentTime > relaxTime) {
                aimAtRotation(direction*90);
            }
            if (Greenfoot.getRandomNumber(50) == 1) {
                direction = Greenfoot.getRandomNumber(4) + 1;
            }
            //System.out.println(direction);
            //aimAtPlayer();
            if (currentTime > nextShotDue && checkFrame == 1) {
                if (findTarget(1200)) {                    
                    shoot();
                }
                nextShotDue = currentTime + fireRate;

            }
        } else {
            fadeAway();
        }
    }

    public void runDifficultyMedium() {
        if (health >= 0) {
            if (hasSeenPlayer || alerted) {
                aimAtPlayer();
                if (currentTime > relaxTime) {
                    alerted = false;
                }
            } else if (currentTime > relaxTime) {
                aimAtRotation(direction*90);
            }
            if (Greenfoot.getRandomNumber(50) == 1) {
                direction = Greenfoot.getRandomNumber(4) + 1;
            }
            //System.out.println(direction);
            if (!collisionCheck(direction)) {
                if (direction == 1) {
                    moveY(-speed);
                }
                if (direction == 2) {
                    moveX(speed);
                }
                if (direction == 3) {
                    moveY(speed);
                }
                if (direction == 4) {
                    moveX(-speed);
                }
            } else {
                direction = Greenfoot.getRandomNumber(4) + 1;
            }
            //aimAtPlayer();
            if (currentTime > nextShotDue && checkFrame == 1) {
                if (findTarget(1200)) {                    
                    shoot();
                }
                nextShotDue = currentTime + fireRate;

            }
        } else {
            fadeAway();
        }
    }

    public void runDifficultyHard() {
        if (health >= 0) {
            if (hasSeenPlayer || alerted) {
                aimAtPlayer();
                if (currentTime > relaxTime) {
                    alerted = false;
                }
            } else if (currentTime > relaxTime) {
                aimAtRotation(direction*90);
            }
            if (Greenfoot.getRandomNumber(50) == 1) {
                direction = Greenfoot.getRandomNumber(4) + 1;
            }
            //System.out.println(direction);
            if (!collisionCheck(direction)) {
                if (direction == 1) {
                    moveY(-speed);
                }
                if (direction == 2) {
                    moveX(speed);
                }
                if (direction == 3) {
                    moveY(speed);
                }
                if (direction == 4) {
                    moveX(-speed);
                }
            } else {
                direction = Greenfoot.getRandomNumber(4) + 1;
            }
            //aimAtPlayer();
            if (currentTime > nextShotDue && checkFrame == 1) {
                if (findTarget(1200)) {                    
                    shoot();
                }
                nextShotDue = currentTime + fireRate;

            }
        } else {
            fadeAway();
        }
    }

    public void hit(int damage) {
        health -= damage;
        getImage().setColor(Color.RED);
        //getImage().fill();
    }

    public void fadeAway() {
        if (getImage().getTransparency() <= 30) {
            GameSettings.setCurrentScore(GameSettings.getCurrentScore() + 200*difficulty);
            getWorld().removeObject(this);
        } else {
            getImage().setTransparency(getImage().getTransparency() - 30);
        }
    }

    public int getColliderRadius() {
        return colliderRadius;
    }

    public void aimAtPlayer() {
        List<Player> players = getWorld().getObjects(Player.class);
        if (players.size() > 0) {
            int rotationForce;
            Player player = players.get(0);
            int targetRotation = (int) Math.round (Math.toDegrees(Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX())) + 180);             
            int currentRotation = getRotation() + 90;
            if (currentRotation > 360) currentRotation = currentRotation - 360;
            if (currentRotation > 360) {
                currentRotation = currentRotation - 360;
            }
            if (currentRotation >= 270 && targetRotation <= 90) {
                rotationForce = (targetRotation + 360 - currentRotation) / turnSpeed;
            }
            else if (currentRotation <= 90 && targetRotation >= 270) {
                rotationForce = (-currentRotation + -(360 - targetRotation)) / turnSpeed;
            } else {
                rotationForce = (targetRotation - currentRotation) / turnSpeed;
            }

            //System.out.println (currentRotation + " " + targetRotation);
            setRotation(getRotation() + rotationForce);
        }
    }

    public void aimAtRotation(int rotation) {
        int rotationForce;
        int targetRotation = rotation;   
        int currentRotation = getRotation() + 90;
        if (currentRotation > 360) {
            currentRotation = currentRotation - 360;
        }

        if (currentRotation > 270 && targetRotation < 90) {
            rotationForce = (targetRotation + 360 - currentRotation) / turnSpeed;
        }
        if (currentRotation < 90 && targetRotation > 270) {
            rotationForce = (-currentRotation + -(360 - targetRotation)) / turnSpeed;
        } else {
            rotationForce = (targetRotation - currentRotation) / turnSpeed;
        }

        //System.out.println (currentRotation + " " + targetRotation);
        setRotation(getRotation() + rotationForce);

    }

    public void shoot() {
        Greenfoot.playSound("HK416.mp3");
        int barrelXOffset = 16;
        int barrelYOffset = 55;
        double alpha = 0;
        double theta = Math.toDegrees(Math.atan(barrelYOffset / barrelXOffset));
        double h = Math.sqrt(Math.pow(barrelXOffset, 2) + Math.pow(barrelYOffset, 2));
        alpha = getRotation() - theta;
        int worldXOffset = (int) Math.round(Math.cos(Math.toRadians(alpha)) * h);
        int worldYOffset = (int) Math.round(Math.sin(Math.toRadians(alpha)) * h);

        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + ((shootSpread / 2) - Greenfoot.getRandomNumber(shootSpread)), 50, 40, damage);
        getWorld().addObject(bullet, -100, -100);
        Muzzleflash muzzleFlash = new Muzzleflash(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + (int) (5 - Greenfoot.getRandomNumber(5)));
        getWorld().addObject(muzzleFlash, -100, -100);
    }

    public boolean findTarget(int range) {
        double checkRotationRadians = 0;
        boolean isVisible = true;
        hasSeenPlayer = true;
        List<Player> players = getWorld().getObjects(Player.class);
        Player player = null;
        if (players.size() > 0) {
            player = players.get(0);
            checkRotationRadians = Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX()) + Math.toRadians(90);        
            int yDifference = player.getFieldY() - this.getFieldY();
            int xDifference = player.getFieldX() - this.getFieldX();
            int distanceToPlayer = (int) Math.round(Math.sqrt(Math.pow(yDifference,2) + Math.pow(xDifference,2)));
            if (distanceToPlayer < range) {
                int rotationToPlayer = (int) Math.round (Math.toDegrees(Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX())));           
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

                        if (distanceToWall < distanceToPlayer) {
                            //System.out.println("B");
                            if (smallest < 0 && largest > 0) {
                                //System.out.println("smallest: " + smallest + " largest: " + largest + " " + "caseB");
                                //System.out.println("cross 0");
                                if (smallest < -90) {
                                    if (rotationToPlayer < smallest && rotationToPlayer >= -180 || rotationToPlayer > largest && rotationToPlayer <= 180) {
                                        //System.out.println("caseA");
                                        hasSeenPlayer = false;
                                        isVisible = false;
                                    }
                                } else if (smallest > -90) {
                                    if (rotationToPlayer > smallest && rotationToPlayer <= 0 || rotationToPlayer < largest && rotationToPlayer >= 0) {
                                        //System.out.println("caseB");
                                        hasSeenPlayer = false;
                                        isVisible = false;
                                    }
                                }
                            }
                            else if (rotationToPlayer > smallest && rotationToPlayer < largest && distanceToWall < distanceToPlayer) {
                                //System.out.println("caseC");
                                hasSeenPlayer = false;
                                isVisible = false; 
                            }
                        }
                    }               

                    //System.out.println("myRotation " + getRotation() + " NPCrotation " + (rotationToPlayer));
                    if (rotationToPlayer > 180 && getRotation() - 90 < 0) {
                        if ((getRotation() - 90) + (360 - rotationToPlayer) > (FOV/2)) {
                            //System.out.println("case1");
                            hasSeenPlayer = false;
                            isVisible = false;
                        }
                    }
                    else if (rotationToPlayer < 0 && getRotation() - 90 > 180) {
                        if ((rotationToPlayer - 90) + (360 - getRotation()) > (FOV/2)) {
                            //System.out.println("case2");
                            hasSeenPlayer = false;
                            isVisible = false;
                        }
                    }
                    else if (getRotation() - 90 > rotationToPlayer + (FOV/2) || getRotation() - 90 < rotationToPlayer - (FOV/2)) {
                        //System.out.println("case3");
                        hasSeenPlayer = false;
                        isVisible = false;
                    }
                    if (hasSeenPlayer) {
                        if (currentTime < reactionDue) {
                            isVisible = false;
                        }
                        relaxTime = currentTime+4000;
                    } else {                   
                        reactionDue = currentTime+reactionTime;
                    }
                    return isVisible;
                } else {
                    return false;
                }
            }
        }
        return false;
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

    public void setVisible(boolean visible) {
        if (visible && health > 0) {
            getImage().setTransparency(255); 
        } else if (health > 0) {
            getImage().setTransparency(getImage().getTransparency() - ((int) Math.ceil(getImage().getTransparency())) / 5);
        }
    }

    public void addRelaxTime(int time) {
        relaxTime = currentTime + time;
    }

    public void alert() {
        alerted = true;
        relaxTime = currentTime + 1000;
    }
}
