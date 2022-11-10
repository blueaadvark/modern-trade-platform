package com.crd.service.tradeservice.app;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectRepository extends JpaRepository<ProjectDbo, UUID> {
}
