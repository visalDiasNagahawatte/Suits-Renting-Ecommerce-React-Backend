package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 29-Jul-23
 */
@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query(nativeQuery = true, value = "SELECT cart_id FROM cart ORDER BY cart_id DESC LIMIT 1")
    Long getLastCartId();
}
