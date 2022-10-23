package Week2.Farm.Farmer;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

class Cow extends BaseAnimal {

    Cow(Gender gender, int id, float weight) {
        super(gender, id, weight);
    }

    @Override
    public void move() {
        System.out.println("Moo Moo, Cow is moving");
    }

    @Override
    public Animal mate(Animal partner) {
        if (this.getGender() != partner.getGender() && this.getClass().equals(partner.getClass())) {
            return Cow.createRandomCow();
        }
        else {
            throw new RuntimeException("Animals Can't Breed");
        }
    }

    static Animal createRandomCow() {
        return new Cow(Gender.values()[ThreadLocalRandom.current().nextInt(Gender.values().length)], Math.abs(UUID.randomUUID().hashCode()), ThreadLocalRandom.current().nextFloat()*50);
    }

    static Animal createRandomCowWithGender(Gender gender) {
        return new Cow(gender, Math.abs(UUID.randomUUID().hashCode()), ThreadLocalRandom.current().nextFloat()*50);
    }
}

