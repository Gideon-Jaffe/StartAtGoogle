package Week2.Farm;

import Week2.Farm.Farmer.*;

public class Main {
    public static void main(String[] args) {
        checkFarmFunctionality();
        checkForwarding();
    }

    public static void checkFarmFunctionality() {
        Farmer myFarmer = new Farmer();
        myFarmer.acquire(AnimalTypes.Cow, Animal.Gender.Female);
        myFarmer.acquire(AnimalTypes.Sheep, Animal.Gender.Male);
        myFarmer.acquire(AnimalTypes.Horse, Animal.Gender.Female);
        myFarmer.acquire(AnimalTypes.Horse, Animal.Gender.Male);
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
        myFarmer.acquireRandomForwardingAnimal(AnimalTypes.Horse, Animal.Gender.Male);
        for (int i = 0; i < 5; i++) {
            myFarmer.moveAnimal(AnimalTypes.Horse);
        }
    }
}
