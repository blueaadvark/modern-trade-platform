package com.crd.service.tradeservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.crd.common.grpc.TradeResources.GetVersionRequest;
import com.crd.common.grpc.TradeResources.GetVersionResponse;
import com.crd.common.grpc.TradeServiceGrpc;
import com.crd.projectname.PostgresDbExtension;

import io.grpc.ManagedChannelBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(PostgresDbExtension.class)
class TradeApiServiceTest {

  @Value("${grpc.server.port}")
  private int grpcPort;

  @Test
  public void checkVersion() {

    var channel = ManagedChannelBuilder.forAddress("localhost", grpcPort)
        .usePlaintext()
        .build();
    var tradeService = TradeServiceGrpc.newBlockingStub(channel);
    var request = GetVersionRequest.newBuilder()
        .build();
    
    var actual = tradeService.getVersion(request);
    
    var expected = GetVersionResponse.newBuilder()
        .setVersion("CRD Trade Service 1.0")
        .build();

    Assertions.assertThat(actual).isEqualTo(expected);
  }
}
