package com.example.clientConnect.order;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import trade_engine.order_validation_service.GetOrderRequest;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

//@Endpoint
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

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getPortfolios(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping("/all/{clientid}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("clientid") Long clientid) throws ClientException {

        Client client = clientService.getClientById(clientid);
        List<Order> clientOrders = orderService.getAllOrders(client);

        return  new ResponseEntity<>(clientOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@RequestParam Long id) throws OrderException {

        Order orders = orderService.getOrder(id);
        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }



    @PostMapping("/ordersubmission")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws OrderException {
        order = orderService.createOrder(order);
        return  new ResponseEntity<>(order, HttpStatus.OK);
    }

/*    @PostMapping("/add/{client_id}")
    public ResponseEntity<Portfolio> addPortfolio(@PathVariable("client_id") Long client_id,@RequestBody Portfolio portfolio) throws ClientException {

        Client client = clientService.getClientById(client_id);

        portfolio.setClient(client);

        portfolio =  portfolioService.addPortfolio(portfolio);

        return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }*/



    //Submitting Order Rest Endpoints
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    public ResponseEntity<Object> submitOrderSoap(@RequestPayload Order orderjson) throws JsonProcessingException {
        String url = "https://order-validation-service.herokuapp.com/ws";
        GetOrderRequest orderRequest = new GetOrderRequest();
        orderRequest.setOrder(orderjson);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        String clientOrderstr = mapper.writeValueAsString(orderjson);
        try {
            Order clientOrder = mapper.readValue((DataInput) orderjson, Order.class);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("mapper error in reading value");
        }
        //Report System : Redis message to Reportingtopic to notify client order made
        System.out.println(clientOrderstr);
        Object orderMessage = restTemplate.postForObject(url, orderRequest, Object.class);
        return new ResponseEntity<Object>(orderMessage, HttpStatus.MULTI_STATUS.OK);
    }


    @PostMapping(path="/client")
   public void sendOrder(@RequestBody String  orderjson) throws JsonProcessingException {
        System.out.println(orderjson);
        ObjectMapper mapper = new ObjectMapper();
        String clientOrderstr = mapper.writeValueAsString(orderjson);
        RestTemplate restTemplate = new RestTemplate();
        Order clientOrder = mapper.readValue(orderjson, Order.class);
        Object messObject = restTemplate.postForObject(NAMESPACE_URI, clientOrder, Object.class);
        System.out.println(messObject);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetOrderRequest")
    public void sendOrder(@RequestPayload GetOrderRequest odr) throws JsonProcessingException {
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



}

