package Week4.Springboot;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authService;

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (!Utils.isEmailValidFormat(user.getEmail()) || !Utils.isPasswordValidFormat(user.getPassword())) {
            return ResponseEntity.badRequest().body("Must send email, and password in correct format!");
        }

        Optional<String> token = authService.login(user.getEmail(), user.getPassword());

        return token.<ResponseEntity<String>>map(s -> ResponseEntity.noContent().header("token", s).build())
                .orElseGet(() -> ResponseEntity.badRequest().body("Incorrect email or password"));

    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (!Utils.isEmailValidFormat(user.getEmail()) || !Utils.isNameValidFormat(user.getName()) || !Utils.isPasswordValidFormat(user.getPassword())) {
            return ResponseEntity.badRequest().body("Must send email, name, and password in correct format!");
        }

        Optional<User> registered = authService.register(user.getEmail(), user.getName(), user.getPassword());

        return registered.map(value -> ResponseEntity.ok(new Gson().toJson(value)))
                .orElseGet(() -> ResponseEntity.badRequest().body("Email already in use!"));

    }
}

