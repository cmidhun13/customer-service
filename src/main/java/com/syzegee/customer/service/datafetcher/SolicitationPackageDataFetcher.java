package com.syzegee.customer.service.datafetcher;

import com.syzegee.customer.service.adapter.SolicitationPackageDBAdaptor;
import com.syzegee.customer.service.entity.SolicitationPackage;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Riya Patel
 */

@Slf4j
@Component
public class SolicitationPackageDataFetcher implements DataFetcher<List<SolicitationPackage>> {
    private SolicitationPackageDBAdaptor solicitationPackageDBAdaptor;
    private String corelationId;

    public SolicitationPackageDataFetcher(SolicitationPackageDBAdaptor solicitationPackageDBAdaptor) {
        this.solicitationPackageDBAdaptor = solicitationPackageDBAdaptor;
    }

    public void setCorelationId(String corelationId) {
        this.corelationId = corelationId;
    }

    @Override
    public List<SolicitationPackage> get(DataFetchingEnvironment environment) {
        if (StringUtils.isEmpty(environment.getArgument("customer_id")) || StringUtils.isEmpty(environment.getArgument("solicitation_id")))
            throw new CustomerRuntimeException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "invalid data");
        return solicitationPackageDBAdaptor.getSolicitationPackageById(Long.valueOf(environment.getArgument("customer_id")), Long.valueOf(environment.getArgument("solicitation_id")), corelationId);
    }


}
