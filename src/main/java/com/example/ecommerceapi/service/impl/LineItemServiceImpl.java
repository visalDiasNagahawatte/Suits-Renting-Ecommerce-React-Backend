package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.LineItemDTO;
import com.example.ecommerceapi.dto.LineItemDetailsDTO;
import com.example.ecommerceapi.entity.LineItem;
import com.example.ecommerceapi.repo.CartRepo;
import com.example.ecommerceapi.repo.LineItemsRepo;
import com.example.ecommerceapi.repo.OrderRepo;
import com.example.ecommerceapi.service.LineItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
@Service
@Transactional
public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemsRepo lineItemsRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public boolean saveLineItems(LineItemDTO lineItemDTO) {
        if (!Objects.isNull(lineItemDTO) && !Objects.isNull(lineItemDTO.getProductDTO())) {
            LineItem lineItem = mapper.map(lineItemDTO, LineItem.class);
            lineItem.setOrderId(orderRepo.getLastOrderId());
            lineItem.setCartId(cartRepo.getLastCartId());
            lineItem.setProductId(lineItemDTO.getProductDTO().getProductId());
            lineItem.setQuantity(lineItemDTO.getQuantity());
            lineItemsRepo.save(lineItem);
            System.out.println("line items ok");
            return true;
        } else {
            throw new RuntimeException("Invalid inputs...");
        }
    }

    @Override
    public List<LineItemDetailsDTO> getLineItemsDetails(){
        return lineItemsRepo.getLineItemsDetails();
    }
}
