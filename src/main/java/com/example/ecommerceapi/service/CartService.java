package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.CartDTO;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
public interface CartService {
    boolean saveCart(CartDTO cartDTO);
}
