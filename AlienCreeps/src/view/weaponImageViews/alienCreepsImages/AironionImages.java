package view.weaponImageViews.alienCreepsImages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AironionImages {
    private Image[] firingImages = new Image[3];
    private Image[] nonFiringImages = new Image[11];

    public AironionImages() {
        makeFiringImages();
        makeNonFiringImages();
    }

    protected void makeFiringImages() {
        for (int i = 0; i < 3; i++) {
            String imageName = "images/Aironion/AironionFire" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                firingImages[i] = image;
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected void makeNonFiringImages() {
        for (int i = 0; i < 11; i++) {
            String imageName = "images/Aironion/Aironion" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                nonFiringImages[i] = image;
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public Image[] getFiringImages() {
        return firingImages;
    }

    public Image[] getNonFiringImages() {
        return nonFiringImages;
    }
}
