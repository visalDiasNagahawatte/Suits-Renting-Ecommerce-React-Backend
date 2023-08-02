package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 26-Jul-23
 */
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    boolean existsByTitle(String title);
    Optional<Product> findByTitle(String title);
    String deleteByTitle(String title);
}
