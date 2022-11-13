package com.sprint.practice.service;

import com.sprint.practice.domain.Customer;
import com.sprint.practice.exceptions.CustomerAlreadyExistException;
import com.sprint.practice.exceptions.CustomerNotFoundException;
import com.sprint.practice.exceptions.ProductNotFoundException;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws CustomerAlreadyExistException;
    List<Customer> getAllCustomer() throws CustomerNotFoundException;
    boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
    List<Customer> getAllProductsByProductName(String productName) throws ProductNotFoundException;
}
