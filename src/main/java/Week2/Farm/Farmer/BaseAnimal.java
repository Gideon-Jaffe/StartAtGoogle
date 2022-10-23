package Week2.Farm.Farmer;

abstract class BaseAnimal implements Animal {
    private final Gender gender;

    private final int id;

    private float weight;

    BaseAnimal(Gender gender, int id, float weight) {
        this.gender = gender;
        this.id = id;
        this.weight = weight;
    }

    @Override
    public void move() {
        System.out.println("I am animal moving");
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public void setWeight(float newWeight) {
        this.weight = newWeight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " id: " + this.id +
                " gender: " + this.gender +
                " weight: " + this.weight;
    }

    @Override
    public Gender getGender() {
        return gender;
    }
}
