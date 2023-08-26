package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.CartDTO;
import com.example.ecommerceapi.dto.LineItemDTO;
import com.example.ecommerceapi.dto.ProductDTO;
import com.example.ecommerceapi.entity.Cart;
import com.example.ecommerceapi.exception.EntryNotFoundException;
import com.example.ecommerceapi.repo.CartRepo;
import com.example.ecommerceapi.repo.OrderRepo;
import com.example.ecommerceapi.service.CartService;
import com.example.ecommerceapi.service.LineItemService;
import com.example.ecommerceapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LineItemService lineItemService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;

    @Override
    public boolean saveCart(CartDTO cartDTO) {
        if (!Objects.isNull(cartDTO)) {
            Cart cart = mapper.map(cartDTO, Cart.class);
            cart.setOrderId(orderRepo.getLastOrderId());
            cartRepo.save(cart);
            System.out.println("Cart ok");
            if (!cartDTO.getLineItemDTOS().isEmpty()) {
                for (LineItemDTO items : cartDTO.getLineItemDTOS()) {
                    lineItemService.saveLineItems(items);
                    ProductDTO productDTO = productService.findProduct(items.getProductDTO().getTitle());
                    int restQty = productDTO.getStockQty() - items.getQuantity();
                    productDTO.setStockQty(restQty);
                    productService.updateProduct(productDTO, productDTO.getTitle());
                }
                return true;
            } else {
                throw new EntryNotFoundException("Invalid Line Items...");
            }
        } else {
            throw new IllegalArgumentException("Invalid Inputs...");
        }
    }
}
