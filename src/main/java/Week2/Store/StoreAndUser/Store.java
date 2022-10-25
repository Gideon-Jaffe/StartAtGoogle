package Week2.Store.StoreAndUser;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Store {


    public void createRandomCouponsForUser(int amount, User user) {
        //int year, month, date;
        Calendar myCalendar = Calendar.getInstance();
        for (int i = 0; i < amount; i++) {
            myCalendar.set(ThreadLocalRandom.current().nextInt(2010, 2040), ThreadLocalRandom.current().nextInt(12), ThreadLocalRandom.current().nextInt(31));
            user.addCoupon(new Coupon(myCalendar.getTime(), ThreadLocalRandom.current().nextDouble(500)));
        }
    }

    public void assignCouponToUser(User user, Date expirationDate, double value) {
        if (expirationDate.compareTo(Calendar.getInstance().getTime()) > 0) {
            user.addCoupon(new Coupon(expirationDate, value));
        }
    }

    public boolean useCoupon(Coupon coupon) {
        if (isCouponLegal(coupon)) {
            System.out.println("Used Coupon: " + coupon);
            return true;
        } else {
            System.out.println("Can't Use Coupon: " + coupon);
            return false;
        }
    }

    private boolean isCouponLegal(Coupon coupon) {
        return coupon.getExpiryDate().compareTo(Calendar.getInstance().getTime()) > 0;
    }
}

