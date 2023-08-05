package com.example.ecommerceapi.entity;

import com.sun.istack.NotNull;
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
    @NotNull
    private Long customerId;
    @NotNull
    private String date;
    @NotNull
    private String duration;
    @NotNull
    private double subTotal;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;
    //This is bidirectional mapping if this won't work unidirectional mapping without below code
    @OneToOne(mappedBy = "orders")
    private Cart cart;
}
