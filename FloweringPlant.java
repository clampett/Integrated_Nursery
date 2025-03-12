package Integrated_Nursery;

import java.time.LocalDate;

/**
 * 
 * @author Andrew Casey, Saadat Emilbekova, Jason Mele
 * @version 3/13/2025
 */
public class FloweringPlant extends Plant {
    private String flowerColors, features;

    public FloweringPlant(String genusSpecies, String commonName, LocalDate dateIntroducted, int[] zoneNumbers, String flowersColors, String features, plantGroup group) {
        super(genusSpecies, commonName, dateIntroducted, zoneNumbers, group);
        this.flowerColors = flowersColors;
        this.features = features;
    }

    public String getFlowersColors() {
        return this.flowerColors;
    }

    public void setFlowersColors(String flowersColors) {
        this.flowerColors = flowersColors;
    }

    public String getFeatures() {
        return this.features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

}
