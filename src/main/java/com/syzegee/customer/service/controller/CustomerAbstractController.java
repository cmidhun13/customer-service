package com.syzegee.customer.service.controller;

import com.syzegee.customer.service.datafetcher.CustomerDataFetcher;
import com.syzegee.customer.service.datafetcher.CustomerUserDataFetcher;
import com.syzegee.customer.service.exception.CustomerServiceException;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * @author Sangam
 */
@Slf4j
@Controller
public abstract class CustomerAbstractController {
    @Value("classpath:customer.graphqls")
    private Resource schemaResource;
    @Value("classpath:customeruser.graphqls")
    private Resource schemaResourceCustomerUser;

    private CustomerDataFetcher dataFetcher;
    private CustomerUserDataFetcher customerUserDataFetcher;

    private GraphQL loadDataFetcher(DataFetcher dataFetcher) throws IOException {
        this.dataFetcher = (CustomerDataFetcher) dataFetcher;
        log.info("Entering loadDataFetcher@CustomerAbstractController");
        return GraphQL.newGraphQL(new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(new InputStreamReader(schemaResource.getInputStream())), buildRuntimeWiring())).build();
    }
    private GraphQL loadDataFetcherForCustomerUser(DataFetcher dataFetcher) throws IOException {
        this.customerUserDataFetcher = (CustomerUserDataFetcher) dataFetcher;
        log.info("Entering loadDataFetcher@CustomerAbstractController");
        return GraphQL.newGraphQL(new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(new InputStreamReader(schemaResourceCustomerUser.getInputStream())), buildCustomerUserRuntimeWiring())).build();

    }
    private RuntimeWiring buildCustomerUserRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("customerUserByEmailId", customerUserDataFetcher))
                .build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("customer", dataFetcher))
                .build();
    }

    public GraphQL buildResponse(DataFetcher dataFetcher, String corelationId) throws IOException {
        log.info("Entering buildResponse@CustomerAbstractController");
        log.info("Initiate buildResponse : " + " - CorrelationId: " + corelationId);
        GraphQL loadSchema = loadDataFetcher(dataFetcher);
        return loadSchema;
    }
    public GraphQL buildResponse(DataFetcher dataFetcher) throws IOException {
        GraphQL loadSchema = loadDataFetcherForCustomerUser(dataFetcher);
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
