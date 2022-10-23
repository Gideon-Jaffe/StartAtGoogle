package Week2.Farm.Farmer;

public class AnimalForwarding implements Animal {

    Animal myAnimal;

    public AnimalForwarding(Animal myAnimal) {
        this.myAnimal = myAnimal;
    }

    @Override
    public void move() {
        myAnimal.move();
    }

    @Override
    public Gender getGender() {
        return myAnimal.getGender();
    }

    @Override
    public float getWeight() {
        return myAnimal.getWeight();
    }

    @Override
    public void setWeight(float newWeight) {
        myAnimal.setWeight(newWeight);
    }

    @Override
    public Animal mate(Animal partner) {
        return myAnimal.mate(partner);
    }
}

