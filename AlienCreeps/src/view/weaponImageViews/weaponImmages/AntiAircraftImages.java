package view.weaponImageViews.weaponImmages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AntiAircraftImages extends WeaponImages {

    public AntiAircraftImages() {
        super(2);
    }


    @Override
    protected void makeFiringImages() {
        try {
            nonFiringImages[0] = new Image(new FileInputStream("images/AntiAircraft/AntiAircraft0.png"));
            nonFiringImages[1] = new Image(new FileInputStream("images/AntiAircraft/AntiAircraft1.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading antiAircraft images");
        }
    }

    @Override
    protected void makeNonFiringImages() {
        try {
            firingImages[0] = new Image(new FileInputStream("images/AntiAircraft/AntiAircraft0Firing.png"));
            firingImages[1] = new Image(new FileInputStream("images/AntiAircraft/AntiAircraft1Firing.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading firing anti aircraft");
        }
    }


}
