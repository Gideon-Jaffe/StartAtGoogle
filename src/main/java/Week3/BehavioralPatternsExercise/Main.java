package Week3.BehavioralPatternsExercise;

public class Main {
    public static void main(String[] args) {
        JsonConvertorVisitor convertor = new JsonConvertorVisitor();
        Asset asset = new Asset("Awesome", 2.5);
        User user = new User("gideon", "my pass");
        Group group = new Group(5);
        System.out.println("-------Printing asset as Json");
        asset.accept(convertor);
        System.out.println("-------Printing user as Json");
        user.accept(convertor);
        System.out.println("-------Printing group as Json");
        group.accept(convertor);
    }
}
