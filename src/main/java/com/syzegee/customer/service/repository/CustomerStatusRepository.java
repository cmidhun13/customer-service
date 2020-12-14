/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.repository;

import com.syzegee.customer.service.entity.Customer;
import com.syzegee.customer.service.entity.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStatusRepository extends JpaRepository<CustomerStatus, Long> {

    /**
     * Custom query for getRecordById only getting object where isActive=true
     *
     * @param customerId
     * @return Rule
     */
    @Query("from CustomerStatus where customerId=:customerId")
    public CustomerStatus getCustomerStatusByCustomerId(@Param("customerId") Long customerId);

}
