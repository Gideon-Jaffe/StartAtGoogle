package Week2.Farm.Farmer;

public interface Animal {
    void move();

    Gender getGender();

    float getWeight();

    void setWeight(float newWeight);
    Animal mate(Animal partner);
}

