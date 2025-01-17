package com.crd.service.businessapigateway.application.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model of Trade used by the API.
 */
@Setter
@Getter
@ToString
public class Order {
  private String id;
  private Date startDate;
  private Date endDate;
  private long notional;
  private double price;
  private String counterparty;
  private String trader;
}
