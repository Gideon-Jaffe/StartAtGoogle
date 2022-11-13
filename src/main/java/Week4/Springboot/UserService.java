package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    private UserService() {
    }

    LocalDateTime updateEmail(String userId, String updatedEmail){
        User deletedUser = userRepo.deleteUser(userId);
        User newUser = new User(deletedUser.getId(), updatedEmail, deletedUser.getName(), deletedUser.getPassword());
        return updateData(newUser);
    }

    LocalDateTime updateName(String userEmail, String updatedName) {
        User user = userRepo.readFromCache(userEmail);
        User newUser = new User(user.getId(), user.getEmail(), updatedName, user.getPassword());
        return updateData(newUser);
    }

    LocalDateTime updatePassword(String userEmail, String updatedPassword) {
        User user = userRepo.readFromCache(userEmail);
        User newUser = new User(user.getId(), user.getEmail(), user.getName(), updatedPassword);
        return updateData(newUser);
    }

    LocalDateTime deleteUser(String userEmail) {
        userRepo.deleteUser(userEmail);
        return LocalDateTime.now();
    }


    LocalDateTime updateData(User user){
        userRepo.writeToFile(user.getEmail() + ".json", user);
        return LocalDateTime.now();
    }
}

