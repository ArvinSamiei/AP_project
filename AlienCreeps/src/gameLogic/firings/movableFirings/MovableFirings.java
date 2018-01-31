package gameLogic.firings.movableFirings;

import gameLogic.firings.Firings;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public abstract class MovableFirings implements Firings {
    private static ArrayList<MovableFirings> allMovableFirings = new ArrayList<>();
    private int speedUnmodified;
    private int speedModified;
    private int energy;
    private int power;
    private int fireRate;
    private double fireRange;
    private boolean isPogromist;


    public MovableFirings(int speedUnmodified, int speedModified, int energy, int power, int fireRate, double fireRange, boolean isPogromist) {
        this.speedUnmodified = speedUnmodified;
        this.speedModified = speedModified;
        this.energy = energy;
        this.power = power;
        this.fireRate = fireRate;
        this.fireRange = fireRange;
        this.isPogromist = isPogromist;
    }

    public void setPogromist(boolean pogromist) {
        isPogromist = pogromist;
    }

    public boolean isPogromist() {
        return isPogromist;
    }




    public boolean isDead() {
        if (this.energy <= 0) {
            return true;
        }

        return false;
    }



    // alaki neveshtam ke too soldier gir nade ke constructor benevis
    public MovableFirings() {

    }


    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        if (this instanceof Soldier){
            System.out.println(energy);
        }
        if (energy <= 0) {
            if (this instanceof Soldier){
                System.out.println(energy);
            }
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public double getFireRange() {
        return fireRange;
    }

    public void setFireRange(double fireRange) {
        this.fireRange = fireRange;
    }


    public static ArrayList<MovableFirings> getAllMovableFirings() {
        return allMovableFirings;
    }




}
