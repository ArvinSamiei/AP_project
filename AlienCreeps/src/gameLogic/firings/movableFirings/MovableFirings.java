package gameLogic.firings.movableFirings;

import gameLogic.firings.Firings;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public abstract class MovableFirings implements Firings {
    //private boolean movableMoved = false;
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

    public int getSpeedModified() {
        return speedModified;
    }

    public void setSpeedModified(int speedModified) {
        this.speedModified = speedModified;
    }


    public boolean isDead() {
        if (this.energy <= 0) {
            System.out.println("dead");
            return true;
        }

        return false;
    }

    /**
     * tabe e payin faghat vase vaghtie ke yedoone target dashte bashe chon momkene do ta alien ham fasele
     * bahash bashan vali in faghat yekishoono mizane .
     * اگه همه ی همسايه هاش رو بزنه که اگه همه بميرن که ديگه چيزی تو شعاعش نيست .
     */


    // alaki neveshtam ke too soldier gir nade ke constructor benevis
    public MovableFirings() {

    }

    public int getSpeedUnmodified() {
        return speedUnmodified;
    }

    public void setSpeedUnmodified(int speedUnmodified) {
        this.speedUnmodified = speedUnmodified;
    }


    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
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

//    public boolean isMovableMoved() {
//        return movableMoved;
//    }
//
//    public void setMovableMoved(boolean movableMoved) {
//        this.movableMoved = movableMoved;
//    }


}
