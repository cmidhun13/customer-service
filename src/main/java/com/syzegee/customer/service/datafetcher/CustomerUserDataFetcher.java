package com.syzegee.customer.service.datafetcher;

import com.syzegee.customer.service.adapter.CustomerUserDBAdapter;
import com.syzegee.customer.service.domain.response.CustomerUserResponse;
import com.syzegee.customer.service.entity.CustomerUser;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerUserDataFetcher implements DataFetcher<CustomerUserResponse> {
    private CustomerUserDBAdapter customerUserDBAdapter;

    @Autowired
    public CustomerUserDataFetcher(CustomerUserDBAdapter customerUserDBAdapter) {
        this.customerUserDBAdapter = customerUserDBAdapter;
    }
    @Override
    public CustomerUserResponse get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("emailId");
        CustomerUser customerUser= customerUserDBAdapter.getCustomerUserDetailsByEmailId(data);
        System.out.println("Customer User customerUser getCustomerUserId : " + customerUser.getCustomerUserId());
        System.out.println("Customer User customerUser getCustomerId : " + customerUser.getCustomerId());
        System.out.println("Customer User getFirstName : " + customerUser.getFirstName());
        System.out.println("Customer User getFirstName : " + customerUser.getLastName());
        System.out.println("Customer User getFirstName : " + customerUser.getCorrelationId());
        System.out.println("Customer User getCreatedBy : " + customerUser.getCreatedBy());
        System.out.println("Customer User getCreatedBy : " + customerUser.getCreatedDate());
        CustomerUserResponse response = CustomerUserResponse.builder().customerId(String.valueOf(customerUser.getCustomerId()))
                .emailId(customerUser.getEmailId())
                .firstName(customerUser.getFirstName())
                .lastName(customerUser.getFirstName())
                .customerUserId(String.valueOf(customerUser.getCustomerUserId()))
                .isActive(String.valueOf(customerUser.getIsActive()))
                .build();

        return response;
    }
}
