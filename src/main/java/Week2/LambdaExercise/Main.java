package Week2.LambdaExercise;

import Utils.RandomNames;
import Week2.LambdaExercise.Items.Item;
import Week2.LambdaExercise.Items.Stock;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock();
        RandomNames randomNameGenerator = new RandomNames("src/main/resources/first_names.json");

        stock.generateRandomItems(randomNameGenerator, 20);

        System.out.println("--------------- Stock Items ---------------");
        System.out.println(stock);

        System.out.println("\n--------------- Expired Items ---------------");
        for (Item currentItem : stock.expiredItems()) {
            System.out.println(currentItem);
        }

        System.out.println("\n--------------- Closest expired item ---------------");
        stock.getItemWithClosestExpiry().ifPresent(item -> System.out.println(stock.getItemWithClosestExpiry() + "\n"));

        System.out.println("\n--------------- Sorted Alphabetically ---------------");
        for (Item currentItem : stock.getItemsSortedAlphabetically()) {
            System.out.println(currentItem);
        }

        System.out.println("\n--------------- item by name (trying STIG) ---------------");
        stock.getItemByName("STIG").ifPresent(item -> System.out.println(stock.getItemWithClosestExpiry() + "\n"));

        System.out.println("\n--------------- names of items above 50 weight ---------------");
        for (String currentName : stock.getItemNamesWithWeightAboveNumber(50.0f)) {
            System.out.println(currentName + ", ");
        }

        System.out.println("\n--------------- sum of items aggregated by type ---------------");
        Map<Item.ProductTypes, Long> sumForProductTypes = stock.getSumForProductTypes();
        for (Map.Entry<Item.ProductTypes, Long> entry : sumForProductTypes.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}

