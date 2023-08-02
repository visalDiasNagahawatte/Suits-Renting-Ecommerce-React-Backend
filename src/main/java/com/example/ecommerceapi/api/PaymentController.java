package com.example.ecommerceapi.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentController {

//    @GetMapping
//    public ResponseEntity<StandardResponse> addPayment(){
//        return new ResponseEntity<>(new StandardResponse(200, "Success...", null), HttpStatus.OK);
//    }
}
