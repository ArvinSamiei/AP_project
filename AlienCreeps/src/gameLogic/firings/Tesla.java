package gameLogic.firings;

public class Tesla implements Firings {
    private int range;
    private int cooldown;
    public int counterForFire = 0;
    private int remainingCooldown;
    private int chargesLeft;
    private static boolean possibleOrNot = false;
    private static Tesla tesla = new Tesla();
    public int numOfUses = 0;

    private Tesla() {
        range = 10 * 20;
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
