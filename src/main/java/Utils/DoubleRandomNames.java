package Utils;

public class DoubleRandomNames extends RandomNames {

    private final RandomNames secondRandomName;

    public DoubleRandomNames(String path1, String path2) {
        super(path1);
        secondRandomName = new RandomNames(path2);
    }

    public DoubleRandomNames(String path1, RandomNames randomNameGenerator) {
        super(path1);
        secondRandomName = randomNameGenerator;
    }

    @Override
    public String getRandomNameFromJsonFile() {
        return super.getRandomNameFromJsonFile() + " " + secondRandomName.getRandomNameFromJsonFile();
    }
}
