package gameLogic;

import gameLogic.firings.movableFirings.Hero;
import gameLogic.firings.movableFirings.alienCreeps.AlienCreeps;

import java.util.ArrayList;

public class Engine {
    private Player player;
    private static ArrayList<AlienCreeps> alienCreepsKilledByHero = new ArrayList<>();
    private static ArrayList<AlienCreeps> alienCreepsKilledByWeapons = new ArrayList<>();
    private static Engine engine = new Engine(new Player());
    public  Hero hero;

    public Engine(Player player) {
        this.player = player;
    }

    public static Engine getInstance() {
        return engine;
    }

    public Player getPlayer() {
        return player;
    }

    public static ArrayList<AlienCreeps> getAlienCreepsKilledByHero() {
        return alienCreepsKilledByHero;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void setAlienCreepsKilledByHero(ArrayList<AlienCreeps> alienCreepsKilledByHero) {
        Engine.alienCreepsKilledByHero = alienCreepsKilledByHero;
    }

    public static ArrayList<AlienCreeps> getAlienCreepsKilledByWeapons() {
        return alienCreepsKilledByWeapons;
    }

    public static void setAlienCreepsKilledByWeapons(ArrayList<AlienCreeps> alienCreepsKilledByWeapons) {
        Engine.alienCreepsKilledByWeapons = alienCreepsKilledByWeapons;
    }

    public static Engine getEngine() {
        return engine;
    }

    public static void setEngine(Engine engine) {
        Engine.engine = engine;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
