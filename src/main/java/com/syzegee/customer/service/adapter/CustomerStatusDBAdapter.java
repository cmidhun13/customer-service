package com.syzegee.customer.service.adapter;

import com.syzegee.customer.service.entity.CustomerStatus;
import com.syzegee.customer.service.entity.CustomerUser;
import com.syzegee.customer.service.repository.CustomerStatusRepository;
import com.syzegee.customer.service.repository.CustomerUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerStatusDBAdapter {
    private static final Logger logger = LogManager.getLogger(CustomerStatusDBAdapter.class);

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    public CustomerStatus getCustomerStatusByCustomerId(long customrId){
        CustomerStatus customerStatus = customerStatusRepository.getCustomerStatusByCustomerId(customrId);
        return  customerStatus;
    }
}
