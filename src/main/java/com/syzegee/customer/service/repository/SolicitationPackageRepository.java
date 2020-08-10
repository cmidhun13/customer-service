package com.syzegee.customer.service.repository;

import com.syzegee.customer.service.entity.SolicitationPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SolicitationPackageRepository extends JpaRepository<SolicitationPackage, Long> {
    @Query("from SolicitationPackage where isActive = true and customerId.customerId=:customerId and solicitationId=:solicitationId")
    List<SolicitationPackage> getRecordById(@Param("customerId") Long customerId, @Param("solicitationId") Long solicitationId);
    @Query("select distinct solicitationId as solicitationId, solicitationName as solicitationName from SolicitationPackage where customerId.customerId=:customerId and isActive=true and :currentDate BETWEEN startFrom AND endDate")
    List<Map<String, Object>> getRecordBySolicitationByCustomerIdAndDate(@Param("customerId") Long customerId, @Param("currentDate") Date currentDate);


}
