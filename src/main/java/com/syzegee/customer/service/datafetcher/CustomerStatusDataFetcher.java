package com.syzegee.customer.service.datafetcher;

import com.syzegee.customer.service.adapter.CustomerStatusDBAdapter;
import com.syzegee.customer.service.adapter.CustomerUserDBAdapter;
import com.syzegee.customer.service.entity.CustomerStatus;
import com.syzegee.customer.service.entity.CustomerUser;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerStatusDataFetcher implements DataFetcher<CustomerStatus> {
    private CustomerStatusDBAdapter customerStatusDBAdapter;


    @Autowired
    public CustomerStatusDataFetcher(CustomerStatusDBAdapter customerStatusDBAdapter) {
        this.customerStatusDBAdapter = customerStatusDBAdapter;
    }
    @Override
    public CustomerStatus get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("customerId");
        System.out.println("-----------ReceivedData--------------:"+data);
        long customerId=0;
        try{
             customerId =  Long.parseLong(data);
        }catch(Exception e){
            e.printStackTrace();
        }

        return  customerStatusDBAdapter.getCustomerStatusByCustomerId(customerId);
    }
}
