package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepo;
    static int id = 0;
    Map<String, User> userTokens;

    private AuthenticationService() {
        this.userTokens = new HashMap<>();
    }

    User register(String email, String name, String password) {

        if (!checkIfUserExists(email)) {
            User user = new User(id++, email, name, password);
            try {
                return userRepo.writeToFile(user.getEmail() + ".json", user);
            } catch (IOException e) {
                System.out.println("Couldn't write to file");
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    User validate(String token) {
        if (!userTokens.containsKey(token)) {
            throw new InvalidParameterException("Token incorrect");
        }
        return userTokens.get(token);
    }


    String login(String email, String password) {
        User cachedUser = userRepo.readFromCache(email);
        if (cachedUser == null) {
            throw new IllegalArgumentException("user doesn\"t exist");
        } else if (!Objects.equals(cachedUser.getPassword(), password)) {
            throw new IllegalArgumentException("wrong password");
        }
        return createToken(cachedUser);
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


    private static boolean checkIfUserExists(String email) {
        try (FileReader fr = new FileReader(email + ".json")) {
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}

