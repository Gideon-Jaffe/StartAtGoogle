package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepo;
    static int id = 0;
    Map<String, User> userTokens;

    private AuthenticationService() {
        this.userTokens = new HashMap<>();
    }

    Optional<User> register(String email, String name, String password) {

        if (!checkIfUserExists(email)) {
            User user = new User(id++, email, name, password);
            return Optional.of(userRepo.writeToFile(user.getEmail() + ".json", user));
        }
        return Optional.empty();
    }

    Optional<User> validate(String token) {
        if (!userTokens.containsKey(token)) {
            return Optional.empty();
        }
        return Optional.of(userTokens.get(token));
    }


    Optional<String> login(String email, String password) {
        User cachedUser = userRepo.readFromCache(email);
        if (cachedUser == null || !Objects.equals(cachedUser.getPassword(), password)) {
            return Optional.empty();
        }

        return Optional.of(createToken(cachedUser));
    }

    private String createToken(User user) {
        String token = UUID.randomUUID().toString();
        userTokens.put(token, user);
        return token;
    }

    void reloadUser(String email, String token) {
        User updatedUser = userRepo.readFromCache(email);
        userTokens.put(token, updatedUser);
    }

    void removeToken(String token) {
        userTokens.remove(token);
    }


    private boolean checkIfUserExists(String email) {
        return userRepo.readFromCache(email) != null;
    }
}

