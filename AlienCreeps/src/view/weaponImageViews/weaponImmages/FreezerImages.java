package view.weaponImageViews.weaponImmages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FreezerImages extends WeaponImages{


    public FreezerImages() {
        super(2);
    }

    public void makeFreezerImageSet() {

    }


    @Override
    protected void makeFiringImages() {
        try {
            firingImages[0] = new Image(new FileInputStream("images/Freezer/FreezerFiring.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading firing freezer images");
        }
    }

    @Override
    protected void makeNonFiringImages() {
        try {
            nonFiringImages[0] = new Image(new FileInputStream("images/Freezer/Freezer.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading freezer images");
        }
    }
}
