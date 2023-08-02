package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Jul-23
 */
@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
