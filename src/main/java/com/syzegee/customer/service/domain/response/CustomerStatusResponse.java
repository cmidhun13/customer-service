package com.syzegee.customer.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

@Data
@Builder

@AllArgsConstructor
public class CustomerStatusResponse {
    private long customerId;
    private String correlationId;
    private Boolean onBoardStatus;
    private Boolean siteUpdateStatus;
    private Boolean marketingAutomationStatus;
    private Boolean cmsSetUpStatus;

    @Tolerate
    public CustomerStatusResponse(){}

    @Override
    public String toString() {

        return "{"+"customerId="+customerId+",onBoardStatus="+onBoardStatus+",correlationId="+correlationId+",siteUpdateStatus="+siteUpdateStatus+"}";
    }

}
