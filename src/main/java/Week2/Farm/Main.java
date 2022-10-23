package Week2.Farm;

import Week2.Farm.Farmer.*;

public class Main {
    public static void main(String[] args) {
        Farmer myFarmer = new Farmer();
        myFarmer.acquire(AnimalTypes.Cow, Animal.Gender.Female);
        myFarmer.acquire(AnimalTypes.Sheep, Animal.Gender.Male);
        myFarmer.acquire(AnimalTypes.Horse, Animal.Gender.Female);
        myFarmer.acquire(AnimalTypes.Horse, Animal.Gender.Male);
        myFarmer.requestAnimal(AnimalTypes.Cow);
        myFarmer.moveAnimal(AnimalTypes.Sheep);
        myFarmer.mate(AnimalTypes.Horse);
        System.out.println(myFarmer);
        myFarmer.requestAnimal(AnimalTypes.Cow);


    }

    public static void checkForwarding() {

    }
}
