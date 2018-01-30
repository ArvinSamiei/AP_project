package gameLogic.firings;

import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public class Tesla implements Firings {
    private int range;
    private int cooldown;
    private int remainingCooldown;
    private int chargesLeft;
    private static boolean possibleOrNot = false;
    private static Tesla tesla = new Tesla();

    private Tesla() {

    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getChargesLeft() {
        return chargesLeft;
    }

    public void setChargesLeft(int chargesLeft) {
        this.chargesLeft = chargesLeft;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }


    public void fire(int middleX, int middleY) {
        if (getChargesLeft() > 0 && getRemainingCooldown() == 0) {
            ArrayList<AlienCreeps> targets = new ArrayList<>();
            targets = findTarget(middleX, middleY);
            kill(targets);
            setChargesLeft(getChargesLeft() - 1);
            setRemainingCooldown(getCooldown());
        } else if (chargesLeft == 0)
            System.out.println("OPS, tesla has no charges left!");
        else if (getRemainingCooldown() != 0)
            System.out.println("tesla is on cooldown... \t " + remainingCooldown + " seconds remaining");


    }

    public ArrayList<AlienCreeps> findTarget(int middleX, int middleY) {
        ArrayList<AlienCreeps> targets = new ArrayList<>();
        for (AlienCreeps alienCreep : AlienCreeps.getAllAlienCreeps()) {
            if (distance(alienCreep.getCoordinates(), middleX, middleY) <= getRange())
                targets.add(alienCreep);
        }
        return targets;
    }

    private double distance(int[] coOrdinate, int middleX, int middleY) {
        double deltaXInPow2 = Math.pow(Math.abs(coOrdinate[0] - middleX), 2);
        double deltaYInPow2 = Math.pow(Math.abs(coOrdinate[1] - middleY), 2);
        return Math.sqrt(deltaXInPow2 + deltaYInPow2);
    }

    public void kill(ArrayList targets) {
        for (AlienCreeps alienCreep : AlienCreeps.getAllAlienCreeps()) {
            if (targets.contains(alienCreep))
                AlienCreeps.getAllAlienCreeps().remove(alienCreep);
        }
    }

    public static boolean isPossibleOrNot() {
        return possibleOrNot;
    }

    public static void setPossibleOrNot(boolean possibleOrNot) {
        Tesla.possibleOrNot = possibleOrNot;
    }

    public static Tesla getInstance() {
        return tesla;
    }


    public void shoot() {

    }

    public void weaken() {

    }



}
