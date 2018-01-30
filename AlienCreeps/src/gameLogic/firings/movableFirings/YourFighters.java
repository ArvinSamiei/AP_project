package gameLogic.firings.movableFirings;

import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public abstract class YourFighters extends MovableFirings {
    private static ArrayList<MovableFirings> allMovableFirings = new ArrayList<>();
    private ArrayList<AlienCreeps> alienCreepsKilled = new ArrayList<>();
    protected int experience;
    protected int level = 1;
    private boolean deadStat = false;
    private int[] coordinates = new int[2];
    boolean fireStat = false;

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getExperience() {
        return experience;
    }


    private void checkToIncreaseLevel() {
        if (experience > 50 * level && level < 3) {
            level++;
        }
    }

    public abstract void freeze();



    public YourFighters() {
        super(0, 0, 300, 6, 5, 0.5 * 100, false);
    }

    public YourFighters(boolean b) {
        super(0, 0, 150, 20, 5, 0.5 * 200,false);
    }

    public boolean getDeadStat() {
        return deadStat;
    }

    public void setDeadStat(boolean deadStat) {
        this.deadStat = deadStat;
    }

    public boolean isFireStat() {
        return fireStat;
    }

    public void setFireStat(boolean fireStat) {
        this.fireStat = fireStat;
    }
}
