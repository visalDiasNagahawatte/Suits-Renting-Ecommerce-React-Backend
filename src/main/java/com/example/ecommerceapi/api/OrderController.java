package com.example.ecommerceapi.api;

import com.example.ecommerceapi.dto.OrderDTO;
import com.example.ecommerceapi.service.LineItemService;
import com.example.ecommerceapi.service.OrderService;
import com.example.ecommerceapi.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 23-Jul-23
 */
@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private LineItemService lineItemService;

    @PostMapping
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody OrderDTO orderDTO) {
        boolean b = orderService.saveOrder(orderDTO);
        return new ResponseEntity<>(new StandardResponse(201, "Success...", b), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllLineItems() {
        List<Object[]> allLineItems = lineItemService.getLineItemsDetails();
        return new ResponseEntity<>(new StandardResponse(200, "Success...", allLineItems), HttpStatus.OK);
    }
}
