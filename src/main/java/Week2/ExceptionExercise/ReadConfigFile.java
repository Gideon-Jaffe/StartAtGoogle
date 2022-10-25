package Week2.ExceptionExercise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class ReadConfigFile {
    Map<String, String> config;

    public ReadConfigFile(String path) {
        readConfig(path);
    }

    private void readConfig(String path){
        try (FileReader reader = new FileReader(path)) {
            Properties prop = new Properties();
            prop.load(reader);
            config = new HashMap<>();
            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                this.config.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating new config file");
            createConfigFile(path);
        } catch (IOException e) {
            System.out.println("Failed to read config file\n");
        }
    }

    private void createConfigFile(String path){
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("config1=this is config 1\n" +
                    "config2=this is config 2");
            writer.close();
            readConfig(path);
        } catch (IOException e) {
            System.out.println("Cant create file, Map remained null");
        }
    }

    public Optional<String> getConfiguration(String configName) {
        try {
            checkIfInConfig(configName);
            return Optional.of(config.get(configName));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }

    private void checkIfInConfig(String configName) throws IllegalArgumentException {
        if (!config.containsKey(configName)) throw new IllegalArgumentException("Key is not in config file");

    }
}
