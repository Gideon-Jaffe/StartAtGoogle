package Week2.Farm.Farmer;

import Week2.Farm.Farmer.Wooden.WoodenHorse;

public class WoodenStructureAdapter implements Animal {
    WoodenHorse woodHorse;


    public WoodenStructureAdapter(WoodenHorse woodHorse) {
        this.woodHorse = woodHorse;
    }

    @Override
    public void move() {
        woodHorse.roll();
    }

    @Override
    public Gender getGender() {
        return Gender.Male;
    }

    @Override
    public float getWeight() {
        return woodHorse.getWeight();
    }

    @Override
    public void setWeight(float newWeight) {
        woodHorse.setWeight(newWeight);
    }

    @Override
    public Animal mate(Animal partner) {
        return new WoodenStructureAdapter(new WoodenHorse(5.5f));
    }
}
