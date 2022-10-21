package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNames {

    private final JSONArray names;

    public RandomNames(String path) {
        JSONArray tempNames;
        try (Reader tempReader = new FileReader(path)) {

            tempNames = (JSONArray) JSONValue.parse(tempReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(path + " not found");
        } catch (IOException e) {
            throw new RuntimeException("IO Error in opening " + path);
        }

        names = tempNames;
    }

    public String getRandomNameFromJsonFile() {
        if (names == null) return "";
        return (String) names.get(ThreadLocalRandom.current().nextInt(names.size()));
    }
}
