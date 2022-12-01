package com.crd.service.dealservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
class DealServiceApplicationTests {

  @Test
  void contextLoads() {
    // intentionally left empty - just used here to test if the uservice is able to start
  }

}
