package com.crd.service.tradeservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
class TradeServiceApplicationTests {

  @Test
  void contextLoads() {
    // intentionally left empty - just used here to test if the uservice is able to start
  }

}
