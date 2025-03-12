package Integrated_Nursery;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Plant simulates a plant within a nursery. Each plant has an:
 * <ul>
 *      <li>ID
 *      <li>Genus Name
 *      <li>Common Name
 *      <li>Introduction Date
 *      <li>A List of Good Climate Zones
 *      <li>The Plant's grouping
 * </ul>
 * 
 * Also inside the class is a HashMap of predicates, which allow the user to select a way to evaluate a Plant (Used in the {@link NurseryDriver}).
 * 
 * @author Andrew Casey, Saadat Emilbekova, Jason Mele
 * @version 3/13/2025
 */
public class Plant {
    private long id;
    private String genusSpecies, commonName;
    private LocalDate dateIntroduced;
    private HashMap<Integer, Zone> zones = new HashMap<>();

    /**Set & used in {@link NurseryDriver} to determine which Plant has the most and least experience.*/
    public static Plant mostExperienced, leastExperienced;
    public static long globalID = 4901;

    public static enum plantGroup {
        Angiosperm, Gymnosperm, Pteridophyte, Bryophyte
    }

    /**
     * Contains predicate lambdas on how to evaluate each Plant in the {@link NurseryDriver}
     */
    public static HashMap<String, Predicate<Plant>> sorter;
    static {
        sorter = new HashMap<>();
        sorter.put("most_experienced", plant -> plant.equals(mostExperienced)); //Returns true if the Plant is the oldest
        sorter.put("least_experienced",  plant -> plant.equals(leastExperienced)); //Returns true if the Plant is the newest
        sorter.put("this_year", plant -> plant.getDateIntroduced().getYear() == LocalDate.now().getYear()); //Returns true if the Plant was made in the current year
        sorter.put("older_than_this_year", plant -> plant.getDateIntroduced().getYear() < LocalDate.now().getYear()); //Returns true if the Plant was NOT made in the current year
    }
 
    /**
     * Constructor for Plant. Checks if the names are valid and then sets them.
     * 
     * @param genusSpecies genus name of the plant
     * @param commonName common name of the plant
     * @param dateIntroducted date the plant was introduced to the nursery
     * @param zoneNumbers int[] of all of the plants acceptable zones
     * @param group the plant's group
     */
    public Plant(String genusSpecies, String commonName, LocalDate dateIntroducted, int[] zoneNumbers, plantGroup group) {
        if(validateNames(genusSpecies, commonName)) {
            this.genusSpecies = genusSpecies;
            this.commonName = commonName;
            this.dateIntroduced = dateIntroducted;
            this.id = ++globalID;

            for(int zoneNumber : zoneNumbers)
                this.zones.put(zoneNumber, Zone.setZone.get(zoneNumber));
        }
        else {
            System.out.printf("Cannot Create Plant: %s\n", commonName);
        }
    }

    /**
     * Determines if the the given zoneNumber if within the plant's acceptable zone
     * 
     * @param zoneNumber A zone number from the Arnold Arboretum map
     * @return True/False if the zone number is in the Plant's zone
     */
    public boolean growsInZone(int zoneNumber) {
        return zones.containsKey(zoneNumber);
    }

    /**
     * Checks if both genus and common names are valid.
     * Names are valid if:
     * <ol>
     *      <li>The first letter in genus is capitalized and the rest are lower case.</li>
     *      <li>The first letter in common is capitalized (the rest can be capitalized).</li>
     *      <li>The total length of genus and common combined is <= 7 and >= 39.</li> 
     * </ol>
     * 
     * @param genus plant genus name to check
     * @param common plant common name to check
     * @return TRUE if both names are valid; FALSE otherwise
     */
    public static boolean validateNames(String genus, String common) {
        String[] genusSplit = genus.split("\\p{Lu}");
        char commonFirst = common.charAt(0);
        int totalLength = genus.length() + common.length();

        if(genusSplit.length != 2) {
            System.err.println("Multiple Capital Letters in Genus; Only the First Character Should be Capitalized");
            return false;
        }

        if(!Character.isUpperCase(commonFirst)) {
            System.err.println("First Character in Common is not Capitalized");
            return false;
        }

        if(totalLength <= 7 || totalLength >= 39) {
            System.err.println("Total Length of Common and Genus has to between 7 & 39 Characters");
            return false;
        }

        return true;
    }

    public long getId() {
        return this.id;
    }

    public String getGenusSpecies() {
        return this.genusSpecies;
    }

    public void setGenusSpecies(String genusSpecies) {
        this.genusSpecies = genusSpecies;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public LocalDate getDateIntroduced() {
        return this.dateIntroduced;
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    public HashMap<Integer,Zone> getZones() {
        return this.zones;
    }

    public void setZones(HashMap<Integer,Zone> zones) {
        this.zones = zones;
    }

    @Override
    public String toString() {
        return commonName + " (" + genusSpecies + ")";
    }
}