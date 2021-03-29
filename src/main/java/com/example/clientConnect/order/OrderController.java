package com.example.clientConnect.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import trade_engine.order_validation_service.GetOrderRequest;
import trade_engine.order_validation_service.GetOrderResponse;

import javax.xml.soap.MessageFactory;

//Uncommment if you want to work with the rest
@RestController
@RequestMapping(path="api/order")
public class OrderController {

    private static final String NAMESPACE_URI = "http://trade-engine/order-validation-service";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    //get all client order
    @GetMapping("/all")
    public Order[] getAllOrders(){
        return orderService.getAllOrders();
    }
    // login a client

    //getting Orders by clientID
    @GetMapping("/client/{clientid}")
    public Order[] getAllClientOrders(@PathVariable("clientid") Long clientid) throws OrderException{
        return orderService.getAllClientOrders(clientid);
    }

    //getting Orders by ID

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) throws OrderException, JsonProcessingException {
        return orderService.getOrder(id);
    }


    // deleting client porfolio based on cliend id
    @DeleteMapping("/delete/client/{clientid}")
    public ResponseEntity<String> deleteOrderByClient(@PathVariable("clientid") Long clientid) throws OrderException {
        orderService.deleteByClientId(clientid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // order request submisssion
    @PostMapping("/sendOrder")
    public void sendOrder(@RequestBody Order odr) throws Exception {
        System.out.println(odr);
        trade_engine.order_validation_service.Order order = new trade_engine.order_validation_service.Order();
        order.setPortfolioId(odr.getPortfolioId());
        order.setClientId(odr.getClientId());
        order.setSide(odr.getSide());
        order.setQuantity(odr.getQuantity());
        order.setProduct(odr.getProduct());
        order.setPrice(odr.getPrice());
        GetOrderRequest getOrderRequest = new GetOrderRequest();
        getOrderRequest.setOrder(order);
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
        messageFactory.afterPropertiesSet();

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("trade_engine.order_validation_service");
        marshaller.afterPropertiesSet();

        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.afterPropertiesSet();
        System.out.println(odr);
        GetOrderResponse response =(GetOrderResponse) webServiceTemplate.marshalSendAndReceive("https://order-validation-service.herokuapp.com/ws", getOrderRequest);
        System.out.println(response.getValidationStatus());
    }

    //find filled/successful orders
    @GetMapping("/all/{status}")
    public ResponseEntity<Order[]> getSuccessOrders(@PathVariable("status") String  status) {
        return orderService.getAllOrdersByStatus(status);
    }

    //cancel trade
    @GetMapping("/cancel/{clientorder_id}") // action: Cancel, order, open
    public ResponseEntity<String> cancelTrade(@PathVariable("clientorder_id") Long clientOrder_id) throws OrderException {
        return orderService.cancelTradeByClientOrderId(clientOrder_id);
    }
}

