package gameLogic;

import gameLogic.firings.movableFirings.Hero;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Barrack {

    private ArrayList<Integer> minsToCreateASoldier = new ArrayList<>();
    private int[] coordinate;
    private int price;
    private static Barrack barrack = new Barrack();
    private ImageView imageView;

    public ArrayList<Integer> getMinsToCreateASoldier() {
        return minsToCreateASoldier;
    }

    private Barrack() {
        price = 90;
        minsToCreateASoldier.add(0);
        minsToCreateASoldier.add(0);
        minsToCreateASoldier.add(0);
        try {
            imageView = new ImageView(new Image(new FileInputStream("images/Barrack.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
