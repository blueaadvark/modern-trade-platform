package com.crd.service.dealservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.crd.projectname.PostgresDbExtension;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(PostgresDbExtension.class)
class DealServiceApplicationTests {

  //@Test
  //void contextLoads() {
    // intentionally left empty - just used here to test if the uservice is able to start
  //}

}
