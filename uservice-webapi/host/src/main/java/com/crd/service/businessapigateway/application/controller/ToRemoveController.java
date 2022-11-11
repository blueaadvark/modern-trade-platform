package com.crd.service.businessapigateway.application.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crd.service.businessapigateway.HelloService;
import com.crd.service.businessapigateway.DealService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ToRemoveController implements HelloService {

  private final DealService dealService;

  @Override
  public AskDto ask() {    
    // ask remove service just to do some network call ...
    dealService.version();

    // ... and return some value to satisfy Gherkin tests
    return new AskDto().setValue(42);
  }
  
}
