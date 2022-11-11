package com.crd.service.businessapigateway.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crd.service.businessapigateway.DealService;
import com.crd.service.businessapigateway.dto.ApiResponse;
import com.crd.service.businessapigateway.dto.OrderDto;
import com.crd.service.businessapigateway.dto.DealResponse;
import com.crd.service.businessapigateway.application.service.DealGrpcService;

import lombok.RequiredArgsConstructor;

/**
 * Controller for Trade operations.
 */
@RestController
@RequestMapping(value = "/deal")
@RequiredArgsConstructor
class DealServiceController implements DealService {

  private final DealGrpcService dealGrpcService;

  /**
   * Version endpoint.
   */
  @Override
  public ResponseEntity<ApiResponse> version() {
    var version = dealGrpcService.getVersion();
    var response = new ApiResponse().setResponse(version);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Create order endpoint.
   */
  @Override
  public ResponseEntity<DealResponse> createOrder(String trader, OrderDto order) {
    order.setTrader(trader);
    var orderResponse = dealGrpcService.createNewOrder(order);
    return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
  }
}
