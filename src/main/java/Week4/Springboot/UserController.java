package Week4.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.security.InvalidParameterException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    AuthenticationService authService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "email", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateEmail(@RequestBody User newEmail, @RequestHeader String token) throws IOException {
        try {
            Utils.checkEmail(newEmail.getEmail());
        } catch (InvalidParameterException ip) {
            return ResponseEntity.badRequest().body("Email not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updateEmail(user, newEmail.getEmail());
        ResponseEntity<String> response;
        if (status) {
            authService.reloadUser(newEmail.getEmail(), token);
            response = ResponseEntity.ok("Successfully updated email");
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @RequestMapping(value = "name", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateName(@RequestBody User newName, @RequestHeader String token) throws IOException {
        try {
            Utils.checkName(newName.getName());
        } catch (InvalidParameterException ip) {
            return ResponseEntity.badRequest().body("Name not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updateName(user, newName.getName());
        ResponseEntity<String> response;
        if (status) {
            authService.reloadUser(user.getEmail(), token);
            response = ResponseEntity.ok("Successfully updated name");
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public ResponseEntity<String> updatePassword(@RequestBody User newPassword, @RequestHeader String token) throws IOException {
        try {
            Utils.checkPassword(newPassword.getPassword());
        } catch (InvalidParameterException ip) {
            return ResponseEntity.badRequest().body("Password not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updatePassword(user, newPassword.getPassword());
        ResponseEntity<String> response;
        if (status) {
            authService.reloadUser(user.getEmail(), token);
            response = ResponseEntity.ok("Successfully updated password");
        } else {
            response = ResponseEntity.internalServerError().build();
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestHeader String token) {
        User user = authService.validate(token);
        boolean status = userService.deleteUser(user);
        if (status) {
            authService.removeToken(token);
        }
        return ResponseEntity.noContent().build();
    }
}

