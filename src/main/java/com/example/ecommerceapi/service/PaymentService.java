package com.example.ecommerceapi.service;

import com.example.ecommerceapi.entity.Payment;
import com.stripe.exception.StripeException;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Jul-23
 */
public interface PaymentService {
    String processVisaPayment(Payment payment);
}
