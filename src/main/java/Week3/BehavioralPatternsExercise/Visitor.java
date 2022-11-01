package Week3.BehavioralPatternsExercise;

public interface Visitor {
    void visit(User user);

    void visit(Group group);

    void visit(Asset asset);
}
