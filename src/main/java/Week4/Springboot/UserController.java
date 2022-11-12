package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    AuthenticationService authService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "email", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateEmail(@RequestBody User newEmail, @RequestHeader String token) {
        if (!Utils.isEmailValidFormat(newEmail.getEmail())) {
            return ResponseEntity.badRequest().body("Email not valid format!");
        }

        Optional<User> user = authService.validate(token);

        if (user.isPresent()) {
            LocalDateTime updateTime = userService.updateEmail(user.get(), newEmail.getEmail());
            authService.reloadUser(newEmail.getEmail(), token);
            return ResponseEntity.ok(String.format("{\"timeUpdated\":%s, \"newEmail\":%s}", updateTime.format(DateTimeFormatter.ISO_LOCAL_TIME), newEmail.getEmail()));
        } else {
            return ResponseEntity.badRequest().body("Token not valid!");
        }
    }

    @RequestMapping(value = "name", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateName(@RequestBody User newName, @RequestHeader String token) {
        if (!Utils.isNameValidFormat(newName.getName())) {
            return ResponseEntity.badRequest().body("Name not in correct format");
        }

        Optional<User> user = authService.validate(token);

        if (user.isPresent()) {
            LocalDateTime updateTime = userService.updateName(user.get(), newName.getName());
            authService.reloadUser(user.get().getEmail(), token);
            return ResponseEntity.ok(String.format("{\"timeUpdated\":%s, \"newName\":%s}", updateTime.format(DateTimeFormatter.ISO_LOCAL_TIME), newName.getName()));
        } else {
            return ResponseEntity.badRequest().body("Token not valid!");
        }
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public ResponseEntity<String> updatePassword(@RequestBody User newPassword, @RequestHeader String token) {
        if (!Utils.isPasswordValidFormat(newPassword.getPassword())) {
            return ResponseEntity.badRequest().body("Password not in correct format");
        }

        Optional<User> user = authService.validate(token);

        if (user.isPresent()) {
            LocalDateTime updateTime = userService.updatePassword(user.get(), newPassword.getPassword());
            authService.reloadUser(user.get().getEmail(), token);
            return ResponseEntity.ok(String.format("{\"timeUpdated\":%s}", updateTime.format(DateTimeFormatter.ISO_LOCAL_TIME)));
        } else {
            return ResponseEntity.badRequest().body("Token Not Valid!");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestHeader String token) {
        Optional<User> user = authService.validate(token);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("Token not valid!");
        } else {
            LocalDateTime deleteTime = userService.deleteUser(user.get());
            authService.removeToken(token);
            return ResponseEntity.ok(String.format("{\"timeDeleted\":%s}", deleteTime.format(DateTimeFormatter.ISO_LOCAL_TIME)));
        }
    }
}

