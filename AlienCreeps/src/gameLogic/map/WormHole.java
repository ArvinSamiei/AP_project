package gameLogic.map;

import java.util.ArrayList;

public class WormHole {
    private static ArrayList<WormHole> wormHoles = new ArrayList<>();
    private int[] startCoordinates = new int[2];
    private int[] endCoordinates = new int[2];

    static {
        makeWormHoles();
    }

    private static void makeWormHoles() {
        WormHole wormHoleSample = new WormHole();
        wormHoleSample.startCoordinates[0] = 8 * 32;
        wormHoleSample.startCoordinates[1] = 22 * 32;
        wormHoleSample.endCoordinates[0] = 12 * 32;
        wormHoleSample.endCoordinates[1] = 14 * 32;
        wormHoles.add(wormHoleSample);
        wormHoleSample = new WormHole();
        wormHoleSample.startCoordinates = new int[]{22 * 32, 17 * 32};
        wormHoleSample.endCoordinates = new int[]{25 * 32, 27 * 32};
        wormHoles.add(wormHoleSample);
        wormHoleSample = new WormHole();
        wormHoleSample.startCoordinates = new int[]{10 * 32, 7 * 32};
        wormHoleSample.endCoordinates = new int[]{28 * 32, 7 * 32};
        wormHoles.add(wormHoleSample);
        wormHoleSample = new WormHole();
        wormHoleSample.startCoordinates = new int[]{33 * 32, 12 * 32};
        wormHoleSample.endCoordinates = new int[]{30 * 32, 20 * 32};
        wormHoles.add(wormHoleSample);
    }

    public static ArrayList<WormHole> getWormHoles() {
        return wormHoles;
    }

    public int[] getStartCoordinates() {
        return startCoordinates;
    }

    public int[] getEndCoordinates() {
        return endCoordinates;
    }
}
