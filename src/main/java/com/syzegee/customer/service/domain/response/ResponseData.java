package com.syzegee.customer.service.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
    private Integer code;
    private String messageDescription;
    private String messageSource;
    private Object errorDetails;
}
