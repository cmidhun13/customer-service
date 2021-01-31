package com.syzegee.customer.service.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerStatusResponse {
    private long customerId;
    private String correlationId;
    private boolean onBoardStatus;
    private boolean siteUpdateStatus;
    private boolean marketingAutomationStatus;
    private boolean cmsSetUpStatus;

}
