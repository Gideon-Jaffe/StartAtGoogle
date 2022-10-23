package Week2.Farm.Farmer;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

class Horse extends BaseAnimal {

    Horse(Gender gender, int id, float weight) {
        super(gender, id, weight);
    }

    @Override
    public void move() {
        System.out.println("neigh, Horse is moving");
    }

    @Override
    public Animal mate(Animal partner) {
        if (this.getGender() != partner.getGender() && this.getClass().equals(partner.getClass())) {
            return createRandomHorse();
        } else {
            return null;
        }
    }

    static Animal createRandomHorse() {
        return new Horse(Gender.values()[ThreadLocalRandom.current().nextInt(Gender.values().length)], Math.abs(UUID.randomUUID().hashCode()), ThreadLocalRandom.current().nextFloat()*50);
    }

    static Animal createRandomHorseWithGender(Gender gender) {
        return new Horse(gender, Math.abs(UUID.randomUUID().hashCode()), ThreadLocalRandom.current().nextFloat()*50);
    }
}
