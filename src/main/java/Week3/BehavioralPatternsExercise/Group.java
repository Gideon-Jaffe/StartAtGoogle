package Week3.BehavioralPatternsExercise;

import java.util.UUID;

public class Group implements Visitable {
    private final int id;
    private int size;

    public Group(int size) {
        this.id = UUID.randomUUID().hashCode();
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
