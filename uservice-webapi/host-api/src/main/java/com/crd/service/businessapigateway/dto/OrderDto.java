package com.crd.service.businessapigateway.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model of Order used by the API.
 */
@Setter
@Getter
@ToString
public class OrderDto {
  private String type;
  private String currency;
  private String index;
  private String maturity;
  private String notional;
  private String trader;
}
