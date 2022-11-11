package com.crd.service.dealservice;

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
@ComponentScan(basePackages = { "com.crd.service.dealservice", "com.crd.projectname" })
public class DealServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DealServiceApplication.class, args);
  }

}
