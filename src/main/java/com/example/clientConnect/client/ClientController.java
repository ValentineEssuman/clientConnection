package com.example.clientConnect.client;


import com.example.clientConnect.order.Order;
import com.example.clientConnect.order.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import trade_engine.order_validation_service.GetOrderRequest;


import java.io.DataInput;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path="api/client")
public class ClientController {


    private  final ClientService clientService;
    private final OrderService orderService;

    @Autowired
    public ClientController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getClients(){
        List<Client> clients = clientService.getClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable("clientId") Long clientId,@RequestBody Client client) throws ClientException{
        Client clients = clientService.getClientById(clientId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client){

        client = clientService.addClient(client);

        return  new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Client> loginClient(@RequestBody Client client) throws ClientException {

        client = clientService.loginClient(client);

        return  new ResponseEntity<>(client, HttpStatus.OK);
    }


    @PostMapping(path="/client")
    public void sendOrder(@RequestBody Order orderjson) throws JsonProcessingException {
        System.out.println(orderjson);
        trade_engine.order_validation_service.Order neworder = new trade_engine.order_validation_service.Order();

        neworder.setClientId(1);
        neworder.setProduct(orderjson.getProduct());
        neworder.setSide(orderjson.getSide());
        neworder.setQuantity(orderjson.getQuantity());
        neworder.setClientId(2);
        neworder.setPortfolioId(orderjson.getPortfolioid());

        GetOrderRequest orderRequest = new GetOrderRequest();
        orderRequest.setOrder(neworder);
        RestTemplate restTemplate = new RestTemplate();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(orderRequest);
        System.out.println(xml);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://trade-engine/order-validation-service\"><soapenv:Header/><soapenv:Body>"+xml+"</soapenv:Body></soapenv:Envelope>";
        HttpEntity<String> entity = new HttpEntity<String>(xml,headers);
        ResponseEntity<String> answer = restTemplate.postForEntity("https://order-validation-service.herokuapp.com/ws", entity, String.class);
        System.out.println(answer);
    }



/*    //submitting order via rest
    @PostMapping("/submit-order")
    public ResponseEntity<Object> submitOrder(@RequestBody JSONParser orderData) throws IOException {
        final String validationService_url = "http://trade-engine/order-validation-service";
        ObjectMapper mapper = new ObjectMapper();
        //Reader readData = new StringReader(orderData);
        //Order clientOrder = mapper.readValue(readData, Order.class);
        RestTemplate restTemplate = new RestTemplate();
        Order clientOrder = mapper.readValue((DataInput) orderData, Order.class);
        Object messObject = restTemplate.postForObject(validationService_url, clientOrder, Object.class);
        System.out.println(messObject);
        return new ResponseEntity<Object>(messObject, HttpStatus.MULTI_STATUS.OK);
    }*/




}
