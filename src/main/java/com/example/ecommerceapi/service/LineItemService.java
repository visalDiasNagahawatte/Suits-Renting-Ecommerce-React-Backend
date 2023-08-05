package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.LineItemDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
public interface LineItemService {
    boolean saveLineItems(LineItemDTO lineItemDTO);

    List<Object[]> getLineItemsDetails();
}
