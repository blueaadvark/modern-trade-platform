package com.crd.service.dealservice.app;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Instrument Template Entity Object.
 */
@Entity
@Table(name = "TMPL_INSTRUMENT")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
public class InstrumentTemplateDbo {
  @Id
  @Column(name = "ENTITY_ID")
  @EqualsAndHashCode.Include
  private UUID entityId;

  @Version
  @Column(name = "ENTITY_VERSION")
  @EqualsAndHashCode.Include
  private Long entityVersion;

  @Column(name = "PRODUCT_TYPE")
  private String productType;

  @Column(name = "CURRENCY")
  private String currency;

  @Column(name = "INDEX")
  private String index;

  @Column(name = "TEMPLATE")
  private String template;
}
