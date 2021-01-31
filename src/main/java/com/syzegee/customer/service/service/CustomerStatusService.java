package com.syzegee.customer.service.service;

import com.syzegee.customer.service.adapter.CustomerDBAdapter;
import com.syzegee.customer.service.adapter.CustomerStatusDBAdapter;
import com.syzegee.customer.service.adapter.CustomerUserDBAdapter;
import com.syzegee.customer.service.datafetcher.CustomerStatusDataFetcher;
import com.syzegee.customer.service.datafetcher.CustomerUserDataFetcher;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerStatusService {
    @Autowired
    CustomerStatusDBAdapter customerStatusDBAdapter;
    @Autowired
    CustomerDBAdapter customerDBAdapter;
    public DataFetcher retrieveCustomerStatus() throws CustomerRuntimeException {
        //log.info("Initiate retrieveCustomerStaus : " + " - CorrelationId: " + corelationId );
        CustomerStatusDataFetcher customerStatusDataFetcher = new CustomerStatusDataFetcher(customerStatusDBAdapter,customerDBAdapter);
        return customerStatusDataFetcher ;

    }


}
