package com.example.ecommerceapi.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 26-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private Long categoryId;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private double price;
    @NotNull
    private int stockQty;
    @NotNull
    private String imgUrl;
    @NotNull
    private boolean inStock;

    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", updatable = false, insertable = false)
    private Category category;
}
