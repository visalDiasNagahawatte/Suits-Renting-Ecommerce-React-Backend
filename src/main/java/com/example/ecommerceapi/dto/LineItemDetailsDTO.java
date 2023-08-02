package com.example.ecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 01-Aug-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemDetailsDTO {
    private Long lineItemId;
    private Long cartId;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private Long customerId;
    private String orderStatus;
    private String date;
    private int duration;
    private double subTotal;
    private String address;
    private String city;
    private String contact;
    private String extra;
    private String customerName;
    private String nic;
}
