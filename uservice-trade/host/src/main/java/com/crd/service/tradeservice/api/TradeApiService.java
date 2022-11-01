package com.crd.service.tradeservice.api;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.crd.common.grpc.TradeResources.CreateOrderRequest;
import com.crd.common.grpc.TradeResources.CreateOrderResponse;
import com.crd.common.grpc.TradeResources.GetVersionRequest;
import com.crd.common.grpc.TradeResources.GetVersionResponse;
import com.crd.common.grpc.TradeServiceGrpc;
import com.crd.service.tradeservice.app.TradeService;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * GRPC API for Trade Service Functionality.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TradeApiService extends TradeServiceGrpc.TradeServiceImplBase {

  private final TradeService tradeService;

  @Override
  @Transactional
  public void getVersion(GetVersionRequest request, StreamObserver<GetVersionResponse> responseObserver) {
    log.info("Calling Trade Service Version Check");

    tradeService.testMethods();

    var versionResponse = GetVersionResponse.newBuilder().setVersion("CRD Trade Service 1.0").build();

    responseObserver.onNext(versionResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void postNewOrder(CreateOrderRequest request, StreamObserver<CreateOrderResponse> responseObserver) {
    log.info("Received a new order");
    log.info(request.toString());

    var orderResponse = CreateOrderResponse.newBuilder().setTradeId("TestTradeId").build();

    responseObserver.onNext(orderResponse);
    responseObserver.onCompleted();
  }
}
