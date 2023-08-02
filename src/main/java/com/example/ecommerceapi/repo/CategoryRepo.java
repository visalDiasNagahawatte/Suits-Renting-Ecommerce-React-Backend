package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 26-Jul-23
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    boolean existsByDescription(String description);
    Optional<Category> findByDescription(String description);
    String deleteByDescription(String description);
}
