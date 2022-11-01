package Week3.BehavioralPatternsExercise;

import com.google.gson.Gson;

public class JsonConvertorVisitor implements Visitor {
    @Override
    public void visit(User user) {
        System.out.println(toJson(user));
    }

    @Override
    public void visit(Group group) {
        System.out.println(toJson(group));
    }

    @Override
    public void visit(Asset asset) {
        System.out.println(toJson(asset));
    }

    private String toJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}
