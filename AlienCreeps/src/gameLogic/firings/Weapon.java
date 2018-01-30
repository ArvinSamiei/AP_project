package gameLogic.firings;

import gameLogic.Engine;
import gameLogic.firings.movableFirings.MovableFirings;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public enum Weapon implements Firings {
    AntiAircraft(0, 12, 30, 15, 20, 180, 1, false),
    Freezer(5, 3, 20, 5, 60, 170, 1, true),
    Laser(10, 7, 20, 7, 40, 150, 1, false),
    MachineGun(10, 5, 20, 10, 0, 100, 1, false),
    Rocket(20, 10, 40, 3, 0, 180, 1, true);

    private int[] coordinate;
    private int powerOnGroundUnits;
    private int powerOnAirUnits;
    private double range;
    private int fireRate;
    private int speedReduction;
    private int level;
    private boolean isPogromist;

    public int price;

    Weapon(int powerOnGroundUnits, int powerOnAirUnits, double range, int fireRate, int speedReduction, int price, int level, boolean isPogromist)  {
        setPrice(price);
        setPowerOnGroundUnits(powerOnGroundUnits);
        setPowerOnAirUnits(powerOnAirUnits);
        setRange(range);
        setFireRate(fireRate);
        setSpeedReduction(speedReduction);
        setLevel(level);
        setPogromist(isPogromist);
    }

    private ArrayList<AlienCreeps> updateTargets() {
        return findSomeTarget();
    }

    protected ArrayList<AlienCreeps> manageTargets(ArrayList<AlienCreeps> targets) {
        for (AlienCreeps alienCreep : targets) {
            if (alienCreep.isDead()) {
                Engine.getAlienCreepsKilledByWeapons().add(alienCreep);
                AlienCreeps.getAllAlienCreeps().remove(alienCreep);
                targets.remove(alienCreep);
            }
        }
        if (targets.size() == 0 && isPogromist == false) {
            targets = updateTargets();
        }
        return targets;
    }

    public static double calculateDistance(int[] coordinate1, int[] coordinate2) {
        int differenceToX = Math.abs(coordinate1[0] - coordinate2[0]);
        int differenceToY = Math.abs(coordinate1[1] - coordinate2[1]);
        double distance = Math.sqrt(Math.pow(differenceToX, 2) + Math.pow(differenceToY, 2));
        return distance;
    }

    public ArrayList<AlienCreeps> findOneTarget() {
        double distance;
        double minDistance = 0;
        ArrayList<AlienCreeps> targets = new ArrayList<>();
        for (AlienCreeps alienCreep : AlienCreeps.getAllAlienCreeps()) {
            distance = calculateDistance(this.coordinate, alienCreep.getCoordinates());
            if (distance < range) {

                if (alienCreep.getAlienCreepTypes().getType().equals("air") && powerOnAirUnits == 0) {
                    continue;
                }
                if (alienCreep.getAlienCreepTypes().getType().equals("ground") && powerOnGroundUnits == 0) {
                    continue;
                }//TODO refactor

                if (distance < minDistance) { // find mindistance TODO refactor
                    minDistance = distance;
                    targets.set(0, alienCreep);
                }
            }
        }
        return targets;
    }


    public ArrayList<AlienCreeps> findSomeTarget() { // TODO cpy paste
        double distance;
        ArrayList<AlienCreeps> targets = new ArrayList<>();
        for (AlienCreeps alienCreep : AlienCreeps.getAllAlienCreeps()) {
            distance = calculateDistance(this.coordinate, alienCreep.getCoordinates());
            if (distance < range) {
                /*if (!(alienCreep.getType().equals(((Hero)this).getGun()))){
                    continue;
                }*/
                if (alienCreep.getAlienCreepTypes().getType().equals("air") && powerOnAirUnits == 0) {
                    continue;
                }
                if (alienCreep.getAlienCreepTypes().getType().equals("ground") && powerOnGroundUnits == 0) {
                    continue;
                }
                //alienCreep.energy -= this.power * fireRate;
                targets.add(alienCreep);
            }
        }
        return targets;
    }

    private ArrayList<AlienCreeps> findWeaponTargets() {//TODO copy paste
        if (isPogromist == false) {
            return this.findOneTarget();
        } else {
            return this.findSomeTarget();
        }
    }

    public void weaken() { // TODO copy paste
        ArrayList<AlienCreeps> targets = findWeaponTargets();
            for (AlienCreeps target : targets) {
                if (target.getAlienCreepTypes().getType().equals("air")) {
                    target.setEnergy(target.getEnergy() - powerOnAirUnits);
                } else {
                    target.setEnergy(target.getEnergy() - powerOnGroundUnits);
                }

                targets = manageTargets(targets);
            }
        
    } // TODO Copy paste bood

    public void freeze() {
        ArrayList<AlienCreeps> targets = findWeaponTargets();

            for (MovableFirings target : targets) {
                if (target.getSpeedModified() != target.getSpeedUnmodified()) {
                    continue;//TODO
                }

                target.setSpeedModified(((100 - speedReduction) / 100) * target.getSpeedUnmodified());
            }
        
    }


    public void shoot() {
        weaken();
        freeze();
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getPrice() {
        return price;
    }
}