package demo.HumanTargets.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import demo.HumanTargets.Serive.StripeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public Map<String, String> createpaymentintent(@RequestBody Map<String ,Object> data)throws StripeException {
        Long amount = Long.valueOf((Integer) data.get("amount"));
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount);
        Map<String,String> responsedata = new HashMap<>();
        responsedata.put("clientSecret",paymentIntent.getClientSecret());
        return responsedata;
    }
    
    
}
