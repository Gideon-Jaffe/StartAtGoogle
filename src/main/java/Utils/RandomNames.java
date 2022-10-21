package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNames {

    private final Reader reader;

    public RandomNames(String path) {
        Reader tempReader = null;
        try {
            tempReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Name file not found");
        }

        this.reader = tempReader;
    }

    public String getRandomNameFromJsonFile() {
        if (this.reader == null) return "";
        JSONArray names = (JSONArray) JSONValue.parse(reader);
        return (String) names.get(ThreadLocalRandom.current().nextInt(names.size()));
    }

    public void close() {
        try {
            this.reader.close();
        } catch (IOException e) {
            System.out.println("The File was already closed");
        }
    }
    /* public static String getRandomFirstName(String path) {
        try (Reader reader = new FileReader(path)) {
            JSONArray names = (JSONArray) JSONValue.parse(reader);
            return (String) names.get(ThreadLocalRandom.current().nextInt(names.size()));
        } catch (FileNotFoundException e) {
            System.out.println("Name file not found");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        return null;
    }*/
}
