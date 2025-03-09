package demo.HumanTargets.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/current-user")
    public String getCurrentUser(Authentication authentication) {
        return authentication !=null ? authentication.getName() : null;
    }
    
}
