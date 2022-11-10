package com.crd.service.tradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry method for the service.
 */
@ConfigurationPropertiesScan
@SpringBootApplication
// TODO: rename root package to com.crd.projectname and remove scan definition below
@ComponentScan(basePackages = { "com.crd.service.tradeservice", "com.crd.projectname" })
public class TradeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TradeServiceApplication.class, args);
  }

}
