package com.crd.service;

import org.assertj.core.api.Assertions;

import io.cucumber.java.en.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DealTests {

  private final TestDealService dealService;

  @When("I make a GET call on \\/deal\\/version")
  public void i_make_a_get_call_on_deal_version() {
    // nothing yet
  }

  @Then("I should receive {int} response status code")
  public void i_should_receive_response_status_code(Integer int1) {
    var result = dealService.getVersionResponseCode();
    Assertions.assertThat(result).isEqualTo(int1);
  }

  @Then("should receive a non-empty body")
  public void should_receive_a_non_empty_body() {
    var result = dealService.getVersionResponse();
    Assertions.assertThat(result).isNotEmpty();
  }
}
