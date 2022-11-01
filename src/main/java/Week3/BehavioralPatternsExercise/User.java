package Week3.BehavioralPatternsExercise;

import java.util.UUID;

public class User implements Visitable {
    private final int id;
    private String name;
    private String password;

    public User(String name, String password) {
        this.id = UUID.randomUUID().hashCode();
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

