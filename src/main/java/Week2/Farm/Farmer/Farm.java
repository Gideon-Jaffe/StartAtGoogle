package Week2.Farm.Farmer;

import java.util.ArrayList;
import java.util.List;

class Farm {
    private final List<Animal> animals;

    public Farm() {
        animals = new ArrayList<>();
    }

    public Animal acquire (AnimalTypes typeToCreate, Animal.Gender gender) {
        Animal acquiredAnimal = null;
        switch (typeToCreate) {
            case Cow:
                acquiredAnimal = Cow.createRandomCowWithGender(gender);
                break;
            case Sheep:
                acquiredAnimal = Sheep.createRandomSheepWithGender(gender);
                break;
            case Horse:
                acquiredAnimal = Horse.createRandomHorseWithGender(gender);
                break;
            default:
                throw new RuntimeException("Not supported animal type");
        }
        animals.add(acquiredAnimal);
        return acquiredAnimal;
    }

    public Animal acquireMoveCountingAnimal(AnimalTypes typeToCreate, Animal.Gender gender) {
        Animal acquiredAnimal = null;
        switch (typeToCreate) {
            case Cow:
                acquiredAnimal = Cow.createRandomCowWithGender(gender);
                break;
            case Sheep:
                acquiredAnimal = Sheep.createRandomSheepWithGender(gender);
                break;
            case Horse:
                acquiredAnimal = Horse.createRandomHorseWithGender(gender);
                break;
            default:
                throw new RuntimeException("Not supported animal type");
        }

        animals.add(new AnimalCountingMoves(acquiredAnimal));
        return acquiredAnimal;
    }

    public Animal requestAnimal(AnimalTypes animalType) {
        for (Animal current : animals) {
            boolean found = false;
            switch (animalType) {
                case Cow:
                    if (current instanceof Cow) found = true;
                    break;
                case Sheep:
                    if (current instanceof Sheep) found = true;
                    break;
                case Horse:
                    if (current instanceof Horse) found = true;
                    break;
            }
            if (found) {
                animals.remove(current);
                return current;
            }
        }
        return null;
    }

    public Animal mate(AnimalTypes animalType) {
        List<Animal> matingPair = new ArrayList<Animal>();
        for (Animal animal : animals) {
            switch (animalType) {
                case Cow:
                    if (animal instanceof Cow) matingPair.add(animal);
                case Sheep:
                    if (animal instanceof Sheep) matingPair.add(animal);
                case Horse:
                    if (animal instanceof Horse) matingPair.add(animal);
            }
            if (matingPair.size() >= 2) break;
        }
        if (matingPair.size() != 2) {
            return null;
        }
        return mate(matingPair.get(0), matingPair.get(1));
    }

    public Animal mate(Animal animal1, Animal animal2) {
        Animal baby = animal1.mate(animal2);
        animals.add(baby);
        return baby;
    }

    public Animal getAnimal(AnimalTypes animalType) {
        for (Animal animal : animals) {
            switch (animalType) {
                case Cow:
                    if (animal instanceof Cow || getInnerOfCountingAnimal(animal) instanceof Cow) return animal;
                case Sheep:
                    if (animal instanceof Sheep || getInnerOfCountingAnimal(animal) instanceof Sheep) return animal;
                case Horse:
                    if (animal instanceof Horse || getInnerOfCountingAnimal(animal) instanceof Horse) return animal;
            }
        }
        throw new RuntimeException("Animal not in farm");
    }

    private Animal getInnerOfCountingAnimal(Animal animal) {
        if (animal instanceof AnimalCountingMoves) return ((AnimalCountingMoves) animal).myAnimal;
        else return null;
    }

    @Override
    public String toString() {
        StringBuilder myStr = new StringBuilder("Farm animals:\n");
        for (Animal currentAnimal : animals) {
            if (currentAnimal != null) myStr.append(currentAnimal.toString()).append("\n");
        }
        return myStr.toString();
        
    }
}
