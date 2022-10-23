package Week2.Farm.Farmer;

public class Farmer {
    private final Farm farm;

    public Animal requestAnimal(AnimalTypes typeToCreate) {
        return farm.requestAnimal(typeToCreate);
    }

    public Farmer() {
        farm = new Farm();
    }

    public void moveAnimal(Animal animal) {
        animal.move();
    }

    public void moveAnimal(AnimalTypes animalType) {
        farm.getAnimal(animalType).move();
    }

    @Override
    public String toString() {
        return "Farmer Is Awesome:\n" + farm;
    }

    public Animal acquire(AnimalTypes animalClass, Gender gender) {
        return farm.acquire(animalClass, gender);
    }

    public Animal acquireRandomForwardingAnimal(AnimalTypes animalClass, Gender gender) {
        return farm.acquireMoveCountingAnimal(animalClass, gender);
    }

    public void mate(AnimalTypes animalClass) {
        farm.mate(animalClass);
    }
}
