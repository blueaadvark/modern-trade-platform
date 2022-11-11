package com.crd.service;

import org.springframework.stereotype.Service;

import com.crd.service.businessapigateway.DealService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestDealService {
  private final DealService service;

  public int getVersionResponseCode() {
    return service.version().getStatusCode().value();
  }

  public String getVersionResponse() {
    return service.version().toString();
  }
}
