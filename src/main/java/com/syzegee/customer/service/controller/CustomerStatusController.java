/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.controller;


import com.syzegee.customer.service.exception.CustomerRuntimeException;
import com.syzegee.customer.service.exception.CustomerServiceException;
import com.syzegee.customer.service.service.CustomerService;
import com.syzegee.customer.service.service.CustomerStatusService;
import com.syzegee.customer.service.service.CustomerUserService;
import com.syzegee.customer.service.util.CorrelationIdUtil;
import com.syzegee.customer.service.util.GenericResponse;
import com.szells.util.StringOperation;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author Sangam
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerStatusController extends CustomerAbstractController {
    //Injects the customer service
    @Autowired
    CustomerStatusService customerStatusService;
    @Value("classpath:customerstaus.graphqls")
    private Resource customerStatuschemaResource;

    @PostMapping("status")
    public ResponseEntity<GenericResponse<Object>> getCustomerStatus(@RequestHeader(value = "correlationId",
            required = false,defaultValue = "") String correlationId,@RequestBody String query) throws IOException {
        ExecutionResult execute;
        Object customerStatusData;
        DataFetcher dataFetcher = customerStatusService.retrieveCustomerStatus();
        //TODO Set up the proper correlation id
        System.out.println("Customer status input data is received");
        execute = buildResponse("customerStatusByCustomerId",dataFetcher,customerStatuschemaResource,"122222").execute(query);
        customerStatusData = execute.getData();
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty()) {
            String errorMessage = errors.get(0).getMessage();
            System.out.println("The error received :"+ errorMessage);
            throw new CustomerServiceException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    errorMessage);
        } else {
            if (((LinkedHashMap) customerStatusData).get("customerStatusByCustomerId") != null) {
                return ResponseEntity.ok(new GenericResponse<Object>(true,HttpStatus.OK.value(),"Customer " +
                        "exist in the system.",null, customerStatusData));
            }
            return ResponseEntity.ok(new GenericResponse<Object>(false,HttpStatus.OK.value(),"Customer " +
                    "does not exist in the system.",null, null));
        }
    }


}



