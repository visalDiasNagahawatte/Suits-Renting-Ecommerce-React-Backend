package com.example.ecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 29-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemDTO {
    private Long lineItemId;
    private int quantity;
    private double totalPrice;
    private Long orderId;
    //    private Long productId;
    private Long cartId;
    private ProductDTO productDTO;
}
