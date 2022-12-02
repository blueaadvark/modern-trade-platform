package com.crd.service.dealservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.crd.projectname.PostgresDbExtension;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(PostgresDbExtension.class)
@Testcontainers
class DealServiceApplicationTests {

  @Test
  @Disabled
  void contextLoads() {
    // intentionally left empty - just used here to test if the uservice is able to start
  }

}
