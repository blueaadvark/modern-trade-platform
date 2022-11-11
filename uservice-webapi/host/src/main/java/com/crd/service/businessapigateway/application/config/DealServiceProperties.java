package com.crd.service.businessapigateway.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Application properties for deal service.
 */
@ConfigurationProperties("deal-service")
@Setter
@Getter
public class DealServiceProperties {
  private String daprAppId;
  private String host;
  private int port;
}
