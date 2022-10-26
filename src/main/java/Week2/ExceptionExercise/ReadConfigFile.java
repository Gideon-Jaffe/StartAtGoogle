package Week2.ExceptionExercise;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class ReadConfigFile {
    Map<String, String> config;

    public ReadConfigFile() {
    }

    public void initializeConfig(String path) throws IllegalArgumentException, IOException {
        checkFileType(path);
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
            config = gson.fromJson(reader,stringStringMap);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating new config file");
            createConfigFile(path);
        }
    }

    private void createConfigFile(String path) throws IOException{
        FileWriter writer = new FileWriter(path);
            writer.write("{\"name\":\"John\", \"age\":\"30\", \"car\":\"Awesome\"}");
            writer.close();
            initializeConfig(path);
    }

    public String getConfiguration(String configName) throws IllegalStateException, IllegalArgumentException {
        checkIfInitialized();
        checkIfInConfig(configName);
        return config.get(configName);
    }

    private void checkFileType(String path) throws IllegalArgumentException {
        if (!path.substring(path.lastIndexOf('.')).equals(".json")) throw new IllegalArgumentException("File not ending with .json");
    }

    private void checkIfInitialized() throws IllegalStateException {
        if (config == null) throw new IllegalStateException("Map not initialized");
    }

    private void checkIfInConfig(String configName) throws IllegalArgumentException {
        if (!config.containsKey(configName)) throw new IllegalArgumentException("Key is not in config file");

    }
}
