package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    private UserService() {
    }

    LocalDateTime updateEmail(User user, String updatedEmail){
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), updatedEmail, user.getName(), user.getPassword());
        return updateData(newUser);
    }

    LocalDateTime updateName(User user, String updatedName) {
        //userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), updatedName, user.getPassword());
        return updateData(newUser);
    }

    LocalDateTime updatePassword(User user, String updatedPassword) {
        //userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), user.getName(), updatedPassword);
        return updateData(newUser);
    }

    LocalDateTime deleteUser(User user) {
        userRepo.deleteFile(user);
        return LocalDateTime.now();
    }


    LocalDateTime updateData(User user){
        userRepo.writeToFile(user.getEmail() + ".json", user);
        return LocalDateTime.now();
    }
}

