package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.OrderDTO;
import com.example.ecommerceapi.entity.Orders;
import com.example.ecommerceapi.repo.CustomerRepo;
import com.example.ecommerceapi.repo.OrderRepo;
import com.example.ecommerceapi.service.CartService;
import com.example.ecommerceapi.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public boolean saveOrder(OrderDTO orderDTO) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (!Objects.isNull(orderDTO) && !Objects.isNull(orderDTO.getCartDTO()) && !Objects.isNull(orderDTO.getCustomerDTO())) {
            if (customerRepo.existsByNic(orderDTO.getCustomerDTO().getNic())) {
                Orders orders = mapper.map(orderDTO, Orders.class);
                orders.setCustomerId(orderDTO.getCustomerDTO().getCustomerId());
                orders.setDate(currentDateTime + "");
                orderRepo.save(orders);
                System.out.println("Order Ok");
                cartService.saveCart(orderDTO.getCartDTO());
                return true;
            } else {
                throw new RuntimeException("Customer not exists...");
            }
        } else {
            throw new RuntimeException("Invalid inputs...");
        }
    }
}
