package com.syzegee.customer.service.adapter;

import com.syzegee.customer.service.entity.CustomerUser;
import com.syzegee.customer.service.repository.CustomerUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riya Patel
 */
@Component
public class CustomerUserDBAdapter {
    private static final Logger logger = LogManager.getLogger(CustomerUserDBAdapter.class);

    @Autowired
    private CustomerUserRepository customerUserRepository;

    public CustomerUser getCustomerUserDetailsByEmailId(String emailId){
        CustomerUser customerUserDetailsByEmailId = customerUserRepository.getCustomerUserDetailsByEmailId(emailId);
        return  customerUserDetailsByEmailId;
    }
}
