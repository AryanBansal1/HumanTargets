package demo.HumanTargets.Serive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String stripapikey;

    public PaymentIntent createPaymentIntent(Long amount, String currency) throws StripeException{
        Stripe.apiKey = stripapikey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                        .setAmount(amount*100)
                        .setCurrency("INR")
                        .setPaymentMethod(currency)
                        .build();

                        return PaymentIntent.create(params);
    }
}
