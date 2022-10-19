package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNames {

    private Reader reader;
    private JSONArray names;

    public RandomNames(String path) {
        try (Reader fileReader = new FileReader(path)) {
            this.reader = fileReader;
            this.names = (JSONArray) JSONValue.parse(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Name file not found");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    public String getRandomName() {
        return (String) names.get(ThreadLocalRandom.current().nextInt(names.size()));
    }

    public static String getRandomFirstName(String path) {
        try (Reader reader = new FileReader(path)) {
            JSONArray names = (JSONArray) JSONValue.parse(reader);
            return (String) names.get(ThreadLocalRandom.current().nextInt(names.size()));
        } catch (FileNotFoundException e) {
            System.out.println("Name file not found");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        return null;
    }
}
