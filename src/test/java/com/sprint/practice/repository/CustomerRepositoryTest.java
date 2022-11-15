package com.sprint.practice.repository;

import com.sprint.practice.domain.Customer;
import com.sprint.practice.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    private Product product;

    @BeforeEach
    public void setUp(){
        product = new Product(11,"product1","This Is A Product");
        customer = new Customer(11,"Haider",645734,product);

    }

    @AfterEach
    public void tearDown(){
        product = null;
        customer = null;
      //  customerRepository.deleteAll();
    }

    @Test
    public void saveCustomer() {
       customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustomerId(),customer1.getCustomerId());
        assertNotEquals(20,customer1.getCustomerId());
    }

    @Test
    public void fetchAllCustomer(){
        List<Customer> customers = customerRepository.findAll();

        assertEquals(1,customers.size());
        assertNotEquals(1,customers.size());
        assertEquals("Haider",customers.get(0).getCustomerName());
        assertNotEquals("Jamuna",customers.get(0).getCustomerName());
    }

    @Test
    public void deleteCustomer(){
        customerRepository.deleteById(customer.getCustomerId());
        assertEquals(Optional.empty(),customerRepository.findById(customer.getCustomerId()));
        assertNotEquals(Optional.of(customer), customerRepository.findById(customer.getCustomerId()));
    }

    @Test
    public void getCustomerByProductName(){
        List<Customer> customers = customerRepository.findByProductWithProductName(customer.getProduct().getProductName());
        assertEquals(1,customers.size());
        assertNotEquals(3,customers.size());
        assertEquals(customer.getProduct().getProductName(),customers.get(0).getProduct().getProductName());
        assertNotEquals("UnExcepted",customers.get(0).getProduct().getProductName());
    }
}
