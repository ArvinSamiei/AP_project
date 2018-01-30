package view.weaponImageViews.weaponImmages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MachinGunImages extends WeaponImages {


    public MachinGunImages() {
        super(4);
    }

    @Override
    protected void makeFiringImages() {
        try {
            firingImages[0] = new Image(new FileInputStream("images/MachinGun/MachinGun0Firing.png"));
            firingImages[1] = new Image(new FileInputStream("images/MachinGun/MachinGun1Firing.png"));
            firingImages[2] = new Image(new FileInputStream("images/MachinGun/MachinGun2Firing.png"));
            firingImages[3] = new Image(new FileInputStream("images/MachinGun/MachinGun3Firing.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading firing machin gun images");
        }
    }

    @Override
    protected void makeNonFiringImages() {
        try {
            nonFiringImages[0] = new Image(new FileInputStream("images/MachinGun/MachinGun0.png"));
            nonFiringImages[1] = new Image(new FileInputStream("images/MachinGun/MachinGun1.png"));
            nonFiringImages[2] = new Image(new FileInputStream("images/MachinGun/MachinGun2.png"));
            nonFiringImages[3] = new Image(new FileInputStream("images/MachinGun/MachinGun3.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fail loading machin gun images");
        }
    }


}
