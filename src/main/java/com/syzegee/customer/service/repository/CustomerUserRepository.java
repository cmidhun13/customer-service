package com.syzegee.customer.service.repository;

import com.syzegee.customer.service.entity.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long> {
    @Query(" from CustomerUser where isActive=true and email_id=:emailId")
    public CustomerUser getCustomerUserDetailsByEmailId(@Param("emailId") String emailId);

}
