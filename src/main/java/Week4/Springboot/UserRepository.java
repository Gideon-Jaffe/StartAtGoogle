package Week4.Springboot;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepository {
    //cache - load all files when app starts
    //for every read - read from cache
    //for every write - write to files and update cache
    static String FILE_DIRECTORY = "./users/";
    static Map<String, User> usersCache;

    private UserRepository() {

        usersCache = new HashMap<>();
        File file = new File(FILE_DIRECTORY);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("Error - Cant crete users folder!");
            }
        }
        loadAllUsersToCache(file);
    }

    User readFromCache(String email) {
        return usersCache.get(email);
    }

    private void loadAllUsersToCache(File folder) {
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (!fileEntry.isDirectory()) {
                if (Utils.isJsonFile(fileEntry)) {
                    User user = readFromFile(fileEntry.getAbsolutePath());
                    usersCache.put(user.getEmail(), user);
                }
            }
        }
    }

    void deleteFile(String path) {
        File file = new File(FILE_DIRECTORY + path);
        file.delete();
    }

    User deleteUser(String email) {
        if (!usersCache.containsKey(email)) {
            throw new IllegalArgumentException("cant remove user that doesn't exist");
        }
        User deletedUser = usersCache.get(email);
        usersCache.remove(email);
        deleteFile(email + ".json");
        return deletedUser;
    }

    User writeToFile(String fileName, User user){
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter(FILE_DIRECTORY + fileName)) {
            String userJson = gson.toJson(user);
            fw.write(userJson);
            usersCache.put(user.getEmail(), user);
        } catch (IOException e) {
            throw new RuntimeException("cant write to new file to update");
        }
        return user;
    }

    private User readFromFile(String fileName) {

        User readUser;
        try (FileReader fr = new FileReader(fileName)) {
            Gson gson = new Gson();
            readUser = gson.fromJson(fr, User.class);
            usersCache.put(readUser.getEmail(), readUser);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return readUser;
    }
}
