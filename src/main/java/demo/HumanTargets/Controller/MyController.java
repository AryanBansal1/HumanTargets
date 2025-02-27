package demo.HumanTargets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import demo.HumanTargets.Model.RequestDonee;
import demo.HumanTargets.Security.UserRepo;
import demo.HumanTargets.Security.Users;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MyController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    
    @Autowired
    UserRepo userRepo;
    
    @PostMapping("/NewdoneeRequest")
    public String NewdoneeRequest(@ModelAttribute RequestDonee requestDonee ) {
        
        return "";
    }

    @PostMapping("/register_process")
    public String register(@ModelAttribute Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        userRepo.save(users);
        return "";
    }

    @GetMapping("/")
    public String home() {
        return "/html/index.html";
    }
    
    
    
}
