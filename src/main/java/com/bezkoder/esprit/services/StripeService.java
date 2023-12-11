package com.bezkoder.esprit.services;


import com.bezkoder.esprit.models.ChargeRequest;
import com.bezkoder.esprit.models.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String apiKey;

    public PaymentResponse charge(ChargeRequest chargeRequest) throws StripeException {
        Stripe.apiKey = apiKey;

        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) chargeRequest.getAmount())
                .setCurrency(chargeRequest.getCurrency())
                .setSource(chargeRequest.getStripeToken())
                .setDescription(chargeRequest.getDescription())
                .build();

        Charge charge = Charge.create(params);

        return new PaymentResponse(charge.getId(), charge.getAmount(), charge.getCurrency(), charge.getStatus());
    }

}