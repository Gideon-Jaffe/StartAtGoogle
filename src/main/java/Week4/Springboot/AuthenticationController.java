package Week4.Springboot;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authService;

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> login(@RequestBody User user) {
        Utils.checkEmail(user.getEmail());
        Utils.checkPassword(user.getPassword());
        String token = authService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.noContent().header("token", token).build();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> register(@RequestBody User user) {
        Utils.checkEmail(user.getEmail());
        Utils.checkName(user.getName());
        Utils.checkPassword(user.getPassword());
        User registered = authService.register(user.getEmail(), user.getName(), user.getPassword());
        return ResponseEntity.ok(registered);
    }
}

