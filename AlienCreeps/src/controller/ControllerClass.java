package controller;

import gameLogic.Engine;
import gameLogic.Player;
import gameLogic.WeaponPlace;
import gameLogic.firings.Weapon;

public class ControllerClass {
    public void setWeapon(int numOfWeapnPlace, String name) throws notEnoughMoneyException {
        for (int i = 0; i < WeaponPlace.getWeaponPlaces().length; i++) {
            if (i == numOfWeapnPlace) {
                if (!WeaponPlace.getWeaponPlaces()[i].setWeapon(name, Engine.getInstance().getPlayer())) {
                    throw new notEnoughMoneyException();
                }
            }
        }
    }
}

class notEnoughMoneyException extends Exception{
    public notEnoughMoneyException(){
        super("You don't have enough money");
    }
}
