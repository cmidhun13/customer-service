package com.syzegee.customer.service.datafetcher;

import com.syzegee.customer.service.adapter.CustomerUserDBAdapter;
import com.syzegee.customer.service.entity.CustomerUser;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerUserDataFetcher implements DataFetcher<CustomerUser> {
    private CustomerUserDBAdapter customerUserDBAdapter;

    @Autowired
    public CustomerUserDataFetcher(CustomerUserDBAdapter customerUserDBAdapter) {
        this.customerUserDBAdapter = customerUserDBAdapter;
    }
    @Override
    public CustomerUser get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("emailId");
        return  customerUserDBAdapter.getCustomerUserDetailsByEmailId(data);
    }
}
