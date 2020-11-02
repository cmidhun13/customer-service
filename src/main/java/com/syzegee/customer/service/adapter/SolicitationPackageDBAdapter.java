package com.syzegee.customer.service.adapter;

import com.syzegee.customer.service.entity.SolicitationPackage;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import com.syzegee.customer.service.repository.SolicitationPackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SolicitationPackageDBAdapter {
    private final SolicitationPackageRepository solicitationPackageRepository;

    public List<SolicitationPackage> getSolicitationPackageById(Long customerId, Long solicitationId, String correlationID) {
        List<SolicitationPackage> record = solicitationPackageRepository.getRecordById(customerId, solicitationId);
        if (record == null) {
            log.info("error in getSolicitationPackageById : " + " - CorrelationId: " + correlationID);
            throw new CustomerRuntimeException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "customerId '" + customerId + "' is not present and solicitationId '" + solicitationId + "' is not present.");
        }
        return record;
    }
}
