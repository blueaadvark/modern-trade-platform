package com.crd.service.dealservice.api;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.crd.common.grpc.DealResources.CreateOrderRequest;
import com.crd.common.grpc.DealResources.CreateOrderResponse;
import com.crd.common.grpc.DealResources.GetVersionRequest;
import com.crd.common.grpc.DealResources.GetVersionResponse;
import com.crd.common.grpc.InstrumentTemplateResources.InstrumentTemplateRequest;
import com.crd.common.grpc.InstrumentTemplateResources.InstrumentTemplateResponse;
import com.crd.service.dealservice.app.InstrumentTemplateRepository;
import com.crd.service.dealservice.app.OrderDbo;
import com.crd.service.dealservice.app.OrderRepository;
import com.crd.service.dealservice.app.DealService;
import com.crd.common.grpc.DealServiceGrpc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * GRPC API for Trade Service Functionality.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DealApiService extends DealServiceGrpc.DealServiceImplBase {

  private final DealService dealService;
  private final OrderRepository orderRepository;
  private final InstrumentTemplateRepository templateRepository;

  @Override
  @Transactional
  public void getVersion(GetVersionRequest request, StreamObserver<GetVersionResponse> responseObserver) {
    log.info("Calling Deal Service Version Check");

    dealService.testMethods();

    var versionResponse = GetVersionResponse.newBuilder().setVersion("CRD Deal Service 1.0").build();

    responseObserver.onNext(versionResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void postNewOrder(CreateOrderRequest request, StreamObserver<CreateOrderResponse> responseObserver) {
    log.info("Received a new order");
    log.info(request.toString());

    var newOrder = new OrderDbo()
        .setEntityId(UUID.randomUUID())
        .setEntityVersion(1L)
        .setType(request.getType())
        .setCurrency(request.getCurrency())
        .setIndex(request.getIndex())
        .setMaturity(request.getMaturity())
        .setNotional(request.getNotional())
        .setTrader(request.getTrader());
    
    log.info("Saving order to database for %s", newOrder.toString());
    var order = orderRepository.save(newOrder);

    // TODO: Send the order to the translation service to display in Manager Workbench
    
    // Get the Template for the trade from the DB SWAPIRS:USD:LIBOR:6M
    var templateName = String.format("%s:%s:%s:%s", order.getType(), order.getCurrency(), order.getIndex(), order.getMaturity());
    log.info("Trying to get template from database for %s", templateName);
    var template = templateRepository.getJsonTemplate(order.getType(), order.getCurrency(), order.getIndex()).getTemplate();

    // Populate the template with the details from the order
    StringWriter writer = new StringWriter();

    try {
      Template t = new Template(templateName, new StringReader(template), new Configuration(Configuration.VERSION_2_3_29));
      t.process(order, writer);
    } catch (Exception e) {
      log.error("Error processing template", e);
    }

    log.info("Filled Template from order");
    log.info(writer.toString());

    // Save the populated template as a new trade to the trade DB
    // Inform the lifecycle service that we have a new trade

    // Set the response object to include the Trade Id
    var orderResponse = CreateOrderResponse.newBuilder().setTradeId(template.toString()).build();

    responseObserver.onNext(orderResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void getInstrumentTemplates(InstrumentTemplateRequest request, StreamObserver<InstrumentTemplateResponse> responseObserver) {
    log.info("Received a template list request");
    log.info(request.toString());

    var dbResponse = templateRepository.getTemplateList(request.getProductType(), request.getCurrency(), request.getIndex()).stream().map(t -> t.getTemplate()).collect(Collectors.toList());
    var templateResponse = InstrumentTemplateResponse.newBuilder().addAllTemplate(dbResponse).build();

    responseObserver.onNext(templateResponse);
    responseObserver.onCompleted();
  }
}
