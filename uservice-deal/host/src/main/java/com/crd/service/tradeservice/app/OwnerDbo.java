package com.crd.service.tradeservice.app;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Table(name = "OWNER")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
class OwnerDbo {

  @Id
  @Column(name = "ENTITY_ID")
  @EqualsAndHashCode.Include
  private UUID entityId;

  @Version
  @Column(name = "ENTITY_VERSION")
  @EqualsAndHashCode.Include
  private Long entityVersion;

  @Column(name = "NAME")
  private String name;
}
