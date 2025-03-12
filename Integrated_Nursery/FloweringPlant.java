package Integrated_Nursery;

import java.time.LocalDate;

/**
 * FloweringPlant simulates a flowering plant in a nursery. It is a subclass of the {@link Plant} class. 
 * Along with {@link Plant} variables, FloweringPlant has flowerColors and features.
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
