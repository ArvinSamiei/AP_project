package view.weaponImageViews.alienCreepsImages;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ActivinionImages  {
    private Image[] deadImages = new Image[5];
    private Image[] moveForwardImages = new Image[3];
    private Image[] moveRightImages = new Image[6];
    private Image[] firingImages = new Image[3];

    public ActivinionImages() {
        makeDeadImages();
        makeMoveForwardImages();
        makeMoveRightImages();
    }

    private void makeDeadImages() {
        for (int i = 0; i < 5; i++) {
            String imageName = "images/Activinion/Activinion_dead" + i + ".png";
            try {
                Image image = new Image(new FileInputStream(imageName));
                deadImages[i] = image;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void makeFiringImages() {
        for (int i = 0; i < 3; i++) {
            String imageName = "images/Activinion/Activinion_attack" + i + ".png";

            try {
                Image image = new Image(new FileInputStream(imageName));
                firingImages[i] = image;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeMoveForwardImages() {
        for (int i = 0; i < 3; i++) {
            String imageName = "images/Activinion/Activinion_moveForward" + i + ".png";
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
            String imageName = "images/Activinion/Activinion_moveRight"+i+".png";
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

    public Image[] getFiringImages(){
        return firingImages;
    }
}
