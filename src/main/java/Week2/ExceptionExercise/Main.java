package Week2.ExceptionExercise;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ReadConfigFile con = new ReadConfigFile();

        String car = "car";
        System.out.println("---------- Trying to get car property before initializing ----------");
        try {
            String gottenConfig1 = con.getConfiguration(car);
            System.out.println("name value is " + gottenConfig1);
        } catch (IllegalStateException e) {
            System.out.println(e);
        }

        System.out.println("\n---------- Trying to initialize map with text file ----------");
        try {
            con.initializeConfig("my_config_file.txt");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n---------- Trying to initialize map with json file ----------");
        try {
            con.initializeConfig("my_config_file.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String config1 = "name";
        String config2 = "ooblah";
        System.out.println("\n---------- Trying to get name property ----------");
        try {
            String gottenConfig1 = con.getConfiguration(config1);
            System.out.println("name value is " + gottenConfig1);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        System.out.println("\n---------- Trying to get ooblah property ----------");
        try {
            String gottenConfig2 = con.getConfiguration(config2);
            System.out.println("name value is " + gottenConfig2);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}

