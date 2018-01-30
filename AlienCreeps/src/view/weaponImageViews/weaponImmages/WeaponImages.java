package view.weaponImageViews.weaponImmages;

import javafx.scene.image.Image;

import java.util.HashMap;

public abstract class WeaponImages {
    protected Image[] nonFiringImages;
    protected Image[] firingImages;
    protected HashMap<Image, Image> mapOfNonToFiring = new HashMap<>();

    public WeaponImages(int numOfImages) {
        nonFiringImages = new Image[numOfImages];
        firingImages = new Image[numOfImages];
        makeMapOfNonToFiring(numOfImages);
    }

    private void makeMapOfNonToFiring(int numOfImages) {
        makeFiringImages();
        makeNonFiringImages();
        for (int i = 0; i < numOfImages; i++) {
            mapOfNonToFiring.put(nonFiringImages[i], firingImages[i]);
        }
    }

    protected abstract void makeFiringImages();

    protected abstract void makeNonFiringImages();

    public Image[] getNonFiringImages() {
        return nonFiringImages;
    }

    public Image[] getFiringImages() {
        return firingImages;
    }

    public HashMap<Image, Image> getMapOfNonToFiring() {
        return mapOfNonToFiring;
    }
}
