package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.OrderDTO;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
public interface OrderService {
    boolean saveOrder(OrderDTO orderDTO);
}
