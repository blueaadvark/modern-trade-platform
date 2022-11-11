package com.crd.service.dealservice.app;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OwnerRepository extends JpaRepository<OwnerDbo, UUID> {
}
