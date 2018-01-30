package gameLogic.firings.movableFirings;

import gameLogic.Barrack;
import gameLogic.Engine;
import gameLogic.firings.Firings;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

public class Soldier extends YourFighters implements Firings {
    int counterForFire = 0;

    public Soldier() { // hero behesh ezafe kardam ta too sarbazash adesh konam
        super(true);
        Engine.getInstance().hero.getAllSoldiers().add(this);
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
    protected void increaseExperience() {
        experience += 5;
    }



    @Override
    public void freeze() {

    }

    public void weaken(AlienCreeps alienCreep) {
            alienCreep.setEnergy(alienCreep.getEnergy() - this.getPower());
            alienCreep.isDead();
    }

    public void move(Hero hero) {
        for (int i = 0; i < hero.getAllSoldiers().size(); i++) {
            if (i == 0) {
                setCoordinates(new int[]{hero.getCoordinates()[0] + 20, hero.getCoordinates()[1]});
            }
            if (i == 1) {
                setCoordinates(new int[]{hero.getCoordinates()[0] - 20, hero.getCoordinates()[1]});
            }
            if (i == 2) {
                setCoordinates(new int[]{hero.getCoordinates()[0], hero.getCoordinates()[1] + 20});
            }
        }
    }

    public int getCounterForFire() {
        return counterForFire;
    }

    public void setCounterForFire(int counterForFire) {
        this.counterForFire = counterForFire;
    }

}