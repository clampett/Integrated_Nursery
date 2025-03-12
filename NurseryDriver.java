package Integrated_Nursery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Driver for the Integrated Nursery. The driver simulates a plant nursery, allowing the user to add a new plant and evaluate existing plants based on some criteria.
 * Specifically, the driver does:
 * <ol>
 *      <li>Creates the already existing Plants in the nursery (2 {@link Tree} & 2 {@link FloweringPlant})
 *      <li>Allows the user to input the specifications of their new {@link Plant} and how to evaluate the plants in the nursery
 *      <li>Creates the new {@link Plant} and sets how to evaluate
 *      <li>Loops through each {@link Plant} and prints each one's relevant information 
 * </ol> 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Jason Mele
 * @version 3/13/2025
 */
public class NurseryDriver {
    private static ArrayList<Plant> plants = new ArrayList<>();
    private static int zone;
    private static Predicate<Plant> evaluatorType;
    private static String evaluator;

    public static void main(String[] args) {
        createPlants();
        createUserPlant();
        setEvaluator();
        loopPlants();
    }

    /**
     * Creates the hardcoded {@link Tree} and {@link FloweringPlant} objects from the Assignment page and adds them to the plants list.
     */
    private static void createPlants() {
        plants.add(new Tree("Acer palmatum", "Bloodgood Japanese Maple", LocalDate.parse("2016-01-02"), new int[]{6,7,8}, "fast", Plant.plantGroup.Gymnosperm));
        plants.add(new Tree("Tsuga canadensi", "Hemlock Tree", LocalDate.parse("2003-01-23"), new int[]{3,4,5,6,7}, "slow", Plant.plantGroup.Gymnosperm));
        plants.add(new FloweringPlant("Murraya paniculata", "Orange Jasmine", LocalDate.parse("2007-01-23"), new int[]{9}, "white", "orange-scented", Plant.plantGroup.Angiosperm));
        plants.add(new FloweringPlant("Convallaria majalis", "Lily-of-the-Valley", LocalDate.parse("2011-10-05"), new int[]{5,6,7,8}, "white, pink, or red", "delicate, fragile", Plant.plantGroup.Angiosperm));
    }

    /**
     * Gets the user's input to create a new {@link Plant} object and to select how the plants ArrayList should be evaluated.
     */
    private static void createUserPlant() {
        Scanner sc = new Scanner(System.in);
        String genus, common, date;
        String validDatePattern = "^\\d{4}-\\d{2}-\\d{2}$";

        do {
            System.out.println("\nWhat zone are you currently in?");
    
            try {
                zone = sc.nextInt();
            } catch (Exception e) {
                System.err.println("ERROR - Numbers ONLY.");
                sc.next();
                zone = -1;
            }

            if(zone < 1 || zone > 11)
                System.err.println("ERROR - Zone must be between 1 and 11.");
    
        } while(zone < 1 || zone > 11);

        sc.nextLine(); //clears buffer

        do {            
            System.out.println("\nHow should we evalute nursery experience with plant? [Enter 'least' or 'most' or 'year' or 'older']");
            evaluator = sc.nextLine();

            if(!evaluator.equals("most") && !evaluator.equals("least") && !evaluator.equals("year") && !evaluator.equals("older"))
                System.err.println("ERROR - Incorrect Input. Must be: 'least' or 'most' or 'year' or 'older'.");

        } while(!evaluator.equals("most") && !evaluator.equals("least") && !evaluator.equals("year") && !evaluator.equals("older"));

        do {
            System.out.println("\nEnter the common name of the plant");
            common = sc.nextLine();
            
            System.out.println("\nEnter the scientific name of the plant (make one up!)");
            genus = sc.nextLine();            
        } while(!Plant.validateNames(genus, common));

        do {
            System.out.println("\nEnter when the plant was first introduced to the nursery [YYYY-MM-DD]");
            date = sc.nextLine();   
            
            if(!date.matches(validDatePattern))
                System.err.println("ERROR - date MUST follow [YYYY-MM-DD]");

            try {
                LocalDate.parse(date);
            } catch(Exception e) {
                System.err.println("ERROR - Cannot parse date.");
            }

        } while(!date.matches(validDatePattern));


        plants.add(new Plant(genus, common, LocalDate.parse(date), new int[]{-1}, null));
        sc.close();
    }

    /**
     * Sets which evaulation predicate should be run based on user input from createUserPlant().
     */
    private static void setEvaluator() {
        if(evaluator.equals("most")) {
            determineExperience(evaluator);
            evaluatorType = Plant.sorter.get("most_experienced");
        } 
        else if(evaluator.equals("least")) {
            determineExperience(evaluator);
            evaluatorType = Plant.sorter.get("least_experienced");
        }
        else if(evaluator.equals("year")) {
            evaluatorType = Plant.sorter.get("this_year");
        }
        else if(evaluator.equals("older")) {
            evaluatorType = Plant.sorter.get("older_than_this_year");
        }
        else { //Should never happen
            System.err.println(evaluator + " is NOT valid. Setting to default ['most'].");
            evaluatorType = Plant.sorter.get("most_experienced");        
        }        
    }

    /**
     * Finds either the most or least experienced {@link Plant} object in plants ArrayList and sets it to its corresponding public static variable in the {@link Plant} class.
     * 
     * @param evaluator decides whether to find the most or least experienced
     */
    private static void determineExperience(String evaluator) {
        if(evaluator.equals("most")) {
            Plant most = plants.get(0);

            for(int i = 1; i < plants.size() ; i++)
                if(plants.get(i).getDateIntroduced().isBefore(most.getDateIntroduced()))
                    most = plants.get(i);

            Plant.mostExperienced = most;
        }
        else if(evaluator.equals("least")) {
            Plant least = plants.get(0);

            for(int i = 1; i < plants.size(); i++)
                if(plants.get(i).getDateIntroduced().isAfter(least.getDateIntroduced()))
                    least = plants.get(i);

            Plant.leastExperienced = least;
        }
        else //Should never happen
            System.err.println(evaluator + " is NOT correct input.");
    }

    /**
     * Loops through all of the {@link Plant} objects in the plants ArrayList.
     * 
     * <p>Prints each plant's:
     * <ul>
     *      <li>ID</li>
     *      <li>Name</li>
     *      <li>Type</li>
     *      <li>Introduction Date</li>
     *      <li>Special Characteristics if it is a {@link Tree} or {@link FloweringPlant}</li>
     *      <li>It's Evaluation</li>
     *      <li>Whether it is good for the chosen zone</li>
     * </ul>
     */
    private static void loopPlants() {
        System.out.println("\n\n\n--------------- Results -------------");

        for(Plant plant : plants) {
            System.out.println(plant.getId());
            System.out.println(plant);
            System.out.println(plant.getClass().getSimpleName());
            System.out.println("introduced on " + plant.getDateIntroduced());

            if(plant instanceof Tree) {
                System.out.println("a " + ((Tree)plant).getGrowingSpeed() + " growing tree");
            }

            if(plant instanceof FloweringPlant) {
                System.out.println(((FloweringPlant)plant).getFeatures() + " with " + ((FloweringPlant)plant).getFlowersColors());
            }

            System.out.println(evaluator + " experience: " + evaluatorType.test(plant));
            System.out.println("good for your zone: " + plant.growsInZone(zone) + "\n");
        }
    }
}