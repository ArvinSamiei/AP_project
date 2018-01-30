package gameLogic.firings;

import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public class WeaponsObject {
    Weapon weapon;
    private int[] coordinate;
    private int powerOnGroundUnits;
    private int powerOnAirUnits;
    private double range;
    private int fireRate;
    private int speedReduction;
    private int level;
    private boolean isPogromist;
    private int counterForFire = 0;
    public int price;
    private boolean fireStat = false;
    private ArrayList<AlienCreeps> targets = new ArrayList<>();

    public WeaponsObject(Weapon weapon) {
        this.weapon = weapon;
        setPrice(weapon.getPrice());
        setPowerOnGroundUnits(weapon.getPowerOnGroundUnits());
        setPowerOnAirUnits(weapon.getPowerOnAirUnits());
        setRange(weapon.getRange());
        setFireRate(weapon.getFireRate());
        setSpeedReduction(weapon.getSpeedReduction());
        setLevel(weapon.getLevel());
        setPogromist(weapon.isPogromist());
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public int getPowerOnGroundUnits() {
        return powerOnGroundUnits;
    }

    public void setPowerOnGroundUnits(int powerOnGroundUnits) {
        this.powerOnGroundUnits = powerOnGroundUnits;
    }

    public int getPowerOnAirUnits() {
        return powerOnAirUnits;
    }

    public void setPowerOnAirUnits(int powerOnAirUnits) {
        this.powerOnAirUnits = powerOnAirUnits;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public int getSpeedReduction() {
        return speedReduction;
    }

    public void setSpeedReduction(int speedReduction) {
        this.speedReduction = speedReduction;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isPogromist() {
        return isPogromist;
    }

    public void setPogromist(boolean pogromist) {
        isPogromist = pogromist;
    }

    public int getCounterForFire() {
        return counterForFire;
    }

    public void setCounterForFire(int counterForFire) {
        this.counterForFire = counterForFire;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFireStat() {
        return fireStat;
    }

    public void setFireStat(boolean fireStat) {
        this.fireStat = fireStat;
    }

    public ArrayList<AlienCreeps> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<AlienCreeps> targets) {
        this.targets = targets;
    }

    public void shoot(AlienCreeps target) {
        weaken(target);
        freeze(target);
    }

    public void weaken(AlienCreeps target) { // TODO copy paste
        if (target.getAlienCreepTypes().getType().equals("air")) {
            target.setEnergy(target.getEnergy() - powerOnAirUnits);
        } else {
            target.setEnergy(target.getEnergy() - powerOnGroundUnits);
        }
    }

    public void freeze(AlienCreeps target) {

    }
}
