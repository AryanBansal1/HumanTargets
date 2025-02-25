package demo.HumanTargets.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import demo.HumanTargets.Model.RequestDonee;


@Controller
public class MyController {
    
    @PostMapping("/NewdoneeRequest")
    public String NewdoneeRequest(@ModelAttribute RequestDonee requestDonee ) {
        
        return "";
    }
    
}
