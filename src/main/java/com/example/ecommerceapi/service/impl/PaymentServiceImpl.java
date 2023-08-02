package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.entity.Payment;
import com.example.ecommerceapi.service.PaymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private RestTemplate restTemplate;

    @Value("${stripe.apikey}")
    private String stripeApiKey;

    @Value("${visa.payment.gateway.url}")
    private String visaPaymentGatewayUrl;

    @Value("${visa.payment.gateway.api.key}")
    private String visaApiKey;

    public PaymentServiceImpl() {
    }

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String processVisaPayment(Payment payment) {
        // Prepare the payment request object to be sent to the payment gateway API
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + visaApiKey);

        HttpEntity<Payment> requestEntity = new HttpEntity<>(payment, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(visaPaymentGatewayUrl, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Payment processed successfully!";
            } else {
                return "Payment failed. Error code: " + response.getStatusCodeValue() + ". Message: " + response.getBody();
            }
        } catch (Exception e) {
            return "Payment failed. Reason: " + e.getMessage();
        }
    }
}
