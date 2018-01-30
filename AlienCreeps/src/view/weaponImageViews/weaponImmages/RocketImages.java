package view.weaponImageViews.weaponImmages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RocketImages extends WeaponImages {

    public RocketImages() {
        super(11);
    }


    @Override
    protected void makeFiringImages() {
        try {
            firingImages[0] = new Image(new FileInputStream("images/Rocket/rocket0Firing.png"));
            firingImages[1] = new Image(new FileInputStream("images/Rocket/rocket1Firing.png"));
            firingImages[2] = new Image(new FileInputStream("images/Rocket/rocket2Firing.png"));
            firingImages[3] = new Image(new FileInputStream("images/Rocket/rocket3Firing.png"));
            firingImages[4] = new Image(new FileInputStream("images/Rocket/rocket4Firing.png"));
            firingImages[5] = new Image(new FileInputStream("images/Rocket/rocket5Firing.png"));
            firingImages[6] = new Image(new FileInputStream("images/Rocket/rocket6Firing.png"));
            firingImages[7] = new Image(new FileInputStream("images/Rocket/rocket7Firing.png"));
            firingImages[8] = new Image(new FileInputStream("images/Rocket/rocket8Firing.png"));
            firingImages[9] = new Image(new FileInputStream("images/Rocket/rocket9Firing.png"));
            firingImages[10] = new Image(new FileInputStream("images/Rocket/rocket10Firing.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading rocket images");
        }
    }

    protected void makeNonFiringImages() {
        try {
            nonFiringImages[0] = new Image(new FileInputStream("images/Rocket/rocket0.png"));
            nonFiringImages[1] = new Image(new FileInputStream("images/Rocket/rocket1.png"));
            nonFiringImages[2] = new Image(new FileInputStream("images/Rocket/rocket2.png"));
            nonFiringImages[3] = new Image(new FileInputStream("images/Rocket/rocket3.png"));
            nonFiringImages[4] = new Image(new FileInputStream("images/Rocket/rocket4.png"));
            nonFiringImages[5] = new Image(new FileInputStream("images/Rocket/rocket5.png"));
            nonFiringImages[6] = new Image(new FileInputStream("images/Rocket/rocket6.png"));
            nonFiringImages[7] = new Image(new FileInputStream("images/Rocket/rocket7.png"));
            nonFiringImages[8] = new Image(new FileInputStream("images/Rocket/rocket8.png"));
            nonFiringImages[9] = new Image(new FileInputStream("images/Rocket/rocket9.png"));
            nonFiringImages[10] = new Image(new FileInputStream("images/Rocket/rocket10.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading firing rocket images");
        }
    }
}
