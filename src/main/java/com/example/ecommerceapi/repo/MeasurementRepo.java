package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 27-Jul-23
 */
@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM measurement WHERE customer_id = :customer_id")
    Long findMeasurementCountByCustomerId(@Param("customer_id") Long customer_id);

    @Query(nativeQuery = true, value = "SELECT * FROM measurement INNER JOIN customer ON measurement.customer_id=customer.customer_id WHERE customer.nic=:nic")
    Optional<Measurement> findByCustomerNic(@Param("nic") String nic);

    @Modifying  // important for void & Integer return type queries
    @Query(nativeQuery = true, value = "DELETE measurement FROM measurement INNER JOIN customer ON measurement.customer_id=customer.customer_id WHERE customer.nic=:nic")
    void deleteMeasurementsByCustomerNic(@Param("nic") String nic);
}

