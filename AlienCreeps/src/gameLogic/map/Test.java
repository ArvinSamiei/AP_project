/*package gameLogic.map;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.MainScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Test {
    public static MainScene mainScene;

    public static void testPath() {
        Path path = new Path();

        for (int[] ints : Path.getPathHomes1_1()) {
            try {
                ImageView imageView = new ImageView(new Image(new FileInputStream("images/wormHole2.png")));
                imageView.relocate(ints[0],ints[1]);
                Group root = (Group) mainScene.getRoot();
                root.getChildren().add(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (int[] ints : Path.getPathHomes1_2()) {
            try {
                ImageView imageView = new ImageView(new Image(new FileInputStream("images/wormHole2.png")));
                imageView.relocate(ints[0],ints[1]);
                Group root = (Group) mainScene.getRoot();
                root.getChildren().add(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (int[] ints : Path.getPathHomes2_1()) {
            try {
                ImageView imageView = new ImageView(new Image(new FileInputStream("images/wormHole2.png")));
                imageView.relocate(ints[0],ints[1]);
                Group root = (Group) mainScene.getRoot();
                root.getChildren().add(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (int[] ints : Path.getPathHomes2_2()) {
            try {
                ImageView imageView = new ImageView(new Image(new FileInputStream("images/wormHole2.png")));
                imageView.relocate(ints[0],ints[1]);
                Group root = (Group) mainScene.getRoot();
                root.getChildren().add(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
*/

