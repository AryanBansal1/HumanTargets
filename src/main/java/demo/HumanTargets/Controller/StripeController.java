package demo.HumanTargets.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import demo.HumanTargets.Serive.StripeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> data) throws StripeException {
        Long amount = Long.valueOf((Integer) data.get("amount"));
        String name = (String) data.get("name");
        String email = (String) data.get("email");
    
        Session session = stripeService.createCheckoutSession(amount, name, email);
        Map<String, String> response = new HashMap<>();
        response.put("checkoutUrl", session.getUrl());
        return response;
    }

    
    
    
    
}
