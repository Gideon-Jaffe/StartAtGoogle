package Week2.Store;

import Utils.DoubleRandomNames;
import Utils.RandomNames;
import Week2.Store.StoreAndUser.Store;
import Week2.Store.StoreAndUser.User;

public class Main {
    public static void main(String[] args) {
        RandomNames randomNames = new DoubleRandomNames("src/main/resources/first_names.json", "src/main/resources/last_names.json");
        Store myStore = new Store();
        User user1 = new User(randomNames.getRandomNameFromJsonFile());

        myStore.createRandomCouponsForUser(10, user1);
        System.out.println(user1);

        System.out.println("Trying to use coupon with highest value");
        user1.useCouponWithHighestValue(myStore).ifPresent(amount -> System.out.println("got " + amount));
        System.out.println("Trying to use coupon with closest expiration date");
        user1.useCouponClosestExpiryDate(myStore).ifPresent(amount -> System.out.println("got " + amount));
        System.out.println("Trying to use coupon random coupon");
        user1.useRandomCoupon(myStore).ifPresent(amount -> System.out.println("got " + amount));
    }
}
