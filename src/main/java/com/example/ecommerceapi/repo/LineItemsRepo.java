package com.example.ecommerceapi.repo;

import com.example.ecommerceapi.dto.LineItemDetailsDTO;
import com.example.ecommerceapi.entity.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 29-Jul-23
 */
@Repository
public interface LineItemsRepo extends JpaRepository<LineItem, Long> {
    @Query(value = "select * from line_item inner join orders on line_item.order_id=orders.order_id inner join customer on orders.customer_id=customer.customer_id", nativeQuery = true)
    List<LineItemDetailsDTO> getLineItemsDetails();
}
