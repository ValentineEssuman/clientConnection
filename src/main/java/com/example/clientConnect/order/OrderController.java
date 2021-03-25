package com.example.clientConnect.order;

import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import trade_engine.order_validation_service.GetOrderRequest;

@Endpoint
//Uncommment if you want to work with the rest
@RestController
@RequestMapping(path="api/order")
public class OrderController {

    private static final String NAMESPACE_URI = "http://trade-engine/order-validation-service";
    private final OrderService orderService;
    private final ClientService clientService;

    public OrderController(OrderService orderService, ClientService clientService) {

        this.orderService = orderService;
        this.clientService = clientService;
    }

    //get all client order
    @GetMapping("/all")
    public Order[] getAllOrders(){
        return orderService.getAllOrders();
    }
    // login a client

    //getting Orders by clientID
    @GetMapping("/all/{clientid}")
    public ResponseEntity<Order> getAllClientOrders(@PathVariable("clientid") Long clientid) throws OrderException{
        return orderService.getAllClientOrders(clientid);
    }

    //getting Orders by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@RequestParam Long id) throws OrderException {
        return orderService.getOrder(id);
    }

/*    //Delete order by Order Id
    @DeleteMapping("/delete/order/{OrderId}")
    public void deleteClientOrder(@PathVariable("clientOrderId") Long OrderId) throws OrderException  {
        //orderService.ge;
    }*/

    // deleting client porfolio based on cliend id
    @DeleteMapping("/delete/client/{clientid}")
    public ResponseEntity<String> deleteOrderByClient(@PathVariable("clientid") Long clientid) throws OrderException {
        orderService.deleteByClientId(clientid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

/*    // deleting client porfolio based on cliend id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable("id") Long id){
        portfolioService.deletePortfolio(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    // order request submisssion
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetOrderRequest")
    public void sendOrder(@RequestPayload Order odr) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(odr);
        System.out.println(xml);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://trade-engine/order-validation-service\"><soapenv:Header/><soapenv:Body>"+xml+"</soapenv:Body></soapenv:Envelope>";
        HttpEntity<String> entity = new HttpEntity<String>(xml,headers);
        ResponseEntity<String> answer = restTemplate.postForEntity("https://order-validation-service.herokuapp.com/ws", entity, String.class);
        System.out.println(answer);
    }

    //find filled/successful orders
    @GetMapping("/all/{status}")
    public ResponseEntity<Order> getSuccessOrders(@PathVariable("status") String  status) {
        return orderService.getAllOrdersByStatus(status);
    }

    //cancel trade
    @PostMapping("/cancel/{client_id}") // action: Cancel, order, open
    public ResponseEntity<Order> cancelTrade(@PathVariable("client_id") Long client_id) throws OrderException {
        return orderService.cancelTradeByClientId(client_id);
        // return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }



}

