package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.CustomerDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 26-Jul-23
 */
public interface CustomerService {
    boolean addCustomer(CustomerDTO customerDTO);
    boolean updateCustomer(CustomerDTO customerDTO, String nic);
    String deleteCustomer(String nic);
    CustomerDTO findCustomer(String nic);
    List<CustomerDTO> getAllCustomers();
}
