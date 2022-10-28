package Week2.LambdaExercise.Items;

import Utils.RandomNames;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stock {
    final List<Item> items;

    public Stock() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    private void generateRandomItem(RandomNames randomNameGenerator) {
        items.add(Item.createRandomItem(randomNameGenerator));
    }

    public void generateRandomItems(RandomNames randomNameGenerator, int amount) {
        for (int i = 0; i < amount; i++) {
            generateRandomItem(randomNameGenerator);
        }
        items.sort(Item::compareTo);
    }

    public List<Item> expiredItems() {
        return items.stream().filter(item -> item.expirationDate.isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public Optional<Item> getItemWithClosestExpiry() {
        return items.stream().filter(item -> !item.expirationDate.isBefore(LocalDate.now())).min(Item::compareTo);
    }

    public List<Item> getItemsSortedAlphabetically() {
        return items.stream().sorted(Item::nameCompare).collect(Collectors.toList());
    }

    public Optional<Item> getItemByName(String name) {
        return items.stream().filter(item -> item.name.equals(name)).findAny();
    }

    public List<String> getItemNamesWithWeightAboveNumber(float minWeight) {
        return items.stream().filter(item -> item.weight >= minWeight).map(Item::getName).collect(Collectors.toList());
    }

    public Map<Item.ProductTypes, Long> getSumForProductTypes() {
        return items.stream().collect(Collectors.groupingBy(Item::getType, Collectors.counting()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("The Stock\n");
        for (Item current : items) {
            builder.append(current).append("\n");
        }
        return builder.toString();
    }
}
