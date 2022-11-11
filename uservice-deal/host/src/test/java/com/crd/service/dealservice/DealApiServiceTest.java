package com.crd.service.dealservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.crd.common.grpc.DealResources.GetVersionRequest;
import com.crd.common.grpc.DealResources.GetVersionResponse;
import com.crd.common.grpc.DealServiceGrpc;
import com.crd.projectname.PostgresDbExtension;

import io.grpc.ManagedChannelBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(PostgresDbExtension.class)
class DealApiServiceTest {

  @Value("${grpc.server.port}")
  private int grpcPort;

  @Test
  public void checkVersion() {

    var channel = ManagedChannelBuilder.forAddress("localhost", grpcPort)
        .usePlaintext()
        .build();
    var service = DealServiceGrpc.newBlockingStub(channel);
    var request = GetVersionRequest.newBuilder()
        .build();
    
    var actual = service.getVersion(request);
    
    var expected = GetVersionResponse.newBuilder()
        .setVersion("CRD Deal Service 1.0")
        .build();

    Assertions.assertThat(actual).isEqualTo(expected);
  }
}
