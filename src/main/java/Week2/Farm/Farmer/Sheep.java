package Week2.Farm.Farmer;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

class Sheep extends BaseAnimal {

    Sheep(Gender gender, int id, float weight) {
        super(gender, id, weight);
    }

    @Override
    public void move() {
        System.out.println("baaaa, Sheep is moving");
    }

    @Override
    public Animal mate(Animal partner) {
        if (this.getGender() != partner.getGender() && this.getClass().equals(partner.getClass())) {
            return createRandomSheep();

        } else {
            return null;
        }
    }

    static Animal createRandomSheep() {
        return new Sheep(Gender.values()[ThreadLocalRandom.current().nextInt(Gender.values().length)], UUID.randomUUID().hashCode(), ThreadLocalRandom.current().nextFloat()*50);
    }

    static Animal createRandomSheepWithGender(Gender gender) {
        return new Sheep(gender, UUID.randomUUID().hashCode(), ThreadLocalRandom.current().nextFloat()*50);
    }
}

