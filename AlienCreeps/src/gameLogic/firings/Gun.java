package gameLogic.firings;

public enum Gun {
    AK47(20, 0, 0.5, 7, 0, 20, false), ShotGun(20, 0, 0.5, 2, 0, 20, true), SniperRifle(70, 50, 6, 1, 0, 60, false);

    private int powerOnGroundUnits;
    private int powerOnAirUnits;
    private double range;
    private int fireRate;
    private int speedReduction;
    private int price;
    private boolean isPogromist;

    Gun(int powerOnGroundUnits, int powerOnAirUnits, double range, int fireRate, int speedReduction, int price, boolean isPogromist) {
        setPowerOnGroundUnits(powerOnGroundUnits);
        setPowerOnAirUnits(powerOnAirUnits);
        setRange(range);
        setFireRate(fireRate);
        setSpeedReduction(speedReduction);
        setPrice(price);
        setPogromist(isPogromist);
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPogromist() {
        return isPogromist;
    }

    public void setPogromist(boolean pogromist) {
        isPogromist = pogromist;
    }
}
