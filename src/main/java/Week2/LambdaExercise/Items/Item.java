package Week2.LambdaExercise.Items;

import Utils.RandomNames;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Item implements Comparable<Item> {
    final String name;

    final ProductTypes type;

    final LocalDate expirationDate;

    final float weight;

    Item(String name, ProductTypes type, LocalDate expirationDate, float weight) {
        this.name = name;
        this.type = type;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    static Item createRandomItem(RandomNames randomNameGenerator) {
        return new Item(randomNameGenerator.getRandomNameFromJsonFile(), Item.getRandomType(), LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(-365, 365)), ThreadLocalRandom.current().nextFloat()*100);
    }

    String getName() {
        return name;
    }

    ProductTypes getType() {
        return type;
    }

    LocalDate getExpirationDate() {
        return expirationDate;
    }

    float getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Item o) {
        return this.expirationDate.compareTo(o.expirationDate);
    }

    public int nameCompare(Item o) {
        return this.name.compareTo(o.name);
    }

    static ProductTypes getRandomType() {
        return ProductTypes.values()[ThreadLocalRandom.current().nextInt(ProductTypes.values().length)];
    }

    @Override
    public String toString() {
        return "Item: " + name + " Type: " + type + " Expiry Date: " + expirationDate.toString() + " weight: " + weight;
    }

    public enum ProductTypes{
        BOOK, CHAIR
    }
}

