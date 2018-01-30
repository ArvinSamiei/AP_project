package view.weaponImageViews.alienCreepsImages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AlgwasonionImages {
    private Image[] deadImages = new Image[5];
    private Image[] moveForwardImages = new Image[3];
    private Image[] moveRightImages = new Image[6];
    private Image[] firingImages = new Image[3];

    public AlgwasonionImages() {
        makeDeadImages();
        makeMoveForwardImages();
        makeMoveRightImages();
    }

    protected void makeFiringImages() {
        for (int i = 0; i < 3; i++) {
            String imageName = "images/Algwasonion/firing" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                firingImages[i] = image;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void makeDeadImages() {
        for (int i = 0; i < 5; i++) {
            String imageName = "images/Algwasonion/dead" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                deadImages[i] = image;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void makeMoveForwardImages() {
        for (int i = 0; i < 3; i++) {
            String imageName = "images/Algwasonion/forward" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                moveForwardImages[i] = image;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeMoveRightImages() {
        for (int i = 0; i < 6; i++) {
            String imageName = "images/Algwasonion/right" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                moveRightImages[i] = image;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Image[] getDeadImages() {
        return deadImages;
    }


    public Image[] getMoveForwardImages() {
        return moveForwardImages;
    }


    public Image[] getMoveRightImages() {
        return moveRightImages;
    }

    public Image[] getFiringImages() {
        return firingImages;
    }

}
