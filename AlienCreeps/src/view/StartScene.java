package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class StartScene extends Scene {

    public StartScene(Parent root, double width, double height, Paint fill, Stage stage) {
        super(root, width, height, fill);
        makeStartScene(this,stage);
    }


    //information on the language situation
    public static HashMap<String, String> persian = new HashMap<>(7);
    public static HashMap<String, String> english = new HashMap<>(7);
    public static HashMap<String, String> currentLanguage = english;
    static {

        persian.put("startMenu", "منوی شروع");
        persian.put("play", "بازی");
        persian.put("load", "لود");
        persian.put("quit", "خروج");
        persian.put("change language", "تغییر زبان");
        persian.put("resume", "ادامه");
        persian.put("tutorial", "آموزش");

        english.put("startMenu", "Start Menu");
        english.put("play", "play");
        english.put("load", "load");
        english.put("quit", "quit");
        english.put("change language", "change language");
        english.put("resume", "resume");
        english.put("tutorial", "tutorial");

    }

    private void makeStartScene(Scene startScene, Stage stage) {
        Group root = (Group) startScene.getRoot();
        root.getChildren().clear();




        //startMenu back ground
        try {
            ImageView background = new ImageView(new Image(new FileInputStream(new File("images\\startMenuBackGround.jpg"))));
            root.getChildren().add(background);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





        //startMenu label
        Label label = new Label(currentLanguage.get("startMenu"));
        label.relocate(580, 100);
        Font nazanin = new Font("B Nazanin", 38);
        label.setFont(nazanin);
        label.setTextFill(Color.DARKGRAY);

        root.getChildren().add(label);


        //cursor image
        try {
        Image image = new Image(new FileInputStream(new File("images\\swordsCursor.png")));
            this.setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        //Image image2 = new Image()
        //this.setCursor(new ImageCursor(image));

        //start menu buttons
        Button playButton = new Button();
        Button resumeButton = new Button();
        Button loadButton = new Button();
        Button changeLanguage = new Button();
        Button quitButton = new Button();
        Button toturialButton = new Button();
        Font nazaninKoochick = new Font("B Nazanin",18);
        Border buttonborder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY,BorderWidths.DEFAULT));


        playButton.setText(currentLanguage.get("play"));
        playButton.setFont(nazaninKoochick);
        playButton.setStyle("-fx-font-weight: bold;");
        playButton.relocate(100,200);
        playButton.setMinSize(100,30);
        playButton.setTextFill(Color.GREEN);
        //Effect buttonEffect = new //ino badan bebinam chie
        DropShadow dropShadow = new DropShadow(BlurType.TWO_PASS_BOX,Color.GRAY,5,0.001,10,10);
        playButton.setEffect(dropShadow);
        playButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"+
                "-fx-font-weight: bold;");
        playButton.setOnMouseEntered(event -> {
            playButton.setStyle("-fx-background-color: \n" +
                    "        #a6b5c9,\n" +
                    "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                    "        linear-gradient(#020b02, #3a3a3a),\n" +
                    "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });
        playButton.setOnMouseExited(event -> {
            playButton.setStyle("-fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });


        try {
            Image image = new Image(new FileInputStream(new File("images\\playCursor.png")));
            playButton.setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


        resumeButton.setText(currentLanguage.get("resume"));
        resumeButton.setFont(nazaninKoochick);
        resumeButton.setStyle("-fx-font-weight: bold;");
        resumeButton.relocate(100,270);
        resumeButton.setMinSize(100,30);
        resumeButton.setEffect(dropShadow);
        resumeButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"+
                "-fx-font-weight: bold;");
        resumeButton.setOnMouseEntered(event -> {
            resumeButton.setStyle("-fx-background-color: \n" +
                    "        #a6b5c9,\n" +
                    "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                    "        linear-gradient(#020b02, #3a3a3a),\n" +
                    "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });
        resumeButton.setOnMouseExited(event -> {
            resumeButton.setStyle("-fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });


        try {
            Image image = new Image(new FileInputStream(new File("images\\bow-and-arrowCursor.png")));
            resumeButton.setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        loadButton.setText(currentLanguage.get("load"));
        loadButton.setFont(nazaninKoochick);
        loadButton.setStyle("-fx-font-weight: bold;");
        loadButton.relocate(100,340);
        loadButton.setMinSize(100,30);
        loadButton.setEffect(dropShadow);
        loadButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"+
                "-fx-font-weight: bold;");
        loadButton.setOnMouseEntered(event -> {
            loadButton.setStyle("-fx-background-color: \n" +
                    "        #a6b5c9,\n" +
                    "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                    "        linear-gradient(#020b02, #3a3a3a),\n" +
                    "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });
        loadButton.setOnMouseExited(event -> {
            loadButton.setStyle("-fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });


        try {
            Image image = new Image(new FileInputStream(new File("images\\axeCursor.png")));
            loadButton.setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        changeLanguage.setText(currentLanguage.get("change language"));
        changeLanguage.setFont(nazaninKoochick);
        changeLanguage.setStyle("-fx-font-weight: bold;");
        changeLanguage.relocate(100,410);
        changeLanguage.setMinSize(100,30);
        changeLanguage.setEffect(dropShadow);
        changeLanguage.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"+
                "-fx-font-weight: bold;");
        changeLanguage.setOnMouseEntered(event -> {
            changeLanguage.setStyle("-fx-background-color: \n" +
                    "        #a6b5c9,\n" +
                    "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                    "        linear-gradient(#020b02, #3a3a3a),\n" +
                    "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });
        changeLanguage.setOnMouseExited(event -> {
            changeLanguage.setStyle("-fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });


        try {
            Image image = new Image(new FileInputStream(new File("images\\baseballCursor.png")));
            changeLanguage.setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        toturialButton.setText(currentLanguage.get("tutorial"));
        toturialButton.setFont(nazaninKoochick);
        toturialButton.setStyle("-fx-font-weight: bold;");
        toturialButton.relocate(100,480);
        toturialButton.setMinSize(100,30);
        toturialButton.setCursor(Cursor.CROSSHAIR);
        toturialButton.setEffect(dropShadow);
        toturialButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"+
                "-fx-font-weight: bold;");
        toturialButton.setOnMouseEntered(event -> {
            toturialButton.setStyle("-fx-background-color: \n" +
                    "        #a6b5c9,\n" +
                    "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                    "        linear-gradient(#020b02, #3a3a3a),\n" +
                    "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });
        toturialButton.setOnMouseExited(event -> {
            toturialButton.setStyle("-fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });



        quitButton.setText(currentLanguage.get("quit"));
        quitButton.setFont(nazaninKoochick);
        quitButton.setStyle("-fx-font-weight: bold;");
        quitButton.relocate(100,550);
        quitButton.setMinSize(100,30);
        quitButton.setEffect(dropShadow);
        quitButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"+
                "-fx-font-weight: bold;");
        quitButton.setOnMouseEntered(event -> {
            quitButton.setStyle("-fx-background-color: \n" +
                    "        #a6b5c9,\n" +
                    "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                    "        linear-gradient(#020b02, #3a3a3a),\n" +
                    "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });
        quitButton.setOnMouseExited(event -> {
            quitButton.setStyle("-fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-background-insets: 0,1,2,0;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-font-family: \"Arial\";\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-font-size: 12px;\n" +
                    "    -fx-padding: 10 20 10 20;"+
                    "-fx-font-weight: bold;");
        });

        try {
            Image image = new Image(new FileInputStream(new File("images\\quitCursor.png")));
            quitButton.setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        root.getChildren().addAll(playButton,resumeButton,loadButton,changeLanguage,quitButton,toturialButton);




        //button actions:

        //change language button
        changeLanguage.setOnAction(event -> {
            if (currentLanguage == english) {
                currentLanguage = persian;
                makeStartScene(root.getScene(), stage);
            } else if (currentLanguage == persian) {
                currentLanguage = english;
                makeStartScene(root.getScene(), stage);
            }
        });
        //quit button
        quitButton.setOnAction(event -> {
            stage.close();
        });
        //play button todo
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Graphic.setIsMainSceneOrNot(true);
                MainScene mainScene = new MainScene(new Group(), 1280, 960, Color.GRAY, stage);
                stage.setScene(mainScene);
            }
        });
    }
}
