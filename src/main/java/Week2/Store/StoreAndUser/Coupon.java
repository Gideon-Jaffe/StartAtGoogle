package Week2.Store.StoreAndUser;

import java.util.Date;
import java.util.UUID;

class Coupon implements Comparable<Coupon> {
    final private UUID id;

    final private Date expiryDate;

    private final double value;

    Coupon(Date expiryDate, double value) {
        this.id = UUID.randomUUID();
        this.expiryDate = expiryDate;
        this.value = value;
    }

    UUID getId() {
        return new UUID(this.id.getMostSignificantBits(), this.id.getLeastSignificantBits());
    }

    Date getExpiryDate() {
        return (Date) expiryDate.clone();
    }

    double getValue() {
        return value;
    }

    @Override
    public int compareTo(Coupon o) {
        int result = Double.compare(value, o.value);
        if (result == 0) {
            result = expiryDate.compareTo(o.expiryDate);
            if (result == 0) {
                result = id.compareTo(o.id);
            }
        }

        return result;
    }

    int compareValue(Coupon o) {
        return Double.compare(value, o.value);
    }

    int compareDate(Coupon o) {
        return this.expiryDate.compareTo(o.expiryDate);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", expiryDate=" + expiryDate +
                ", value=" + value +
                '}';
    }
}

