/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.datafetcher;

import com.google.common.collect.MoreCollectors;
import com.syzegee.customer.service.adapter.CustomerDBAdapter;
import com.syzegee.customer.service.entity.Customer;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Sangam
 */
@Slf4j
@Component
public class CustomerDataFetcher implements DataFetcher<Customer>  {

    private CustomerDBAdapter customerAdapter;
    private  String corelationId;


    @Autowired
    public CustomerDataFetcher(CustomerDBAdapter customerAdapter) {
        this.customerAdapter = customerAdapter;
    }

    public String getCorelationId() {
        return corelationId;
    }

    public void setCorelationId(String corelationId) {
        this.corelationId = corelationId;
    }

    @Override
    public Customer get(DataFetchingEnvironment dataFetchingEnvironment) throws CustomerRuntimeException {
        log.info("Initiate getCustomer : " + " - CorrelationId: " +corelationId);
        String data = dataFetchingEnvironment.getArgument("id");
        Customer customerById =null;
        if(data==null || data.isEmpty()){
            log.info("error in getCustomer : " + " - CorrelationId: " + corelationId );
            throw new CustomerRuntimeException(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),
                    "invalid data!  customerId cannot be null");
        }
        Long customerId =null;
        try {
            customerId = Long.parseLong(data);
            System.out.println("Customer id :"+ data);
            customerById = customerAdapter.getCustomerById(customerId,corelationId );
            System.out.println("CustomerId :"+ customerById.getCustomerId());
            customerById.setFirstName(customerById.getCustomerUserCollection().stream().collect(MoreCollectors.onlyElement()).getFirstName());
            customerById.setLastName(customerById.getCustomerUserCollection().stream().collect(MoreCollectors.onlyElement()).getLastName());
            customerById.setEmailId(customerById.getCustomerUserCollection().stream().collect(MoreCollectors.onlyElement()).getEmailId());
        }catch(NumberFormatException e){
            log.info("error in getCustomer : " + " - CorrelationId: " + corelationId );
            throw new CustomerRuntimeException(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),
                    "invalid data! '" +data+"' customerId supports only numbers formats");
        }catch(Exception e){
            log.info("---error in getCustomer Exception : " + " - CorrelationId: " + corelationId );
            e.printStackTrace();
            throw new CustomerRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "invalid data! '" +data+"' customerId supports only numbers formats");
        }

        return customerById;

    }

}
