package view.weaponImageViews.weaponImmages;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LaserImages extends WeaponImages {

    public LaserImages() {
        super(1);
    }


    protected void makeNonFiringImages() {
        try {
            nonFiringImages[0] = new Image(new FileInputStream("images/laser/laser.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading laser image");
        }
    }

    protected void makeFiringImages() {
        try {
            firingImages[0] = new Image(new FileInputStream("images/laser/laserFiring.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading laser firing image");
        }
    }
}
