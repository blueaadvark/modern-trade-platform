package com.crd.service.tradeservice.api;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.crd.common.grpc.TradeResources.CreateOrderRequest;
import com.crd.common.grpc.TradeResources.CreateOrderResponse;
import com.crd.common.grpc.TradeResources.GetVersionRequest;
import com.crd.common.grpc.TradeResources.GetVersionResponse;
import com.crd.common.grpc.TradeServiceGrpc;
import com.crd.service.tradeservice.app.OrderDbo;
import com.crd.service.tradeservice.app.OrderRepository;
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
  private final OrderRepository orderRepository;

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

    // Prepare the new order for saving to the database
    var scopedDigit = LocalDateTime.now().getSecond() % 2 + 1; // (from 1 to 2)
    var scopedAsString = "00000000-0000-0000-0000-00000000000" + scopedDigit;
    var scopedUuid = UUID.fromString(scopedAsString);

    var newOrder = new OrderDbo()
        .setEntityId(scopedUuid)
        .setEntityVersion(1L)
        .setType(request.getType())
        .setCurrency(request.getCurrency())
        .setIndex(request.getIndex())
        .setMaturity(request.getMaturity())
        .setNotional(request.getNotional())
        .setTrader(request.getTrader());
    
    var order = orderRepository.findById(scopedUuid)
        .orElseGet(() -> orderRepository.save(newOrder));

    // TODO: Send the order to the translation service to display in Manager Workbench
    
    // Get the Template for the trade from the DB
    // Populate the template with the details from the order
    // Save the populated template as a new trade to the trade DB
    // Inform the lifecycle service that we have a new trade

    // Set the response object to include the Trade Id
    var orderResponse = CreateOrderResponse.newBuilder().setTradeId(order.getEntityId().toString()).build();

    responseObserver.onNext(orderResponse);
    responseObserver.onCompleted();
  }
}
