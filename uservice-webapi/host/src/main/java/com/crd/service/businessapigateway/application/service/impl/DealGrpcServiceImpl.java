package com.crd.service.businessapigateway.application.service.impl;

import org.springframework.stereotype.Service;

import com.crd.common.grpc.DealServiceGrpc;
import com.crd.common.grpc.DealResources.CreateOrderRequest;
import com.crd.common.grpc.DealServiceGrpc.DealServiceBlockingStub;
import com.crd.common.grpc.InstrumentTemplateResources.InstrumentTemplateRequest;
import com.crd.service.businessapigateway.application.config.Closeable;
import com.crd.service.businessapigateway.application.model.Order;
import com.crd.service.businessapigateway.application.service.DealGrpcService;
import com.crd.service.businessapigateway.dto.DealResponse;
import com.crd.service.businessapigateway.dto.InstrumentTemplateRequestDto;
import com.crd.service.businessapigateway.dto.InstrumentTemplateResponse;
import com.crd.service.businessapigateway.dto.OrderDto;

import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Trade Enpoint implementation.
 */
@Service
@Slf4j
public class DealGrpcServiceImpl implements DealGrpcService {

  private final DealServiceBlockingStub dealService;

  /** Uses given channel to communicate with backend. Shutdowns the channel properly. */
  public DealGrpcServiceImpl(Closeable.Of<ManagedChannel> dealManagedChannel) {
    var channel = dealManagedChannel.item();
    this.dealService = DealServiceGrpc.newBlockingStub(channel);
  }

  @Override
  public String getVersion() {
    log.info("Calling CRD Deal MicroService");

    var response = dealService.getVersion(null);
    var version = response.getVersion();

    log.info("Received Version information from trade service, info {}", version);

    return version;
  }

  @Override
  public DealResponse createNewOrder(OrderDto order) {
    log.info("Calling CRD Deal MicroService with new order");
    log.info(order.toString());

    var orderRequest = CreateOrderRequest.newBuilder()
        .setCurrency(order.getCurrency())
        .setIndex(order.getIndex())
        .setMaturity(order.getMaturity())
        .setNotional(order.getNotional())
        .setTrader(order.getTrader())
        .setType(order.getType())
        .build();

    var response = dealService.postNewOrder(orderRequest);
    var dealResponse = new DealResponse().setTradeId(response.getTradeId());

    log.info("Received response from deal service, info {}", response.toString());
    log.info("Deal Service Response {}", dealResponse.toString());

    return dealResponse;
  }

  @Override
  public Order getDeal(String tradeId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public InstrumentTemplateResponse getTemplates(InstrumentTemplateRequestDto templateRequest) {
    log.info("Calling CRD Deal Microservice to get templates");
    log.info(templateRequest.toString());

    var instrumentTemplateRequest = InstrumentTemplateRequest.newBuilder()
        .setCurrency(templateRequest.getCurrency().isEmpty() ? "%" : templateRequest.getCurrency())
        .setIndex(templateRequest.getIndex().isEmpty() ? "%" : templateRequest.getIndex())
        .setProductType(templateRequest.getProductType().isEmpty() ? "%" : templateRequest.getProductType())
        .build();
    
    var response = dealService.getInstrumentTemplates(instrumentTemplateRequest);
    var templates = new InstrumentTemplateResponse().setTemplates(response.getTemplateList());

    log.info("Received response from deal service, info {}", response.toString());
    log.info("Deal Service Response {}", templates.toString());

    return templates;
  }
}
