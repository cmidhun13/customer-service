package com.syzegee.customer.service.service;

import com.syzegee.customer.service.adapter.CustomerUserDBAdapter;
import com.syzegee.customer.service.datafetcher.CustomerUserDataFetcher;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerUserService {
    @Autowired
    CustomerUserDBAdapter customerUserDBAdapter;
    public DataFetcher retrieveCustomeUserDeatailsByEmailId(){
        DataFetcher fetcher = new CustomerUserDataFetcher(customerUserDBAdapter);
        return fetcher ;
    }


}
