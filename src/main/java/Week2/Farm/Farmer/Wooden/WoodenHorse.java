package Week2.Farm.Farmer.Wooden;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class WoodenHorse implements WoodenStructures {

    private final int id;
    private float weight;

    public int getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public WoodenHorse(float weight) {
        this.weight = weight;
        this.id = UUID.randomUUID().hashCode();
    }

    @Override
    public void roll() {
        System.out.println("Rolling horse incoming!!!");
    }

    @Override
    public WoodenStructures replicate() {
        return new WoodenHorse(ThreadLocalRandom.current().nextFloat()*50);
    }
}

