package com.crd.service.businessapigateway;

import org.springframework.cloud.openfeign.FeignClient;

import com.crd.service.businessapigateway.configuration.DealServiceConfiguration;

/** {@inheritDoc} */
@FeignClient(name = "deal-service", url = "http://localhost:18010/deal", configuration = DealServiceConfiguration.class)
public interface DealServiceFeign extends DealService {
  
}
