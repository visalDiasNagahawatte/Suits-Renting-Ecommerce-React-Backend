package com.example.ecommerceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 28-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long customerId;
    private String date;
    private String duration;
    private double subTotal;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;
    @OneToOne(mappedBy = "orders")
    private Cart cart;
}
