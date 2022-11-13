package com.sprint.practice.service;

import com.sprint.practice.domain.Customer;
import com.sprint.practice.exceptions.CustomerAlreadyExistException;
import com.sprint.practice.exceptions.CustomerNotFoundException;
import com.sprint.practice.exceptions.ProductNotFoundException;
import com.sprint.practice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistException {
        if (customerRepository.findById(customer.getCustomerId()).isPresent()){
            throw new CustomerAlreadyExistException();
        }

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() throws CustomerNotFoundException {
        if (customerRepository.findAll().isEmpty()){
            throw new CustomerNotFoundException();
        }
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean result = false;
        if (customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException();
        }else {
            customerRepository.deleteById(customerId);
            result = true;
        }
        return result;
    }

    @Override
    public List<Customer> getAllProductsByProductName(String productName) throws ProductNotFoundException {
        if (customerRepository.findByProductWithProductName(productName).isEmpty()){
            throw new ProductNotFoundException();
        }
        return customerRepository.findByProductWithProductName(productName);
    }
}
