package com.syzegee.customer.service.controller;

import com.syzegee.customer.service.datafetcher.SolicitationPackageDataFetcher;
import com.syzegee.customer.service.datafetcher.SolicitationPackageListDataFetcher;
import com.syzegee.customer.service.entity.SolicitationPackage;
import com.syzegee.customer.service.exception.CustomerServiceException;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * @author Sangam
 */
@Slf4j
@Controller
public abstract class SolicitationPackageAbstractController {
    @Value("classpath:solicitationpackage.graphqls")
    private Resource schemaResource;
    @Value("classpath:solicitation.graphqls")
    private Resource schemaResourceSolicitation;
    private SolicitationPackageListDataFetcher dataFetcher;
    private SolicitationPackageDataFetcher solicitationPackageDataFetcher;

    private GraphQL loadDataFetcher(DataFetcher<List<SolicitationPackage>> dataFetcher) throws IOException {
        this.solicitationPackageDataFetcher = (SolicitationPackageDataFetcher) dataFetcher;
        return GraphQL.newGraphQL(new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(new InputStreamReader(schemaResource.getInputStream())), buildRuntimeWiring())).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("solicitationPackage", solicitationPackageDataFetcher))
                .build();
    }

    public GraphQL buildResponse(DataFetcher<List<SolicitationPackage>> dataFetcher, String corelationId) throws IOException {
        log.info("Entering buildResponse@CustomerAbstractController");
        log.info("Initiate buildResponse : " + " - CorrelationId: " + corelationId);
        return loadDataFetcher(dataFetcher);
    }
    public GraphQL buildResponse(DataFetcher dataFetcher) throws IOException {
        GraphQL loadSchema = loadDataFetcherForSolicitation(dataFetcher);
        return loadSchema;
    }
    private GraphQL loadDataFetcherForSolicitation(DataFetcher dataFetcher) throws IOException {
        this.dataFetcher = (SolicitationPackageListDataFetcher) dataFetcher;
        log.info("Entering loadDataFetcher@SolicitationPAckageAbstractController");
        return GraphQL.newGraphQL(new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(new InputStreamReader(schemaResourceSolicitation.getInputStream())), buildSolicitationListRuntimeWiring())).build();

    }
    private RuntimeWiring buildSolicitationListRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("getSolicitationPackage", dataFetcher))
                .build();
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
