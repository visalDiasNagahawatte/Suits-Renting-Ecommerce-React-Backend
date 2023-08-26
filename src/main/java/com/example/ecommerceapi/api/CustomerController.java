package com.example.ecommerceapi.api;

import com.example.ecommerceapi.dto.CustomerDTO;
import com.example.ecommerceapi.service.CustomerService;
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
@RequestMapping("/api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean b = service.addCustomer(customerDTO);
        return new ResponseEntity<>(new StandardResponse(201, "Success", b), HttpStatus.CREATED);
    }

    @PutMapping(params = {"nic"})
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO customerDTO, @RequestParam String nic) {
        boolean b = service.updateCustomer(customerDTO, nic);
        return new ResponseEntity<>(new StandardResponse(204, "Success", b), HttpStatus.OK);
    }

    @DeleteMapping(params = {"nic"})
    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam String nic) {
        String s = service.deleteCustomer(nic);
        return new ResponseEntity<>(new StandardResponse(204, "Success", s), HttpStatus.OK);
    }

    @GetMapping(value = "/find", params = {"nic"})
    public ResponseEntity<StandardResponse> findCustomer(@RequestParam String nic){
        CustomerDTO customer = service.findCustomer(nic);
        return new ResponseEntity<>(new StandardResponse(200,"Success", customer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> allCustomers = service.getAllCustomers();
        return new ResponseEntity<>(new StandardResponse(200,"Success", allCustomers), HttpStatus.OK);
    }
}
