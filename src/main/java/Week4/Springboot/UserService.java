package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    private UserService() {
    }

    boolean updateEmail(User user, String updatedEmail){
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), updatedEmail, user.getName(), user.getPassword());
        updateData(newUser);
        return true;
    }

    boolean updateName(User user, String updatedName) throws IOException {
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), updatedName, user.getPassword());
        updateData(newUser);
        return true;
    }

    boolean updatePassword(User user, String updatedPassword) throws IOException {
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), user.getName(), updatedPassword);
        updateData(newUser);
        return true;
    }

    boolean deleteUser(User user) {
        userRepo.deleteFile(user);
        return true;
    }


    void updateData(User user){
        userRepo.writeToFile(user.getEmail() + ".json", user);
    }
}

