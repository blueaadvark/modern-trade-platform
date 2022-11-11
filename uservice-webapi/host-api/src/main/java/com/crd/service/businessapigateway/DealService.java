package com.crd.service.businessapigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.crd.service.businessapigateway.dto.ApiResponse;
import com.crd.service.businessapigateway.dto.OrderDto;
import com.crd.service.businessapigateway.dto.DealResponse;

/**
 * API Interface for the trade service controller.
 */
public interface DealService {
  
  @RequestMapping(method = RequestMethod.GET, value = "/version")
  ResponseEntity<ApiResponse> version();

  @RequestMapping(method = RequestMethod.POST, value = "/order")
  ResponseEntity<DealResponse> createOrder(@RequestHeader(value = "trader") String trader, @RequestBody OrderDto order);
}
