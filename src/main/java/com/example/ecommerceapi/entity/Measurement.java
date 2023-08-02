package com.example.ecommerceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 27-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Measurement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;
    private Long customerId;
    private String waistSize;
    private String hipSize;
    private String pantLength;
    private String neckSize;
    private String sleeveLength;
    private String jacketSize;
    private String extra;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;
}
