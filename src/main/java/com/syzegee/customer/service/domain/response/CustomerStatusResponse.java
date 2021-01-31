package com.syzegee.customer.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatusResponse {
    private long customerId;
    private String correlationId;
    private boolean onBoardStatus;
    private boolean siteUpdateStatus;
    private boolean marketingAutomationStatus;
    private boolean cmsSetUpStatus;


}
