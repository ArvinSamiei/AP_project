package gameLogic.firings.movableFirings;

import gameLogic.Barrack;
import gameLogic.Engine;
import gameLogic.firings.Firings;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Soldier extends YourFighters implements Firings {
    int counterForFire = 0;
    ImageView imageView;
    AlienCreeps target;

    public Soldier() {
        super(true);
        Engine.getInstance().hero.getAllSoldiers().add(this);
        MovableFirings.getAllMovableFirings().add(this);
        try {
            imageView = new ImageView(new Image(new FileInputStream("images/soldier images/MoveRight1.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isDead(Hero hero, Barrack barrack) {
        boolean dead = super.isDead();
        if (dead == true) {
            hero.getAllSoldiers().remove(this);
            Barrack.getInstance().getMinsToCreateASoldier().add(0);
        }
        return dead;
    }

    @Override
    public void freeze() {

    }

    public void weaken(AlienCreeps alienCreep) {
            alienCreep.setEnergy(alienCreep.getEnergy() - this.getPower());
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
}