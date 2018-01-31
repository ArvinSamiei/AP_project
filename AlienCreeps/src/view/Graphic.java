package view;

import controller.ControllerClass;
import gameLogic.Barrack;
import gameLogic.Engine;
import gameLogic.WeaponPlace;
import gameLogic.firings.Tesla;
import gameLogic.firings.Weapon;
import gameLogic.firings.WeaponsObject;
import gameLogic.firings.movableFirings.Hero;
import gameLogic.firings.movableFirings.MovableFirings;
import gameLogic.firings.movableFirings.Soldier;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;
import gameLogic.map.WormHole;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.weaponImageViews.alienCreepsImages.ActivinionImages;
import view.weaponImageViews.alienCreepsImages.AironionImages;
import view.weaponImageViews.alienCreepsImages.AlbertonionImages;
import view.weaponImageViews.alienCreepsImages.AlgwasonionImages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import static gameLogic.firings.movableFirings.alienCreeps.AlienCreepTypes.*;

public class Graphic extends Application {
    private WormHole crossedWormHole = new WormHole();
    public static boolean movableMoved = false;
    private static boolean isMainSceneOrNot = false;
    private AlbertonionImages albertonionImages = new AlbertonionImages();
    private AlgwasonionImages algwasonionImages = new AlgwasonionImages();
    private AironionImages aironionImages = new AironionImages();
    private ActivinionImages activinionImages = new ActivinionImages();
    static ImageView teslaImageView = new ImageView();

    public static boolean isIsMainSceneOrNot() {
        return isMainSceneOrNot;
    }

    public static void setIsMainSceneOrNot(boolean isMainSceneOrNot) {
        Graphic.isMainSceneOrNot = isMainSceneOrNot;
    }

    public static void main(String[] args) {
        launch(args);
        ControllerClass controller = new ControllerClass();
    }

    AnimationTimer animationTimer = new AnimationTimer() {
        Integer counter = 0;

        @Override
        public void handle(long now) {
            if (stage.getScene() instanceof StartScene) {
                return;
            }
            if (!(stage.getScene() instanceof MainScene)) {
                return;
            }
            if (((MainScene) stage.getScene()).getPauseState() == true) {
                return;
            }
            MainScene mainScene = (MainScene) stage.getScene();

            ((MainScene) (stage.getScene())).goldLabel.setText("Gold : " + Engine.getInstance().getPlayer().getGold());
            Group rootOfMainScene = (Group) ((MainScene) (stage.getScene())).getRoot();
            counter++;
            manageStartingPointOfWormHoles();
            manageEndPointForWormHoles();

            moveOfAliens(rootOfMainScene, counter);

            if (counter % 60 == 0) {
                ((MainScene) stage.getScene()).timer.increaseTimer();
                ((MainScene) stage.getScene()).getClock().setText(((MainScene) stage.getScene()).timer.toString());
            }
            if (counter % 240 == 0) {
                makeAlienCreeps();
            }
            //tesla handle
            manageTesla();

            Hero hero = Engine.getInstance().hero;

            if (hero.getDeadStat() == true) {
                try {
                    hero.getImageView().setImage(new Image(new FileInputStream("images/hero images/Die4.png")));
                    hero.setTarget(null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                findHeroTarget(hero);
                heroFire(hero);
            }


            MainScene.enrgyOfHero.setText("Energy of hero : " + hero.getEnergy());

            showDeadAliens(counter, rootOfMainScene);


            firingPicsOfAliens();

            alienCreepsFire(rootOfMainScene);

            for (int i = 0; i < WeaponPlace.getWeaponPlaces().length; i++) {
                WeaponPlace weaponPlace = WeaponPlace.getWeaponPlaces()[i];

                if (weaponPlace.getWeapon() == null) {
                    continue;
                }

                if (weaponPlace.getWeapon().getTargets().size() == 0) {
                    switch (weaponPlace.getWeapon().getWeapon()) {
                        case AntiAircraft:
                            mainScene.weaponplacesImages[i].setImage(mainScene.antiAircraftImages.getNonFiringImages()[0]);
                            break;
                        case MachineGun:
                            mainScene.weaponplacesImages[i].setImage(mainScene.machinGunImages.getNonFiringImages()[2]);
                            break;
                        case Freezer:
                            mainScene.weaponplacesImages[i].setImage(mainScene.freezerImages.getNonFiringImages()[0]);
                            break;
                        case Rocket:
                            mainScene.weaponplacesImages[i].setImage(mainScene.rocketImages.getNonFiringImages()[2]);
                            break;
                        case Laser:
                            mainScene.weaponplacesImages[i].setImage(mainScene.laserImages.getNonFiringImages()[0]);
                            break;
                    }
                }
            }

            weaponsFindTarget();


            weaponsFire(mainScene);


            manageImagesOfSoldiers(hero);

            for (Soldier soldier : hero.getAllSoldiers()) {
                if (soldier.getTarget() == null){
                    try {
                        soldier.getImageView().setImage(new Image(new FileInputStream("images/soldier images/MoveRight1.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Soldier soldier : hero.getAllSoldiers()) {
                rootOfMainScene.getChildren().remove(soldier.getImageView());
                soldier.getImageView().relocate(soldier.getCoordinates()[0], soldier.getCoordinates()[1]);
                rootOfMainScene.getChildren().add(soldier.getImageView());
            }


            for (AlienCreeps alienCreeps : AlienCreeps.getAllAlienCreeps()) {
                for (Soldier soldier : hero.getAllSoldiers()) {
                    if (soldier.getTarget() != null) {
                        continue;
                    }
                    int x = Math.abs(alienCreeps.getCoordinates()[0] - soldier.getCoordinates()[0]);
                    int y = Math.abs(alienCreeps.getCoordinates()[1] - soldier.getCoordinates()[1]);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= soldier.getFireRange()) {
                        soldier.setTarget(alienCreeps);
                        if (alienCreeps.getShooterToThis() == null) {
                            alienCreeps.setShooterToThis(soldier);
                        }
                    }
                }
            }

            for (int i1 = 0; i1 < hero.getAllSoldiers().size(); i1++) {
                Soldier soldier = hero.getAllSoldiers().get(i1);
                if (soldier.getTarget() != null) {
                    if (MainScene.moved == true) {
                        soldier.getTarget().setFiring(false);
                        soldier.setTarget(null);
                    } else {
                        soldier.setCounterForFire(soldier.getCounterForFire() + 1);
                        if (soldier.getCounterForFire() % (60 / soldier.getFireRate()) == 0) {
                            System.out.println("hello");
                            soldier.weaken(soldier.getTarget());
                            soldier.getTarget().setFiring(true);
                            if (soldier.getTarget().isDead()) {
                                Engine.getInstance().getPlayer().setGold(Engine.getInstance().getPlayer().getGold() + 5);
                                AlienCreeps.getDeadAlienCreeps().add(soldier.getTarget());
                                AlienCreeps.getAllAlienCreeps().remove(soldier.getTarget());
                                soldier.setTarget(null);
                                soldier.setCounterForFire(0);
                            }
                            if (soldier.getCounterForFire() == soldier.getFireRate()) {
                                soldier.setCounterForFire(0);
                            }
                            try {
                                System.out.println("shelik");
                                soldier.getImageView().setImage(new Image(new FileInputStream("images/soldier images/MoveRight1Firing.png")));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }


            manageHeroDeath(hero);

            for (int i = 0; i < Barrack.getInstance().getMinsToCreateASoldier().size(); i++) {
                Integer integer = Barrack.getInstance().getMinsToCreateASoldier().get(i);
                if (integer.equals(hero.getTimeToreturn())) {
                    Soldier soldier = new Soldier();
                    Barrack.getInstance().getMinsToCreateASoldier().remove(integer);
                } else {
                    integer++;
                    Barrack.getInstance().getMinsToCreateASoldier().set(i, integer);
                }
            }
        }
    };

    private void manageImagesOfSoldiers(Hero hero) {
        for (int i = 0; i < hero.getAllSoldiers().size(); i++) {
            Soldier soldier = hero.getAllSoldiers().get(i);
            switch (i) {
                case 0:
                    soldier.setCoordinates(new int[]{hero.getCoordinates()[0] - 32, hero.getCoordinates()[1]});
                    break;
                case 1:
                    soldier.setCoordinates(new int[]{hero.getCoordinates()[0], hero.getCoordinates()[1] - 32});
                    break;
                case 2:
                    soldier.setCoordinates(new int[]{hero.getCoordinates()[0] + 64, hero.getCoordinates()[1]});
            }
        }
    }

    private void manageTesla() {
        if (MainScene.teslashooted == true) {
            if (Tesla.getInstance().numOfUses < 2) {
                Tesla.getInstance().counterForFire++;
                if (Tesla.getInstance().counterForFire == 600) {
                    try {
                        MainScene.teslaImage.setImage(new Image(new FileInputStream("images/Tesla/tesla.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    MainScene.teslashooted = false;
                }
            }
        }
    }

    private void weaponsFindTarget() {
        for (int i = 0; i < WeaponPlace.getWeaponPlaces().length; i++) {

            WeaponPlace weaponPlace = WeaponPlace.getWeaponPlaces()[i];
            if (weaponPlace.getWeapon() == null) {
                continue;
            }

            WeaponsObject weapon = weaponPlace.getWeapon();
            if (weapon.getWeapon().equals(Weapon.Freezer)) {
                for (AlienCreeps alienCreeps : weapon.getTargets()) {
                    alienCreeps.getAlienCreepTypes().setSpeed(alienCreeps.getAlienCreepTypes().speed);
                }
            }
            weapon.getTargets().clear();
            for (int i1 = 0; i1 < AlienCreeps.getAllAlienCreeps().size(); i1++) {
                AlienCreeps alienCreeps = AlienCreeps.getAllAlienCreeps().get(i1);
                int x = Math.abs(alienCreeps.getCoordinates()[0] - weaponPlace.getCoordinates()[0]);
                int y = Math.abs(alienCreeps.getCoordinates()[1] - weaponPlace.getCoordinates()[1]);

                if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= weapon.getRange()) {
                    if (weapon.getPowerOnAirUnits() == 0 && alienCreeps.getAlienCreepTypes().getType().equals("air")) {
                        continue;
                    }
                    weapon.getTargets().add(alienCreeps);
                    if (weapon.isPogromist() == false) {
                        break;
                    }
                }
            }
        }
    }

    private void weaponsFire(MainScene mainScene) {
        for (int i = 0; i < WeaponPlace.getWeaponPlaces().length; i++) {
            WeaponPlace weaponPlace = WeaponPlace.getWeaponPlaces()[i];
            if (weaponPlace.getWeapon() == null) {
                continue;
            }
            WeaponsObject weapon = weaponPlace.getWeapon();

            weapon.setCounterForFire(weapon.getCounterForFire() + 1);

            if ((weapon.getCounterForFire() % (60 / weapon.getFireRate()) == 0)) {
                if (weapon.getTargets().size() == 0) {
                    weapon.setCounterForFire(0);
                    continue;
                }

                if (weapon.getCounterForFire() == weapon.getFireRate()) {
                    weapon.setCounterForFire(0);
                }


                for (int i1 = 0; i1 < weapon.getTargets().size(); i1++) {
                    AlienCreeps alienCreeps = weapon.getTargets().get(i1);
                    int xDifference = alienCreeps.getCoordinates()[0] - weaponPlace.getCoordinates()[0];
                    int yDifference = -(alienCreeps.getCoordinates()[1] - weaponPlace.getCoordinates()[1]);

                    switch (weapon.getWeapon()) {
                        case AntiAircraft:
                            if (xDifference >= 0) {
                                mainScene.weaponplacesImages[i].setImage(mainScene.antiAircraftImages.getFiringImages()[1]);
                            } else {
                                mainScene.weaponplacesImages[i].setImage(mainScene.antiAircraftImages.getFiringImages()[0]);
                            }
                            break;
                        case MachineGun:
                            if (xDifference >= 0 && yDifference >= 0) {
                                mainScene.weaponplacesImages[i].setImage(mainScene.machinGunImages.getFiringImages()[2]);
                            } else if (xDifference >= 0 && yDifference <= 0) {
                                mainScene.weaponplacesImages[i].setImage(mainScene.machinGunImages.getFiringImages()[1]);
                            } else if (xDifference <= 0 && yDifference >= 0) {
                                mainScene.weaponplacesImages[i].setImage(mainScene.machinGunImages.getFiringImages()[3]);
                            } else if (xDifference <= 0 && yDifference <= 0) {
                                mainScene.weaponplacesImages[i].setImage(mainScene.machinGunImages.getFiringImages()[0]);
                            }
                            break;
                        case Freezer:
                            mainScene.weaponplacesImages[i].setImage(mainScene.freezerImages.getFiringImages()[0]);
                            break;
                        case Rocket:
                            if (xDifference >= 0) {
                                mainScene.weaponplacesImages[i].setImage(mainScene.rocketImages.getFiringImages()[2]);
                            } else {
                                if (yDifference <= 0) {
                                    mainScene.weaponplacesImages[i].setImage(mainScene.rocketImages.getFiringImages()[6]);
                                } else {
                                    mainScene.weaponplacesImages[i].setImage(mainScene.rocketImages.getFiringImages()[6]);
                                }
                            }
                            break;
                        case Laser:
                            mainScene.weaponplacesImages[i].setImage(mainScene.laserImages.getFiringImages()[0]);
                            break;
                    }
                    weapon.shoot(alienCreeps);
                    if (alienCreeps.getEnergy() <= 0) {
                        Engine.getInstance().getPlayer().setGold(Engine.getInstance().getPlayer().getGold() + 5);
                        weapon.getTargets().remove(alienCreeps);
                        AlienCreeps.getDeadAlienCreeps().add(alienCreeps);
                        AlienCreeps.getAllAlienCreeps().remove(alienCreeps);
                    }
                }

            }

        }
    }


    private void manageHeroDeath(Hero hero) {
        if (hero.isDeadStat() == true) {
            hero.setTimeLeftToReturn(hero.getTimeLeftToReturn() - 1);
            if (hero.getTimeLeftToReturn() == 0) {
                hero.setDeadStat(false);
                hero.setTimeLeftToReturn(hero.getTimeToreturn());
                hero.setEnergy(300);
            } else {
                try {
                    hero.getImageView().setImage(new Image(new FileInputStream("images/hero images/Die4.png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void alienCreepsFire(Group rootOfMainScene) {
        for (AlienCreeps alienCreeps : AlienCreeps.getAllAlienCreeps()) {
            if (alienCreeps.isFiring() == false) {
                continue;
            }
            alienCreeps.setCounterForFire(alienCreeps.getCounterForFire() + 1);
            if ((alienCreeps.getCounterForFire() % (60 / alienCreeps.getAlienCreepTypes().getFireRate())) != 0) {
                continue;
            }
            alienCreeps.weaken(alienCreeps.getShooterToThis());
            if (alienCreeps.getShooterToThis() == null) {
                break;
            }
            if (alienCreeps.getShooterToThis().isDead()) {
                if (alienCreeps.getShooterToThis() instanceof Soldier){
                    Soldier soldier = (Soldier) alienCreeps.getShooterToThis();
                    rootOfMainScene.getChildren().remove(soldier.getImageView());
                    soldier.isDead();
                }
                alienCreeps.setShooterToThis(null);
                alienCreeps.setFiring(false);
                break;
            }
        }
    }


    private void firingPicsOfAliens() {
        for (AlienCreeps alienCreeps : AlienCreeps.getAllAlienCreeps()) {
            if (alienCreeps.isFiring() == false) {
                continue;
            }
            if ((alienCreeps.getCounterForFire() % (60 / alienCreeps.getAlienCreepTypes().getFireRate())) != 0) {
                continue;
            }
            if (alienCreeps.getShooterToThis() != null) {
                try {
                    switch (alienCreeps.getAlienCreepTypes()) {
                        case Aironion:
                            if (alienCreeps.getCounterForFire() % 3 == 0) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Aironion/AironionFire0.png")));
                            } else if (alienCreeps.getCounterForFire() % 3 == 1) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Aironion/AironionFire1.png")));
                            } else {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Aironion/AironionFire2.png")));
                            }
                            break;
                        case Activionion:
                            if (alienCreeps.getCounterForFire() % 4 == 0) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Activinion/Activinion_attack0.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 1) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Activinion/Activinion_attack1.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 2) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Activinion/Activinion_attack4.png")));
                            }
                            break;
                        case Albertonion:
                            if (alienCreeps.getCounterForFire() % 4 == 0) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Albertonion/firing0.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 1) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Albertonion/firing1.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 2) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Albertonion/firing2.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 3) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Albertonion/firing3.png")));
                            }
                            break;
                        case Algwasonion:
                            if (alienCreeps.getCounterForFire() % 4 == 0) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Algwasonion/firing0.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 1) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Algwasonion/firing1.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 2) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Algwasonion/firing2.png")));
                            } else if (alienCreeps.getCounterForFire() % 4 == 3) {
                                alienCreeps.getImageView().setImage(new Image(new FileInputStream("images/Algwasonion/firing3.png")));
                            }
                            break;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void heroFire(Hero hero) {
        if (hero.getTarget() != null && hero.getEnergy() > 0) {
            if (MainScene.moved == true) {
                hero.getTarget().setFiring(false);
                hero.setTarget(null);
            } else {
                hero.setCounterForFire(hero.getCounterForFire() + 1);
                if (Engine.getInstance().hero.getCounterForFire() % (60 / Engine.getInstance().hero.getFireRate()) == 0) {
                    hero.weaken(hero.getTarget());
                    hero.getTarget().setFiring(true);
                    if (hero.getTarget().isDead()) {
                        Engine.getInstance().getPlayer().setGold(Engine.getInstance().getPlayer().getGold() + 15);
                        AlienCreeps.getDeadAlienCreeps().add(hero.getTarget());
                        AlienCreeps.getAllAlienCreeps().remove(hero.getTarget());
                        hero.setTarget(null);
                        hero.setCounterForFire(0);
                    }
                    if (hero.getCounterForFire() == hero.getFireRate()) {
                        hero.setCounterForFire(0);
                    }
                    hero.drawHero(hero.fireLeftImages, hero.getCounterForFire() % 4);
                }
            }
        }
    }

    private void findHeroTarget(Hero hero) {
        if (hero.getDeadStat() == true) {
            hero.setTarget(null);
            return;
        }
        for (int i = 0; i < AlienCreeps.getAllAlienCreeps().size(); i++) {
            if (hero.getTarget() != null) {
                return;
            }

            AlienCreeps alienCreeps = AlienCreeps.getAllAlienCreeps().get(i);
            if (hero.getGun().getPowerOnAirUnits() == 0 && alienCreeps.getAlienCreepTypes().getType().equals("air")) {
                continue;
            }
            int x = Math.abs(alienCreeps.getCoordinates()[0] - hero.getCoordinates()[0]);
            int y = Math.abs(alienCreeps.getCoordinates()[1] - hero.getCoordinates()[1]);
            if (x <= hero.getFireRange() && y <= hero.getFireRange()) {
                hero.setTarget(alienCreeps);
                if (alienCreeps.getShooterToThis() == null) {
                    alienCreeps.setShooterToThis(hero);
                }
                break;
            }
        }
    }

    private void showDeadAliens(Integer counter, Group rootOfMianScene) {
        if (AlienCreeps.getDeadAlienCreeps().size() == 0) {
            return;
        }
        boolean removeOrNo = false;
        int counterForRemove = 0;
        if (counter % 5 == 0) {
            for (int i = 0; i < AlienCreeps.getDeadAlienCreeps().size(); i++) {
                AlienCreeps alienCreeps = AlienCreeps.getDeadAlienCreeps().get(i);
                if (alienCreeps.getDeadLevel() == 8) {
                    i--;
                    rootOfMianScene.getChildren().remove(alienCreeps.getImageView());
                    AlienCreeps.getDeadAlienCreeps().remove(alienCreeps);

                } else if (alienCreeps.getDeadLevel() <= 3) {
                    alienCreeps.setDeadLevel(alienCreeps.getDeadLevel() + 1);
                    if (alienCreeps.getDeadLevel() == 3) {
                        removeOrNo = true;
                    }
                    switch (alienCreeps.getAlienCreepTypes()) {
                        case Algwasonion:
                            alienCreeps.getImageView().setImage(algwasonionImages.getDeadImages()[alienCreeps.getDeadLevel()]);
                            break;
                        case Albertonion:
                            alienCreeps.getImageView().setImage(albertonionImages.getDeadImages()[alienCreeps.getDeadLevel()]);
                            break;
                        case Activionion:
                            alienCreeps.getImageView().setImage(activinionImages.getDeadImages()[alienCreeps.getDeadLevel()]);
                            break;
                        case Aironion:
                            alienCreeps.getImageView().setImage(null);
                            break;
                    }
                } else {
                    alienCreeps.setDeadLevel(alienCreeps.getDeadLevel() + 1);
                }

            }

        }
    }

    private void moveOfAliens(Group rootOfMainScene, int counter) {
        try {
            outer:
            for (int i = 0; i < AlienCreeps.getAllAlienCreeps().size(); i++) {
                AlienCreeps alienCreeps = AlienCreeps.getAllAlienCreeps().get(i);
                if (alienCreeps.isFiring() == true) {
                    continue;
                }
                alienCreeps.setCounterForMove(alienCreeps.getCounterForMove() + 1);
                if (alienCreeps.getCounterForMove() % (60 / alienCreeps.getAlienCreepTypes().getSpeed()) == 0) {
                    // System.out.println(alienCreeps.getAlienCreepTypes().toString() + " counter = " + counter);
                    if (alienCreeps.getCurrentHomeNo() == alienCreeps.getPath().getThisPathHomes().size() - 1) {
                        Engine.getInstance().getPlayer().setFlag(Engine.getInstance().getPlayer().getFlag() + 1);
                        if (Engine.getInstance().getPlayer().getFlag() == 5) {
                            Engine.getInstance().getPlayer().setFlag(0);
                            AlienCreeps.getAllAlienCreeps().clear();
                            counter = 0;
                            MainScene.mediaPlayer.stop();
                            rootOfMainScene.getChildren().clear();
                            stage.close();
                            ((MainScene) stage.getScene()).setPauseState(true);
                            Stage gameOverStage = new Stage();
                            Scene gameOverScene = new Scene(new Group());
                            Group root = (Group) gameOverScene.getRoot();
                            Label label = new Label("Game Over");
                            Button returnToMainMenuButton = new Button("return to main menu");
                            Button exitButton = new Button("Exit");
                            gameOverStage.setScene(gameOverScene);
                            root.getChildren().addAll(label, returnToMainMenuButton, exitButton);
                            exitButton.setOnMouseClicked(event -> {
                                gameOverStage.close();
                            });
                            VBox vBox = new VBox();
                            vBox.getChildren().addAll(label, returnToMainMenuButton, exitButton);
                            Group rootOfGameOverScene = (Group) gameOverScene.getRoot();
                            gameOverStage.setScene(gameOverScene);
                            rootOfGameOverScene.getChildren().add(vBox);
                            gameOverStage.show();
                            returnToMainMenuButton.setOnMouseClicked(event -> {
                                stage = new Stage();
                                stage.setScene(new StartScene(new Group(), 1280, 960, Color.BLUE, stage));
                                stage.show();
                                gameOverStage.close();
                            });
                            break outer;
                        }
                        AlienCreeps.getAllAlienCreeps().remove(alienCreeps);
                        rootOfMainScene.getChildren().remove(alienCreeps.getImageView());
                        i--;
                        continue outer;
                    }

                    if (alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo() + 1)[1] == alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[1]) {
                        if (alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[0] > alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo() + 1)[0]) {
                            alienCreeps.getCoordinates()[0] = alienCreeps.getCoordinates()[0] - 1;
                        } else if (alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[0] < alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo() + 1)[0]) {
                            alienCreeps.getCoordinates()[0] = alienCreeps.getCoordinates()[0] + 1;
                        }
                        alienCreeps.setMoved32Pixel(alienCreeps.getMoved32Pixel() + 1);
                        if (alienCreeps.getMoved32Pixel() == 32) {
                            alienCreeps.setCurrentHomeNo(alienCreeps.getCurrentHomeNo() + 1);
                            alienCreeps.setMoved32Pixel(0);
                        }
                    }

                    if (alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo() + 1)[0] == alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[0]) {
                        if (alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[1] > alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo() + 1)[1]) {
                            alienCreeps.getCoordinates()[1] = alienCreeps.getCoordinates()[1] - 1;
                        } else if (alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[1] < alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo() + 1)[1]) {
                            alienCreeps.getCoordinates()[1] = alienCreeps.getCoordinates()[1] + 1;
                        }
                        alienCreeps.setMoved32Pixel(alienCreeps.getMoved32Pixel() + 1);
                        if (alienCreeps.getMoved32Pixel() == 32) {
                            alienCreeps.setCurrentHomeNo(alienCreeps.getCurrentHomeNo() + 1);
                            alienCreeps.setMoved32Pixel(0);
                        }
                    }

                    ImageView imageView = null;
                    rootOfMainScene.getChildren().remove(alienCreeps.getImageView());
                    switch (alienCreeps.getAlienCreepTypes()) {
                        case Aironion:
                            alienCreeps.setImageView(new ImageView(aironionImages.getNonFiringImages()[0]));
                            break;
                        case Activionion:
                            alienCreeps.setImageView(new ImageView(activinionImages.getMoveForwardImages()[0]));
                            break;
                        case Albertonion:
                            alienCreeps.setImageView(new ImageView(albertonionImages.getMoveForwardImages()[0]));
                            break;
                        case Algwasonion:
                            alienCreeps.setImageView(new ImageView(algwasonionImages.getMoveForwardImages()[0]));
                            break;
                    }
                    alienCreeps.getImageView().relocate(alienCreeps.getCoordinates()[0], alienCreeps.getCoordinates()[1]);
                    rootOfMainScene.getChildren().add(alienCreeps.getImageView());
                }
            }
        } catch (Exception e) {

        }
    }

    private void manageEndPointForWormHoles() {
        for (MovableFirings movableFirings : MovableFirings.getAllMovableFirings()) {
            for (WormHole wormHole : WormHole.getWormHoles()) {
                if (crossedWormHole != wormHole || (crossedWormHole == wormHole)) {
                    if (movableFirings instanceof Hero) {
                        Hero hero = ((MainScene) stage.getScene()).hero;
                        int xHero = hero.getCoordinates()[0];
                        int yHero = hero.getCoordinates()[1];
                        int xWorm = wormHole.getEndCoordinates()[0];
                        int yWorm = wormHole.getEndCoordinates()[1];
                        if ((xWorm >= xHero + 0 && xWorm + 32 <= xHero + 64 && yWorm >= yHero + 0 && yWorm + 32 <= yHero + 64) || (xWorm >= xHero + 0 && xWorm + 32 <= xHero + 64 && yWorm >= yHero + 32 && yWorm + 32 <= yHero + 32 + 64)) {
                            hero.setCoordinates(new int[]{wormHole.getStartCoordinates()[0] - 35, wormHole.getStartCoordinates()[1] - 35});
                            hero.drawHero(hero.moveLeftPics, 2);
                        }
                    }

                }
            }
        }
    }

    private void manageStartingPointOfWormHoles() {
        for (MovableFirings movableFirings : MovableFirings.getAllMovableFirings()) {
            for (WormHole wormHole : WormHole.getWormHoles()) {
                if (crossedWormHole != wormHole || (crossedWormHole == wormHole)) {
                    if (movableFirings instanceof Hero) {
                        Hero hero = ((MainScene) stage.getScene()).hero;
                        int xHero = hero.getCoordinates()[0];
                        int yHero = hero.getCoordinates()[1];
                        int xWorm = wormHole.getStartCoordinates()[0];
                        int yWorm = wormHole.getStartCoordinates()[1];
                        if ((xWorm >= xHero + 0 && xWorm + 32 <= xHero + 64 && yWorm >= yHero + 0 && yWorm + 32 <= yHero + 64) || (xWorm >= xHero + 0 && xWorm + 32 <= xHero + 64 && yWorm >= yHero + 32 && yWorm + 32 <= yHero + 32 + 64)) {
                            hero.setCoordinates(new int[]{wormHole.getEndCoordinates()[0] - 35, wormHole.getEndCoordinates()[1] - 35});
                            hero.drawHero(hero.moveLeftPics, 2);
                            crossedWormHole = wormHole;
                        }
                    }
                    if (movableFirings instanceof AlienCreeps) {
                        AlienCreeps alienCreeps = (AlienCreeps) movableFirings;
                        if (alienCreeps.getAlienCreepTypes().name().equals("Aironion")) {
                            continue;
                        }
                        int xAlienCreep = alienCreeps.getCoordinates()[0];
                        int yAlienCreep = alienCreeps.getCoordinates()[1];
                        int xWorm = wormHole.getStartCoordinates()[0];
                        int yWorm = wormHole.getStartCoordinates()[1];
                        if (xWorm == xAlienCreep && yWorm == yAlienCreep) {
                            //for (int[] ints : alienCreeps.getPath().getThisPathHomes()) {
                            for (int i = 0; i < alienCreeps.getPath().getThisPathHomes().size(); i++) {
                                int[] ints = alienCreeps.getPath().getThisPathHomes().get(i);
                                if (ints[0] == wormHole.getEndCoordinates()[0] && ints[1] == wormHole.getEndCoordinates()[1]) {
                                    alienCreeps.setCurrentHomeNo(i + 1);
                                    int endX = alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[0];
                                    int endY = alienCreeps.getPath().getThisPathHomes().get(alienCreeps.getCurrentHomeNo())[1];
                                    alienCreeps.setCoordinates(new int[]{endX, endY});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void makeAlienCreeps() {
        Random random = new Random();
        AlienCreeps alienCreep;
        int randomNumOfAliens = random.nextInt(4) + 1;
        for (int i = 0; i < randomNumOfAliens; i++) {
            int randomNumber = random.nextInt(4) + 1;
            switch (randomNumber) {
                case 1:
                    alienCreep = new AlienCreeps(Activionion);
                    break;
                case 2:
                    alienCreep = new AlienCreeps(Algwasonion);
                    break;
                case 3:
                    alienCreep = new AlienCreeps(Aironion);
                    break;
                case 4:
                    alienCreep = new AlienCreeps(Albertonion);
                    break;
            }
        }

    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public Stage stage = new Stage();
    public Scene startMenu = new Scene(new Group(), 1280, 960, Color.BLUE);
    public Scene gameScene = new Scene(new Group(), 1280, 960, Color.GRAY);

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        StartScene startScene = new StartScene(new Group(), 1280, 960, Color.BLUE, stage);
        stage.setScene(startScene);
        stage.setTitle("Alien Creeps");
        stage.show();
        animationTimer.start();
    }


}
