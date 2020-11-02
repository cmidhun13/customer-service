package com.syzegee.customer.service.service;

import com.syzegee.customer.service.adapter.SolicitationPackageDBAdaptor;
import com.syzegee.customer.service.datafetcher.SolicitationPackageDataFetcher;
import com.syzegee.customer.service.datafetcher.SolicitationPackageListDataFetcher;
import com.syzegee.customer.service.entity.SolicitationPackage;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitationPackageService {
    @Autowired
    SolicitationPackageDBAdaptor solicitationPackageDBAdaptor;
    public DataFetcher retrieveSolicitationPackagesByCustomerId(){
        DataFetcher fetcher = new SolicitationPackageListDataFetcher(solicitationPackageDBAdaptor);
        return fetcher ;
    }

    public DataFetcher<List<SolicitationPackage>> retrieveSolicitationPackageById(String corelationId) {
        SolicitationPackageDataFetcher fetcher = new SolicitationPackageDataFetcher(solicitationPackageDBAdaptor);
        fetcher.setCorelationId(corelationId);
        return fetcher;
    }


}
