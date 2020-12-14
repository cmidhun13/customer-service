/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.controller;


import com.syzegee.customer.service.entity.SolicitationPackage;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import com.syzegee.customer.service.exception.CustomerServiceException;
import com.syzegee.customer.service.service.SolicitationPackageService;
import com.syzegee.customer.service.util.CorrelationIdUtil;
import com.syzegee.customer.service.util.GenericResponse;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class   SolicitationPackageController extends SolicitationPackageAbstractController {
    //Injects the customer service
    private final SolicitationPackageService solicitationPackageService;

    /**
     * This method is used to get customer by custId using graphql
     *
     * @param query takes the query to get customer for specefic fields
     * @return object of customer based on the grapg query else return error
     */

    @PostMapping("/solicitation")
    public ResponseEntity<Object> getSolicitation(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,@RequestHeader(value = "correlationId",
            required = false, defaultValue = "") String correlationId, @RequestBody String query)
            throws IOException, CustomerRuntimeException {
        String validCorrelationId = CorrelationIdUtil.generateCorrelationId(correlationId);
        ExecutionResult execute;
        Object customerData;
        DataFetcher<List<SolicitationPackage>> dataFetcher = solicitationPackageService.retrieveSolicitationPackageById(validCorrelationId);
        execute = buildResponse(dataFetcher, validCorrelationId).execute(query);
        customerData = execute.getData();
        handleErrors(execute, validCorrelationId);

        return new ResponseEntity<>(customerData, HttpStatus.OK);
    }

    @PostMapping("/solicitationGeneric")
    public ResponseEntity<GenericResponse> solicitationGeneric(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
                                                               @RequestHeader(value = "correlationId",
            required = false, defaultValue = "") String correlationId, @RequestBody String query)
            throws IOException, CustomerRuntimeException {
        String validCorrelationId = CorrelationIdUtil.generateCorrelationId(correlationId);
        ExecutionResult execute;
        Object data;
        DataFetcher<List<SolicitationPackage>> dataFetcher = solicitationPackageService.retrieveSolicitationPackageById(validCorrelationId);
        execute = buildResponse(dataFetcher, validCorrelationId).execute(query);
        data = execute.getData();
        handleErrors(execute, validCorrelationId);
        if (((LinkedHashMap) data).get("solicitationPackage") != null) {
            return ResponseEntity.ok(new GenericResponse<Object>(true,HttpStatus.OK.value(),"Solicitation Packages fetched successfully.","Solicitation Packages fetched successfully.", data));
        }
        return ResponseEntity.ok(new GenericResponse<Object>(false,HttpStatus.OK.value(),"Solicitation Packages not found.","Solicitation Packages not found.", null));

    }

    @PostMapping("/solicitation/list")
    public ResponseEntity<GenericResponse> getSolicitationPackageList(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,@RequestBody String query) throws IOException {
        ExecutionResult execute;
        Object customerUserData;
        DataFetcher dataFetcher = solicitationPackageService.retrieveSolicitationPackagesByCustomerId();
        execute = buildResponse(dataFetcher).execute(query);
        customerUserData = execute.getData();
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty()) {
            String errorMessage = errors.get(0).getMessage();
            throw new CustomerServiceException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    errorMessage);
        } else {
            if (((List)((LinkedHashMap) customerUserData).get("getSolicitationPackage")).size()!=0) {
                return ResponseEntity.ok(new GenericResponse<Object>(true,HttpStatus.OK.value(),"Solicitation Packages fetched successfully.","Solicitation Packages fetched successfully.", customerUserData));
            }
            return ResponseEntity.ok(new GenericResponse<Object>(false,HttpStatus.OK.value(),"Solicitation Packages not found.","Solicitation Packages not found.", null));
        }
    }
}



