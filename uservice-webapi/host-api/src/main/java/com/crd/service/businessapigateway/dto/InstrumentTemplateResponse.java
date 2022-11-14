package com.crd.service.businessapigateway.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Response from template querying.
 */
@Data
@Accessors(chain = true)
@ToString
public class InstrumentTemplateResponse {
  private List<String> templates;
}
