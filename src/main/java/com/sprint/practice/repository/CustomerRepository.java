package com.sprint.practice.repository;

import com.sprint.practice.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer,Integer> {

    @Query("{'product.productName': {$in :[?0]}}")
    List<Customer> findByProductWithProductName(String productName);
}
