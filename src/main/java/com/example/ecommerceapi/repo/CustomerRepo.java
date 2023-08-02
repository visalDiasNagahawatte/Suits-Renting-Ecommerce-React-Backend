package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 26-Jul-23
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    boolean existsByNic(String nic);

    String deleteByNic(String nic);

    Optional<Customer> findByNic(String nic);

    @Query(nativeQuery = true, value = "select customer_id from customer order by customer_id desc limit 1")
    Long getLastCustomerId();
}
