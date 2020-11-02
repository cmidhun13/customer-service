package com.syzegee.customer.service.datafetcher;

import com.syzegee.customer.service.adapter.SolicitationPackageDBAdaptor;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SolicitationPackageListDataFetcher implements DataFetcher<Object> {
    private SolicitationPackageDBAdaptor solicitationPackageDBAdaptor;


    public SolicitationPackageListDataFetcher(SolicitationPackageDBAdaptor solicitationPackageDBAdaptor) {
        this.solicitationPackageDBAdaptor = solicitationPackageDBAdaptor;
    }

    @Override
    public List<Map<String, Object>> get(DataFetchingEnvironment environment) {
        Long data = environment.getArgument("customerId");
        return  solicitationPackageDBAdaptor.getSolicitationPackageByCustomerIdAndDate(data);
    }

}
