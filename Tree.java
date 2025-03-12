package Integrated_Nursery;

import java.time.LocalDate;

/**
 * 
 * @author Andrew Casey, Saadat Emilbekova, Jason Mele
 * @version 3/13/2025
 */
public class Tree extends Plant {
    private String growingSpeed;

    public Tree(String genusSpecies, String commonName, LocalDate dateIntroducted, int[] zoneNumbers, String growingSpeed, plantGroup group) {
        super(genusSpecies, commonName, dateIntroducted, zoneNumbers, group);
        this.growingSpeed = growingSpeed;
    }

    public String getGrowingSpeed() {
        return this.growingSpeed;
    }

    public void setGrowingSpeed(String growingSpeed) {
        this.growingSpeed = growingSpeed;
    }

}
