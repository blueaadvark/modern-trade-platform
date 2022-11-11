package com.crd.service.businessapigateway.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Response from order insertion.
 */
@Data
@Accessors(chain = true)
@ToString
public class DealResponse {
  private String tradeId;
}
