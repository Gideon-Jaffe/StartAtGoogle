package Utils;

import java.io.File;
import java.io.IOException;

public class FileHandling {
    public static boolean tryCreateFile(String path) {
        File file = new File(path);
        try {
            file.createNewFile();
            return file.exists();
        } catch (IOException e) {
            throw new RuntimeException("Cant create File");
        }
    }
}
