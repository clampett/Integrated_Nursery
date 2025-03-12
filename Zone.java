package Integrated_Nursery;

import java.util.HashMap;

/**
 * Zone represents the different regions of the earth divided by temperatures to which different plants grow best.
 * Each Zone object has a lower and upper temperature, decided by its zone number.
 * 
 * <p>Regions come from the Arnold Arboretum map.</p>
 * 
 * @author Andrew Casey, Saadat Emilbekova, Jason Mele
 * @version 3/13/2025
 */
public class Zone {
    public int lowerTemp, upperTemp;

    /**
     * HashMap to set the zones in for the {@link Plant} class and it's derivatives.
     */
    public static final HashMap<Integer, Zone> setZone = new HashMap<>();
    static{
        setZone.put(1, new Zone(1));
        setZone.put(2, new Zone(2));
        setZone.put(3, new Zone(3));
        setZone.put(4, new Zone(4));
        setZone.put(5, new Zone(5));
        setZone.put(6, new Zone(6));
        setZone.put(7, new Zone(7));
        setZone.put(8, new Zone(8));
        setZone.put(9, new Zone(9));
        setZone.put(10, new Zone(10));
        setZone.put(11, new Zone(11));
        setZone.put(-1, null);
    }

    /**
     * Constructor for {@link Zone}. Private, so only used for the setZone HashMap.
     * @param zoneNumber A zone number from the Arnold Arboretum map
     */
    private Zone(int zoneNumber) {
        switch(zoneNumber) {
            case 1:
                lowerTemp = Integer.MIN_VALUE;
                upperTemp = -50;
                break;
            case 2:
                lowerTemp = -50;
                upperTemp = -40;
                break;
            case 3:
                lowerTemp = -40;
                upperTemp = -30;
                break;
            case 4:
                lowerTemp = -30;
                upperTemp = -20;
                break;
            case 5:
                lowerTemp = -20;
                upperTemp = -10;
                break;
            case 6:
                lowerTemp = -10;
                upperTemp = 0;
                break;
            case 7:
                lowerTemp = 0;
                upperTemp = 10;
                break;
            case 8:
                lowerTemp = 10;
                upperTemp = 20;
                break;
            case 9:
                lowerTemp = 20;
                upperTemp = 30;
                break;
            case 10:
                lowerTemp = 30;
                upperTemp = 40;
                break;
            case 11:
                lowerTemp = 40;
                upperTemp = 50;
                break;
            default:
                System.err.println("Invalid Zone Number");
        }
    }
}