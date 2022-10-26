package Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNames {

    private final List<String> jsonNames;

    public RandomNames(String path) {
        try (Reader tempReader = new FileReader(path)) {
            Gson gson = new Gson();
            Type stringList = new TypeToken<List<String>>(){}.getType();
            jsonNames = gson.fromJson(tempReader, stringList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(path + " not found");
        } catch (IOException e) {
            throw new RuntimeException("IO Error in opening " + path);
        }
    }

    public String getRandomNameFromJsonFile() {
        if (jsonNames == null) return "";
        return jsonNames.get(ThreadLocalRandom.current().nextInt(jsonNames.size()));
    }

    public boolean isNameInJsonFile(String name) {
        if (jsonNames == null) return false;
        return jsonNames.contains(name);
    }
}

