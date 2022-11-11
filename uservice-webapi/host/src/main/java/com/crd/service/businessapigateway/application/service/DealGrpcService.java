package com.crd.service.businessapigateway.application.service;

import com.crd.service.businessapigateway.application.model.Order;
import com.crd.service.businessapigateway.dto.DealResponse;
import com.crd.service.businessapigateway.dto.OrderDto;

/**
 * Deal gRpc Service Interface.
 */
public interface DealGrpcService {
  String getVersion();

  DealResponse createNewOrder(OrderDto order);

  Order getDeal(String tradeId);
}
