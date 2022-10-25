package Week2.ExceptionExercise;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ReadConfigFile con = new ReadConfigFile("config.properties");

        String config1 = "config1";
        String config2 = "ooblah";
        Optional<String> gottenConfig1 = con.getConfiguration(config1);
        if (gottenConfig1.isPresent()) {
            System.out.println(gottenConfig1.get());
        } else {
            System.out.println(config1 + " configuration does not exist");
        }

        Optional<String> gottenConfig2 = con.getConfiguration(config2);
        if (gottenConfig2.isPresent()) {
            System.out.println(gottenConfig2.get());
        } else {
            System.out.println(config2 + " configuration does not exist");
        }
    }
}

