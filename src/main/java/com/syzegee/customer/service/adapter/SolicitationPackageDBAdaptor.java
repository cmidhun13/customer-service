package com.syzegee.customer.service.adapter;

import com.syzegee.customer.service.entity.SolicitationPackage;
import com.syzegee.customer.service.exception.CustomerRuntimeException;
import com.syzegee.customer.service.repository.SolicitationPackageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Riya Patel
 */
@Slf4j
@Component
public class SolicitationPackageDBAdaptor {
    private static final Logger logger = LogManager.getLogger(SolicitationPackageDBAdaptor.class);

    @Autowired
    private SolicitationPackageRepository solicitationPackageRepository;

    public List<SolicitationPackage> getSolicitationPackageById(Long customerId, Long solicitationId, String correlationID) {
        List<SolicitationPackage> record = solicitationPackageRepository.getRecordById(customerId, solicitationId);
        if (record == null) {
            log.info("error in getSolicitationPackageById : " + " - CorrelationId: " + correlationID);
            throw new CustomerRuntimeException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "customerId '" + customerId + "' is not present and solicitationId '" + solicitationId + "' is not present.");
        }
        return record;
    }

    public List<Map<String, Object>> getSolicitationPackageByCustomerIdAndDate(Long customerId){
        List<Map<String, Object>> solicitationPackageList = solicitationPackageRepository.getRecordBySolicitationByCustomerIdAndDate(customerId,new Date());
        return  solicitationPackageList;
    }
}
