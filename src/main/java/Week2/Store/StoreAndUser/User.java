package Week2.Store.StoreAndUser;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class User {
    final private String name;

    final private List<Coupon> coupons;

    public User(String name) {
        this.name = name;
        this.coupons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    List<Coupon> getCoupons() {
        return new ArrayList<>(coupons);
    }

    public OptionalDouble useCouponWithHighestValue(Store store) {
        if (hasNoCoupons()) return OptionalDouble.empty();
        Optional<Coupon> match = coupons.stream().max(Coupon::compareValue);
        return match.map(coupon -> useCouponAtStore(store, coupon)).orElseGet(OptionalDouble::empty);
    }

    public OptionalDouble useCouponWithId(Store store, UUID uuid) {
        if (hasNoCoupons()) return OptionalDouble.empty();

        Optional<Coupon> match = coupons.stream().filter(coupon -> coupon.getId() == uuid).findFirst();
        return match.map(coupon -> useCouponAtStore(store, coupon)).orElseGet(OptionalDouble::empty);
    }

    public OptionalDouble useCouponClosestExpiryDate(Store store) {
        if (hasNoCoupons()) return OptionalDouble.empty();

        Optional<Coupon> match = coupons.stream().min(Coupon::compareDate);
        return match.map(coupon -> useCouponAtStore(store, coupon)).orElseGet(OptionalDouble::empty);
    }

    public OptionalDouble useRandomCoupon(Store store) {
        if (hasNoCoupons()) return OptionalDouble.empty();

        Coupon match = coupons.get(ThreadLocalRandom.current().nextInt(coupons.size()));
        if (store.useCoupon(this, match)) {
            coupons.remove(match);
            return OptionalDouble.of(match.getValue());
        } else {
            return OptionalDouble.empty();
        }
    }

    private OptionalDouble useCouponAtStore(Store store, Coupon match) {
        if (store.useCoupon(this, match)) {
            coupons.remove(match);
            return OptionalDouble.of(match.getValue());
        } else {
            return OptionalDouble.empty();
        }
    }

    private boolean hasNoCoupons() {
        return coupons.size() == 0;
    }

    void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(name).append(" coupons:\n");
        for (Coupon coupon :
                coupons) {
            str.append(coupon).append("\n");
        }
        return str.toString();
    }
}

