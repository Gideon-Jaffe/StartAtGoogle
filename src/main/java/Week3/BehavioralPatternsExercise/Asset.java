package Week3.BehavioralPatternsExercise;

import java.util.UUID;

public class Asset implements Visitable {
    private final int serialNumber;
    private String owner;
    private double rating;

    public Asset(String owner, double rating) {
        this.serialNumber = UUID.randomUUID().hashCode();
        this.owner = owner;
        this.rating = rating;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

