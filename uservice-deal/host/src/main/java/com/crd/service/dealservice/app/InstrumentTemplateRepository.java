package com.crd.service.dealservice.app;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Instrument Template Repository Interface.
 */
@Repository
public interface InstrumentTemplateRepository extends JpaRepository<InstrumentTemplateDbo, UUID> {
  
  @Query("select t from #{#entityName} t where t.productType = :productType and t.currency = :currency and t.index = :index")
  InstrumentTemplateDbo getJsonTemplate(@Param("productType") String productType, @Param("currency") String currency, @Param("index") String index);
}
