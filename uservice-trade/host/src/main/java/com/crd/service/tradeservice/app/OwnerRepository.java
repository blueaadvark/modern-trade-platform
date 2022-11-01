package com.crd.service.tradeservice.app;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
interface OwnerRepository extends JpaRepository<OwnerDbo, UUID> {

  @Lock(LockModeType.OPTIMISTIC)
  Optional<OwnerDbo> findByEntityId(UUID entityId);
}
