/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.controller;


import com.syzegee.customer.service.exception.CustomerRuntimeException;
import com.syzegee.customer.service.exception.CustomerServiceException;
import com.syzegee.customer.service.service.CustomerService;
import com.syzegee.customer.service.service.CustomerUserService;
import com.syzegee.customer.service.util.CorrelationIdUtil;
import com.syzegee.customer.service.util.GenericResponse;
import com.szells.util.StringOperation;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/")
public class CustomerController extends CustomerAbstractController {
    //Injects the customer service
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerUserService customerUserService;

    /**
     * This method is used to get customer by custId using graphql
     *
     * @param query takes the query to get customer for specefic fields
     * @return object of customer based on the grapg query else return error
     */

    @PostMapping(value="/customers")
    public ResponseEntity<Object> getCustomer(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,@RequestHeader(value = "correlationId",
            required = false,defaultValue = "") String correlationId,@RequestBody String query)
            throws IOException, CustomerRuntimeException {
        log.info("Entered into getCustomer@CustomerController");
        String validCorrelationId = CorrelationIdUtil.generateCorrelationId(correlationId);
        log.info("Initiate getCustomer : " + " - CorrelationId: " + validCorrelationId);
        ExecutionResult execute;

        Object customerData;
        DataFetcher dataFetcher = customerService.retrieveCustomerById(validCorrelationId);
        execute = buildResponse(dataFetcher, validCorrelationId).execute(query);
        customerData = execute.getData();
        handleErrors(execute, validCorrelationId);
        return new ResponseEntity<>(customerData, HttpStatus.OK);
    }
    @PostMapping(value="/customersWithoutAuth")    // this method is used in ng auth project (forgotPassword api)
    public ResponseEntity<Object> getCustomerWithoutAuth(@RequestHeader(value = "correlationId",
            required = false,defaultValue = "") String correlationId,@RequestBody String query)
            throws IOException, CustomerRuntimeException {
        log.info("Entered into getCustomer@CustomerController");
        String validCorrelationId = CorrelationIdUtil.generateCorrelationId(correlationId);
        log.info("Initiate getCustomer : " + " - CorrelationId: " + validCorrelationId);
        ExecutionResult execute;

        Object customerData;
        DataFetcher dataFetcher = customerService.retrieveCustomerById(validCorrelationId);
        execute = buildResponse(dataFetcher, validCorrelationId).execute(query);
        customerData = execute.getData();
        handleErrors(execute, validCorrelationId);
        return new ResponseEntity<>(customerData, HttpStatus.OK);
    }
    @PostMapping("/customerUserDetails")
    public ResponseEntity<GenericResponse<Object>> getCustomerDetails(@RequestBody String query) throws IOException {
        ExecutionResult execute;
        Object customerUserData;
        DataFetcher dataFetcher = customerUserService.retrieveCustomeUserDeatailsByEmailId();
        execute = buildResponse(dataFetcher).execute(query);
        customerUserData = execute.getData();
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty()) {
            String errorMessage = errors.get(0).getMessage();
            throw new CustomerServiceException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    errorMessage);
        } else {
            if (((LinkedHashMap) customerUserData).get("customerUserByEmailId") != null) {
                return ResponseEntity.ok(new GenericResponse<Object>(true,HttpStatus.OK.value(),"Customer " +
                        "exist in the system.",null, customerUserData));
            }
            return ResponseEntity.ok(new GenericResponse<Object>(false,HttpStatus.OK.value(),"Customer " +
                    "does not exist in the system.",null, null));
        }
    }
    @GetMapping(value = "/encryption")
    public GenericResponse<String> encryption(@RequestParam("memberid") String msg) throws Exception {
        String encrypt = StringOperation.encrypt(msg);
        return new GenericResponse<>(true, HttpStatus.OK.value(), "Encrypted successfully.", null, encrypt);
    }
    @GetMapping(value = "/decryption")
    public GenericResponse<String> decryption(@RequestParam("memberid") String msg) throws Exception {
        String decrypt = StringOperation.decrypt(msg);
        return new GenericResponse<>(true, HttpStatus.OK.value(), "Decrypted successfully.", null, decrypt);
    }

}



