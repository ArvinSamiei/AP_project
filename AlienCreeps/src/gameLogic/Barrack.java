package gameLogic;

import gameLogic.firings.movableFirings.Hero;

import java.util.ArrayList;

public class Barrack {

    private ArrayList<Integer> minsToCreateASoldier = new ArrayList<>();
    private int[] coordinate;
    private int price;
    private static Barrack barrack = new Barrack();

    public ArrayList<Integer> getMinsToCreateASoldier() {
        return minsToCreateASoldier;
    }

    private Barrack() {
        price = 90;
        minsToCreateASoldier.add(0);
        minsToCreateASoldier.add(0);
        minsToCreateASoldier.add(0);
    }

    public static Barrack getInstance() {
        return barrack;
    }


    public void trainSoldiers(/*boolean[] aliveSoldiers*/ Hero hero) {

        //TODO: beshe ke bezanam...
    }

    public void setMinsToCreateASoldier(ArrayList<Integer> minsToCreateASoldier) {
        this.minsToCreateASoldier = minsToCreateASoldier;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }
}
