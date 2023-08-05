package com.example.ecommerceapi.repo;

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
    @Query(nativeQuery = true, value = "SELECT line_item.line_item_id, line_item.cart_id, line_item.order_id, line_item.product_id, line_item.quantity, line_item.total_price, orders.date, orders.duration, orders.sub_total, orders.customer_id, customer.nic, customer.name, customer.address, customer.city, customer.contact" +
            " FROM line_item INNER JOIN orders ON line_item.order_id = orders.order_id INNER JOIN customer ON orders.customer_id = customer.customer_id;")
    List<Object[]> getLineItemsDetails();
}
