package com.sprint.practice.controller;

import com.sprint.practice.domain.Customer;
import com.sprint.practice.exceptions.CustomerAlreadyExistException;
import com.sprint.practice.exceptions.CustomerNotFoundException;
import com.sprint.practice.exceptions.ProductNotFoundException;
import com.sprint.practice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer/api/")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistException {
        ResponseEntity<?> responseEntity = null;

        try {
            responseEntity = new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
        }catch (CustomerAlreadyExistException e){
            throw new CustomerAlreadyExistException();
        }

        return responseEntity;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> fetchCustomers() throws CustomerNotFoundException {
        return  new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
    }
    @DeleteMapping("customer/{customerId}")
    public ResponseEntity<?> deleteByCustomerId(@PathVariable int customerId) throws CustomerNotFoundException {

        ResponseEntity<?> responseEntity = null;
        try{
            responseEntity =  new ResponseEntity<>(customerService.deleteCustomer(customerId),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomerNotFoundException();
        }
        return responseEntity;

    }

    @GetMapping("/products/{productName}")
    public ResponseEntity<?> getProductByProductName(@PathVariable String productName) throws ProductNotFoundException {

        ResponseEntity<?> responseEntity =null;
        try{
            responseEntity = new ResponseEntity<>(customerService.getAllProductsByProductName(productName),HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException();
        }

        return responseEntity;
    }
}
