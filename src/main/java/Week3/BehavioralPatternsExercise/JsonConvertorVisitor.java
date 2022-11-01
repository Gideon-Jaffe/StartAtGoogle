package Week3.BehavioralPatternsExercise;

import com.google.gson.Gson;

public class JsonConvertorVisitor implements Visitor{
    @Override
    public void visit(User user) {
        Gson gson = new Gson();
        gson.toJson(user);
    }

    @Override
    public void visit(Group group) {

    }

    @Override
    public void visit(Asset asset) {

    }
}
