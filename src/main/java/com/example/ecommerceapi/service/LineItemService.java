package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.LineItemDTO;
import com.example.ecommerceapi.dto.LineItemDetailsDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
public interface LineItemService {
    boolean saveLineItems(LineItemDTO lineItemDTO);
    List<LineItemDetailsDTO> getLineItemsDetails();
}
