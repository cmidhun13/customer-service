package com.syzegee.customer.service.domain.response;

import lombok.experimental.Tolerate;

public class CustomerSiteResponse {
    private long customerId;
    private String correlationId;
    private Boolean onBoardStatus;
    private Boolean siteUpdateStatus;
    private Boolean marketingAutomationStatus;
    private Boolean cmsSetUpStatus;

    @Tolerate
    public CustomerSiteResponse(){}

    @Override
    public String toString() {

        return "{"+"customerId="+customerId+",onBoardStatus="+onBoardStatus+",correlationId="+correlationId+",siteUpdateStatus="+siteUpdateStatus+"}";
    }
}
