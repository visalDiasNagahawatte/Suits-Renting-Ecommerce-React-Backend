package com.example.ecommerceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 29-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LineItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItemId;
    private int quantity;
    private double totalPrice;
    private Long orderId;
    private Long productId;
    private Long cartId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    private Orders orders;
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", insertable = false, updatable = false)
    private Cart cart;
}
