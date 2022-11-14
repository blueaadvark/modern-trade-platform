package com.crd.service.businessapigateway.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model of Instrument Template Request used by the API.
 */
@Setter
@Getter
@ToString
public class InstrumentTemplateRequestDto {
  private String productType;
  private String currency;
  private String index;
}
