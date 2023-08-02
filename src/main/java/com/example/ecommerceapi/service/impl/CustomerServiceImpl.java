package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.CustomerDTO;
import com.example.ecommerceapi.dto.MeasurementDTO;
import com.example.ecommerceapi.entity.Customer;
import com.example.ecommerceapi.entity.Measurement;
import com.example.ecommerceapi.repo.CustomerRepo;
import com.example.ecommerceapi.repo.MeasurementRepo;
import com.example.ecommerceapi.service.CustomerService;
import com.example.ecommerceapi.service.MeasurementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 26-Jul-23
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private MeasurementRepo measurementRepo;

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) {
        if (!Objects.isNull(customerDTO)) {
            if (!repo.existsByNic(customerDTO.getNic())) {
                repo.save(mapper.map(customerDTO, Customer.class));
                measurementService.addMeasurement(customerDTO.getMeasurementDTO());
                return true;
            } else {
                throw new RuntimeException("Already exists...");
            }
        }else{
            throw new RuntimeException("Invalid inputs...");
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, String nic) {
        if(!Objects.isNull(customerDTO)){
            Optional<Customer> byNic = repo.findByNic(nic);
            if(byNic.isPresent()){
                Customer customer = byNic.get();
                customer.setName(customerDTO.getName());
                customer.setNic(customerDTO.getNic());
                customer.setAddress(customerDTO.getAddress());
                customer.setCity(customerDTO.getCity());
                customer.setContact(customerDTO.getContact());
                customer.setExtra(customerDTO.getExtra());
                repo.save(customer);
                return true;
            }else{
                throw new RuntimeException("Not found...");
            }
        }else{
            throw new RuntimeException("Invalid inputs...");
        }
    }

    @Override
    public String deleteCustomer(String nic) {
        if(!nic.isEmpty()){
            if(repo.existsByNic(nic)){
                measurementRepo.deleteMeasurementsByCustomerNic(nic);
                return repo.deleteByNic(nic);
            }else {
                throw new RuntimeException("Not found...");
            }
        }else{
            throw new RuntimeException("Invalid inputs...");
        }
    }

    @Override
    public CustomerDTO findCustomer(String nic) {
        if(!nic.isEmpty()) {
            Optional<Customer> optionalCustomer = repo.findByNic(nic);
            Optional<Measurement> optionalMeasurement = measurementRepo.findByCustomerNic(nic);
            if (optionalCustomer.isPresent() && optionalMeasurement.isPresent()) {
                CustomerDTO customerDTO = mapper.map(optionalCustomer.get(), CustomerDTO.class);
                customerDTO.setMeasurementDTO(mapper.map(optionalMeasurement.get(), MeasurementDTO.class));
                return customerDTO;
            } else {
                throw new RuntimeException("Not found...");
            }
        }else{
            throw new RuntimeException("Invalid inputs...");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>(){}.getType());
    }
}
