package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 28-Jul-23
 */
@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
    @Query(nativeQuery = true, value = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1")
    Long getLastOrderId();
}
