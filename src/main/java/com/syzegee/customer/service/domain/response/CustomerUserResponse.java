package com.syzegee.customer.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder

@AllArgsConstructor
public class CustomerUserResponse {
    private String customerId;
    private String customerUserId;
    private String isActive;
    private String emailId;
    private String firstName;
    private String lastName;
    private String correlationId;
    @Tolerate
    public CustomerUserResponse(){}


    @Override
    public String toString() {

        return "{"+"customerId="+customerId+",isActive="+isActive+",correlationId="+correlationId+",firstName="+firstName+"}";
    }
}
