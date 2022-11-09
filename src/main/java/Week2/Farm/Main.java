package Week2.Farm;

import Week2.Farm.Farmer.*;
import Week2.Farm.Farmer.Wooden.WoodenHorse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static Logger log = LogManager.getLogger(Week2.Farm.Main.class.getName());
    public static void main(String[] args) {
        log.info("Starting functionality test");
        checkFarmFunctionality();
        log.info("Starting forwarding test");
        checkForwarding();
        //checkAdapter();
    }

    public static void checkFarmFunctionality() {
        Farmer myFarmer = new Farmer();
        myFarmer.acquire(AnimalTypes.Cow, Gender.Female);
        myFarmer.acquire(AnimalTypes.Sheep, Gender.Male);
        myFarmer.acquire(AnimalTypes.Horse, Gender.Female);
        myFarmer.acquire(AnimalTypes.Horse, Gender.Male);
        myFarmer.requestAnimal(AnimalTypes.Cow);
        System.out.println("Moving Sheep");
        myFarmer.moveAnimal(AnimalTypes.Sheep);
        myFarmer.mate(AnimalTypes.Horse);
        System.out.println("Printing Farm Animals");
        System.out.println(myFarmer);
        myFarmer.requestAnimal(AnimalTypes.Sheep);
        System.out.println("Printing Farm Details after removing a sheep");
        System.out.println(myFarmer);

    }

    public static void checkForwarding() {
        Farmer myFarmer = new Farmer();
        myFarmer.acquireRandomForwardingAnimal(AnimalTypes.Horse, Gender.Male);
        for (int i = 0; i < 5; i++) {
            myFarmer.moveAnimal(AnimalTypes.Horse);
        }
    }

    public static void checkAdapter() {
        WoodenStructureAdapter structure = new WoodenStructureAdapter(new WoodenHorse(5.5f));

        structure.move();
    }
}
