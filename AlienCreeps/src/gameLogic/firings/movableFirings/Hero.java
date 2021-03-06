package gameLogic.firings.movableFirings;

import gameLogic.Engine;
import gameLogic.firings.Firings;
import gameLogic.firings.Gun;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Hero extends YourFighters implements Firings {
    int timeToreturn = 90;
    int timeLeftToReturn = 90;
    private Group root;
    private boolean deadStat = false;
    private int fireRate;
    public Image[] moveLeftPics = new Image[4];
    public Image[] moveRightPics = new Image[4];
    public Image[] moveForwardPics = new Image[4];
    public Image[] moveDownwardPics = new Image[4];
    public Image[] deadImages = new Image[4];
    public Image[] fireLeftImages = new Image[4];
    public Image[] fireForwardImages = new Image[4];
    public Image[] fireRightImages = new Image[4];
    public Image[] fireDownwardImages = new Image[4];
    private int walkState = 0;
    Gun gun = Gun.AK47;
    private AlienCreeps target;
    private ArrayList<AlienCreeps> targets = new ArrayList<>();
    int counterForFire = 0;
    private ArrayList<Soldier> allSoldiers = new ArrayList<>();
    ImageView imageView = new ImageView();

    public Hero(Group root) {//todo change variables
        super();
        this.fireRate = 5;
        setCoordinates(new int[]{1056, 872});
        this.root = root;
        Engine.getInstance().hero = this;
        try {
            moveLeftPics[0] = new Image(new FileInputStream(new File("images/hero images/MoveLeft1.png")));
            moveLeftPics[1] = new Image(new FileInputStream(new File("images/hero images/MoveLeft2.png")));
            moveLeftPics[2] = new Image(new FileInputStream(new File("images/hero images/MoveLeft3.png")));
            moveLeftPics[3] = new Image(new FileInputStream(new File("images/hero images/MoveLeft4.png")));

            moveRightPics[0] = new Image(new FileInputStream(new File("images/hero images/MoveRight1.png")));
            moveRightPics[1] = new Image(new FileInputStream(new File("images/hero images/MoveRight2.png")));
            moveRightPics[2] = new Image(new FileInputStream(new File("images/hero images/MoveRight3.png")));
            moveRightPics[3] = new Image(new FileInputStream(new File("images/hero images/MoveRight4.png")));

            moveForwardPics[0] = new Image(new FileInputStream(new File("images/hero images/MoveForward1.png")));
            moveForwardPics[1] = new Image(new FileInputStream(new File("images/hero images/MoveForward2.png")));
            moveForwardPics[2] = new Image(new FileInputStream(new File("images/hero images/MoveForward3.png")));
            moveForwardPics[3] = new Image(new FileInputStream(new File("images/hero images/MoveForward4.png")));

            moveDownwardPics[0] = new Image(new FileInputStream(new File("images/hero images/MoveDownward1.png")));
            moveDownwardPics[1] = new Image(new FileInputStream(new File("images/hero images/MoveDownward2.png")));
            moveDownwardPics[2] = new Image(new FileInputStream(new File("images/hero images/MoveDownward3.png")));
            moveDownwardPics[3] = new Image(new FileInputStream(new File("images/hero images/MoveDownward4.png")));

            for (int i = 0; i < 4; i++) {
                fireLeftImages[i] = new Image(new FileInputStream("images/hero images/FireLeft" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 4; i++) {
                fireRightImages[i] = new Image(new FileInputStream("images/hero images/FireRight" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 4; i++) {
                fireForwardImages[i] = new Image(new FileInputStream("images/hero images/FireForward" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 4; i++) {
                fireDownwardImages[i] = new Image(new FileInputStream("images/hero images/FireDownward" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 4; i++) {
                deadImages[i] = new Image(new FileInputStream("images/hero images/Die" + (i + 1) + ".png"));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MovableFirings.getAllMovableFirings().add(this);
        drawHero(moveLeftPics, walkState);
    }

    public void moveHeroForward(int speed) {
        this.setCoordinates(new int[]{getCoordinates()[0], getCoordinates()[1] - speed});
        walkState++;
        if (walkState == 4)
            walkState = 0;
        drawHero(moveForwardPics, walkState);
    }

    public void moveHeroDownward(int speed) {
        this.setCoordinates(new int[]{getCoordinates()[0], getCoordinates()[1] + speed});
        walkState++;
        if (walkState == 4)
            walkState = 0;

        drawHero(moveDownwardPics, walkState);
    }

    public void moveHeroLeft(int speed) {
        this.setCoordinates(new int[]{getCoordinates()[0] - speed, getCoordinates()[1]});
        walkState++;
        if (walkState == 4)
            walkState = 0;
        drawHero(moveLeftPics, walkState);

    }

    public void moveHeroRight(int speed) {
        this.setCoordinates(new int[]{getCoordinates()[0] + speed, getCoordinates()[1]});
        walkState++;
        if (walkState == 4)
            walkState = 0;
        drawHero(moveRightPics, walkState);

    }

    public void drawHero(Image[] heroPics, int stateOfWalk) {

        try {
            Image hero = heroPics[stateOfWalk];
            imageView.relocate(getCoordinates()[0], getCoordinates()[1]);
            root.getChildren().remove(imageView);
            imageView.setImage(hero);
            root.getChildren().add(imageView);
            for (int i = 0; i < 4; i++) {
                if (hero == moveLeftPics[i]) {
                }
            }
            for (int i = 0; i < 4; i++) {
                if (hero == moveRightPics[i]) {
                }
            }
            for (int i = 0; i < 4; i++) {
                if (hero == moveForwardPics[i]) {
                }
            }
            for (int i = 0; i < 4; i++) {
                if (hero == moveDownwardPics[i]) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void weaken(AlienCreeps alienCreep) {
        if (alienCreep.getAlienCreepTypes().getType().equals("air")) {
            alienCreep.setEnergy(alienCreep.getEnergy() - gun.getPowerOnAirUnits());
        } else {
            alienCreep.setEnergy(alienCreep.getEnergy() - gun.getPowerOnGroundUnits());
        }
    }

    @Override
    public void freeze() {

    }


    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }


    public Gun getGun() {
        return gun;
    }

    public ArrayList<Soldier> getAllSoldiers() {
        return allSoldiers;
    }

    public int getCounterForFire() {
        return counterForFire;
    }

    public void setCounterForFire(int counterForFire) {
        this.counterForFire = counterForFire;
    }


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public AlienCreeps getTarget() {
        return target;
    }

    public void setTarget(AlienCreeps target) {
        this.target = target;
    }

    public boolean setGun(Gun gun) {
        if (Engine.getInstance().getPlayer().getGold() - gun.getPrice() < 0) {
            return false;
        }
        Engine.getInstance().getPlayer().setGold(Engine.getInstance().getPlayer().getGold() - gun.getPrice());
        this.gun = gun;
        return true;
    }

    public int getTimeToreturn() {
        return timeToreturn;
    }

    public void setTimeToreturn(int timeToreturn) {
        this.timeToreturn = timeToreturn;
    }

    public int getTimeLeftToReturn() {
        return timeLeftToReturn;
    }

    public void setTimeLeftToReturn(int timeLeftToReturn) {
        this.timeLeftToReturn = timeLeftToReturn;
    }

    public boolean isDeadStat() {
        return deadStat;
    }

    @Override
    public void setDeadStat(boolean deadStat) {
        this.deadStat = deadStat;
    }
}
