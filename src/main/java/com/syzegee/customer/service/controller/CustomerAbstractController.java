package com.syzegee.customer.service.controller;

import com.syzegee.customer.service.datafetcher.CustomerDataFetcher;
import com.syzegee.customer.service.datafetcher.CustomerUserDataFetcher;
import com.syzegee.customer.service.exception.CustomerServiceException;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Controller
public abstract class CustomerAbstractController {

    private GraphQL loadDataFetcher(String graphQlMethodname,DataFetcher dataFetcher,Resource resource) throws IOException {
        log.info("Entering loadDataFetcher@CustomerAbstractController");
        return GraphQL.newGraphQL(new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(new InputStreamReader(resource.getInputStream())), buildRuntimeWiring(graphQlMethodname,dataFetcher))).build();

    }
    private RuntimeWiring buildRuntimeWiring(String graphQLMethodName,DataFetcher dataFetcher) {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher(graphQLMethodName,dataFetcher))
                .build();
    }


    public GraphQL buildResponse(String graphQLMethodName,DataFetcher dataFetcher,Resource resource, String corelationId) throws IOException {
        log.info("Entering buildResponse@CustomerAbstractController");
        log.info("Initiate buildResponse : " + " - CorrelationId: " + corelationId);
        GraphQL loadSchema = loadDataFetcher(graphQLMethodName,dataFetcher,resource);
        return loadSchema;
    }


    public void handleErrors(ExecutionResult execute, String corelationId) throws CustomerServiceException {
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty()) {
            log.info("Entering handleErrors@CustomerAbstractController");
            log.info("Initiate handleErrors : " + " - CorrelationId: " + corelationId);
            String errorMessage = errors.get(0).getMessage();
            throw new CustomerServiceException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    errorMessage);

        }
    }
}
