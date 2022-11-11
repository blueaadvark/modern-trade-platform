package com.crd.service.dealservice.app;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Instrument Template Repository Interface.
 */
@Repository
public interface InstrumentTemplateRepository extends JpaRepository<InstrumentTemplateDbo, UUID> {
  InstrumentTemplateDbo getTemplateByName(String name);
}
