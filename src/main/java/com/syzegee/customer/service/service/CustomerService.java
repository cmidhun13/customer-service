/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.service;

import com.syzegee.customer.service.adapter.CustomerDBAdapter;
import com.syzegee.customer.service.adapter.CustomerStatusDBAdapter;
import com.syzegee.customer.service.datafetcher.CustomerDataFetcher;
import com.syzegee.customer.service.datafetcher.CustomerStatusDataFetcher;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sangam
 */
@Slf4j
@Service
public class CustomerService {
    @Autowired
    CustomerDBAdapter customerDBAdapter;
    @Autowired
    CustomerStatusDBAdapter customerStatusDBAdapter;

    public DataFetcher retrieveCustomerById(String corelationId) throws CustomerRuntimeException {
        log.info("Initiate retrieveCustomerById : " + " - CorrelationId: " + corelationId );

        CustomerDataFetcher customerDataFetcher = new CustomerDataFetcher(customerDBAdapter);
        customerDataFetcher.setCorelationId(corelationId);
        DataFetcher dataFetcher=customerDataFetcher;
        return dataFetcher ;

    }

    public DataFetcher retrieveCustomerStatus() throws CustomerRuntimeException {
        //log.info("Initiate retrieveCustomerStaus : " + " - CorrelationId: " + corelationId );
        CustomerStatusDataFetcher customerStatusDataFetcher = new CustomerStatusDataFetcher(customerStatusDBAdapter,customerDBAdapter);
        return customerStatusDataFetcher ;

    }


}
