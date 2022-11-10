package com.crd.service.tradeservice.app;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Repository Interface.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderDbo, UUID> {
}
