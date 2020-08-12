/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.adapter;

import com.syzegee.customer.service.entity.Customer;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import com.syzegee.customer.service.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Sangam
 */
@Slf4j
@Configuration
public class CustomerDBAdapter {
    @Autowired
    CustomerRepository customerRepository;

    /**
     * This method is used to get the customer by customerId
     *
     * @param id takes the customer Id
     * @return customer entity else return error
     */
    public Customer getCustomerById(Long id,String corelationId ) throws CustomerRuntimeException{
        log.info("Initiate getCustomerById : " + " - CorrelationId: " + corelationId );
        return customerRepository.getRecordById(id);
    }

    /**
     * This method is used to find list of customers
     *
     * @return List of customers
     */
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
