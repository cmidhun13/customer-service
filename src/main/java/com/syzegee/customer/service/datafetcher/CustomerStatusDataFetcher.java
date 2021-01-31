package com.syzegee.customer.service.datafetcher;

import com.syzegee.customer.service.adapter.CustomerDBAdapter;
import com.syzegee.customer.service.adapter.CustomerStatusDBAdapter;
import com.syzegee.customer.service.adapter.CustomerUserDBAdapter;
import com.syzegee.customer.service.domain.response.CustomerStatusResponse;
import com.syzegee.customer.service.entity.Customer;
import com.syzegee.customer.service.entity.CustomerStatus;
import com.syzegee.customer.service.entity.CustomerUser;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerStatusDataFetcher implements DataFetcher<CustomerStatusResponse> {
    private CustomerStatusDBAdapter customerStatusDBAdapter;
    private CustomerDBAdapter customerAdapter;

    @Autowired
    public CustomerStatusDataFetcher(CustomerStatusDBAdapter customerStatusDBAdapter,CustomerDBAdapter customerAdapter) {
        this.customerStatusDBAdapter = customerStatusDBAdapter;
        this.customerAdapter= customerAdapter;
    }
    @Override
    public CustomerStatusResponse get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("customerId");
        System.out.println("-----------ReceivedData--------------:"+data);
        long customerId=0;
        try{
             customerId =  Long.parseLong(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        Customer customerById = customerAdapter.getCustomerById(customerId,"" );
        CustomerStatus status =   customerStatusDBAdapter.getCustomerStatusByCustomerId(customerById);
        System.out.println("---Got the response ---"+ status.getCustomerId());
        CustomerStatusResponse response = CustomerStatusResponse.builder().customerId(customerById.getCustomerId())
                .correlationId(status.getCorrelationId())
                .cmsSetUpStatus(status.getCmsSetUpStatus())
                .onBoardStatus(status.getOnBoardStatus())
                .marketingAutomationStatus(status.getMarketingAutomationStatus()).build();
        return response;
    }
}
