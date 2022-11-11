package com.crd.service.dealservice.app;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Table(name = "PROJECT")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
class ProjectDbo {

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

  @OneToOne
  @JoinColumn(name = "OWNER_ID", referencedColumnName = "ENTITY_ID")
  private OwnerDbo owner;
}
