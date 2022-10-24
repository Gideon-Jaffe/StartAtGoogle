package Week2.ExceptionExercise;

import Utils.FileHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class readConfigFile {
    Map<String, String> config;

    public readConfigFile(String path) {
        this.config = new HashMap<>();
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
            throw new RuntimeException(e);
        }
    }

    private void createConfigFile(String path) {
        if (!FileHandling.tryCreateFile(path)) {
            throw new RuntimeException("Cant create file");
        }
    }
}
