package Week2.ExceptionExercise;

import Utils.FileHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadConfigFile {
    Map<String, String> config;

    public ReadConfigFile(String path) throws IOException {
        this.config = new HashMap<>();
        readConfig(path);
    }

    private void readConfig(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            Properties prop = new Properties();
            prop.load(reader);
            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                this.config.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating new config file");
            createConfigFile(path);
        } catch (IOException e) {
            throw new IOException("Failed in function readConfig\n" + e);
        }
    }

    private void createConfigFile(String path) throws IOException {
        if (!FileHandling.tryCreateFile(path)) {
            throw new IOException("Cant create file");
        }
        readConfig(path);
    }

    public String getConfiguration(String configName) throws IllegalStateException {
        if (config == null) throw new IllegalStateException();
        if (!config.containsKey(configName)) throw new IllegalArgumentException();
        return config.get(configName);
    }
}
