package demo.HumanTargets.Serive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String stripapikey;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.prod-url}")
    private String  prodUrl;

    public Session createCheckoutSession(Long amount, String name, String email) throws StripeException {
        Stripe.apiKey = stripapikey;
    
        SessionCreateParams params =
            SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(prodUrl +"/success.html") // ✅ Replace with your actual frontend
                .setCancelUrl(prodUrl+"/cancel.html")
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("inr")
                                .setUnitAmount(amount * 100) // in paise
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Donation from " + name)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .setCustomerEmail(email) // optional
                .build();

                System.out.println("Success URL: " + prodUrl + "/success");
                System.out.println("Cancel URL: " + prodUrl + "/cancel");

    
        return Session.create(params);
    }
}
