import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    public NPC(int x, int y) {
        super(x,y);
        shootSpread = 15;
        health = 100;
        GreenfootImage image = getImage();
        image.scale(240,200);
        speed = 1;
        fireRate = 150;
        difficulty = 2;
        direction = 4;
        FOV = 120;
        turnSpeed = 5;
        difficulty = GameSettings.getDifficulty();
        if (difficulty == 1) {
            reactionTime = 1000;
        }
        if (difficulty == 2) {
            reactionTime = 400;
        }
    }

    public void act() 
    {
        currentTime = System.currentTimeMillis();        
        updateLocation();
        switch(difficulty) {
            case 1:
            runDifficultyEasy();
            break;
            case 2:
            runDifficultyMedium();
            break;
        }
    }

    public void runDifficultyEasy() {
        if (health >= 0) {
            if (hasSeenPlayer) {
                aimAtPlayer();
            } else {
                aimAtRotation(90);
            }
            if (currentTime > nextShotDue) {
                if (findTarget(300)) shoot();
                nextShotDue = currentTime + fireRate;
            }
        } else {
            fadeAway();
        }
    }

    public void runDifficultyMedium() {

        if (health >= 0) {
            if (hasSeenPlayer) {
                aimAtPlayer();
            } else {
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
            if (currentTime > nextShotDue) {
                if (findTarget(300)) {                    
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
            // System.out.println (getRotation());
            Player player = players.get(0);
            int targetRotation = (int) Math.round (Math.toDegrees(Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX()))) + 90;
            rotationForce = (targetRotation - getRotation()) / turnSpeed;

            if (getRotation() > 270 && targetRotation < 90) {
                rotationForce = (targetRotation + 360 - getRotation()) / turnSpeed;
            }
            if (getRotation() < 90 && targetRotation > 270) {
                rotationForce = -(getRotation() + 360 - targetRotation) / turnSpeed;
            }

            setRotation(getRotation() + rotationForce);
        }
    }

    public void aimAtRotation(int rotation) {
        int rotationForce;
        int targetRotation = rotation - 90;
        rotationForce = (targetRotation - getRotation()) / turnSpeed;
        if (getRotation() > 270 && targetRotation < 90) {
            rotationForce = (targetRotation + 360 - getRotation()) / turnSpeed;
        }
        if (getRotation() < 90 && targetRotation > 270) {
            rotationForce = -(getRotation() + 360 - targetRotation) / turnSpeed;
        }
        setRotation(getRotation() + rotationForce);

    }

    public void shoot() {
        Greenfoot.playSound("HK416.mp3");
        int barrelXOffset = 16;
        int barrelYOffset = 75;
        double alpha = 0;
        double theta = Math.toDegrees(Math.atan(barrelYOffset / barrelXOffset));
        double h = Math.sqrt(Math.pow(barrelXOffset, 2) + Math.pow(barrelYOffset, 2));
        alpha = getRotation() - theta;
        int worldXOffset = (int) Math.round(Math.cos(Math.toRadians(alpha)) * h);
        int worldYOffset = (int) Math.round(Math.sin(Math.toRadians(alpha)) * h);

        Bullet bullet = new Bullet(getFieldX() + worldXOffset, getFieldY() + worldYOffset, getRotation() - 90 + shootSpread / 2 - Greenfoot.getRandomNumber(shootSpread) , 50, 40, 15);
        getWorld().addObject(bullet, 0, 0);
    }

    public boolean findTarget(int range) {
        double checkRotationRadians = 0;
        List<Player> players = getWorld().getObjects(Player.class);
        Player player = null;
        if (players.size() > 0) {
            player = players.get(0);
            checkRotationRadians = Math.atan2(player.getFieldY() - this.getFieldY(), player.getFieldX() - this.getFieldX()) + Math.toRadians(90);        
            int yDifference = player.getFieldY() - this.getFieldY();
            int xDifference = player.getFieldX() - this.getFieldX();
            int distanceToPlayer = (int) Math.round(Math.sqrt(Math.pow(yDifference,2) + Math.pow(xDifference,2)));
            int checkDist = 0;
            int[] playerPosition;
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

                    if (distanceToWall > distanceToPlayer) {
                        System.out.println("B");
                        if (smallest < 0 && largest > 0) {
                            //System.out.println("cross 0");
                            if (smallest < -90) {
                                if (rotationToPlayer < smallest && rotationToPlayer > -180 || rotationToPlayer > largest && rotationToPlayer <= 180) {
                                    hasSeenPlayer = false;
                                    return false;
                                }
                            } else if (smallest > -90) {
                                if (rotationToPlayer > smallest && rotationToPlayer < 0 || rotationToPlayer < largest && rotationToPlayer >= 0) {
                                    hasSeenPlayer = false;
                                    return false;
                                }
                            }
                        }
                        else if (rotationToPlayer > smallest && rotationToPlayer < largest) {
                            hasSeenPlayer = false;
                            return false;   
                        }

                        if (rotationToPlayer > 180 && getRotation() - 90 < 0) {
                            if ((getRotation() - 90) + (360 - rotationToPlayer) > (FOV/2)) {
                                hasSeenPlayer = false;
                                return false; 
                            }
                        }
                        else if (rotationToPlayer < 0 && getRotation() - 90 > 180) {
                            if ((rotationToPlayer - 90) + (360 - getRotation()) > (FOV/2)) {
                                hasSeenPlayer = false;
                                return false;
                            }
                        }
                        else if (getRotation() - 90 > rotationToPlayer + (FOV/2) || getRotation() - 90 < rotationToPlayer - (FOV/2)) {
                            hasSeenPlayer = false;
                            return false;
                        }
                        if (rotationToPlayer > 180 && getRotation() - 90 < 0) {
                            if ((getRotation() - 90) + (360 - rotationToPlayer) > (FOV/2)) {
                                hasSeenPlayer = false;
                                return false;
                            }
                        }
                        else if (rotationToPlayer < 0 && getRotation() - 90 > 180) {
                            if ((rotationToPlayer - 90) + (360 - getRotation()) > (FOV/2)) {
                                hasSeenPlayer = false;
                                return false;
                            }
                        }
                        else if (getRotation() - 90 > rotationToPlayer + (FOV/2) || getRotation() - 90 < rotationToPlayer - (FOV/2)) {
                            hasSeenPlayer = false;
                            return false;
                        }
                        else if (!hasSeenPlayer) {
                            aimAtPlayer();
                            hasSeenPlayer = true;
                            reactionDue = currentTime+reactionTime;
                            return false;
                        }
                        else if (hasSeenPlayer && currentTime > reactionDue) {
                            aimAtPlayer();
                            return true;
                        }
                        else {
                            return false;
                        }
                    } else {
                        System.out.println("A");
                        hasSeenPlayer = false;
                        return false;
                    }
                }
            }

            if (!hasSeenPlayer) {
                aimAtPlayer();
                hasSeenPlayer = true;
                reactionDue = currentTime+reactionTime;
                return false;
            }
            else if (hasSeenPlayer && currentTime > reactionDue) {
                aimAtPlayer();
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
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

    public void setVisible(boolean visible) {
        if (visible && health > 0) {
            getImage().setTransparency(255); 
        } else if (health > 0) {
            getImage().setTransparency(getImage().getTransparency() - ((int) Math.ceil(getImage().getTransparency())) / 5);
        }
    }
}
