package com.example.clientConnect.client;


import com.example.clientConnect.order.OrderService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;


@RestController
@RequestMapping(path="api/client")
public class ClientController {

    private  final ClientService clientService;
    private final OrderService orderService;
    private final PortfolioService portfolioService;

    Jedis jedis = new Jedis("redis-17587.c92.us-east-1-3.ec2.cloud.redislabs.com", 17587);


    @Autowired
    public ClientController(ClientService clientService, OrderService orderService, PortfolioService portfolioService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.portfolioService = portfolioService;
        jedis.auth("rLAKmB4fpXsRZEv9eJBkbddhTYc1RWtK");

    }


    //getting all clients
    @GetMapping("/all")
    public Client[] getClients(){
        return clientService.getClients();
    }

    // login a client
    @PostMapping("/login")

    public ResponseEntity<Client> loginClient(@RequestBody Client client) throws ClientException, JsonProcessingException {
        ResponseEntity<Client> oldclient  = clientService.loginClient(client);
        return new ResponseEntity<> (oldclient.getBody(), HttpStatus.ACCEPTED);
    }

    //creating a new client
    @PostMapping("/register")

    public void registerClient(@RequestBody Client client) throws JsonProcessingException {

        clientService.addNewClient(client);
        jedis.publish("report-message", "Client Connectivity"+ client.getName()+":" + client.getEmail()+" has been registered");
        //Redis log creating of new clients
    }

    //deleting client based on id
    @DeleteMapping("/unregister/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId) throws ClientException {
        clientService.deleteClient(clientId);
    }

    //getting client by ID
    @GetMapping("/{clientId}")

    public ResponseEntity<Client> getClient(@PathVariable("clientId") Long clientId) throws ClientException, JsonProcessingException {
        return clientService.getClientById(clientId);
    }

    //adding portfolio based on clientid
    @PostMapping("/add/{client_id}")

    public ResponseEntity<Portfolio> addPortfolio(@PathVariable("client_id") Long client_id, @RequestBody Portfolio portfolio) throws ClientException, JsonProcessingException {


        return portfolioService.addPortfolioByClientId(client_id, portfolio);

        // return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<Client> updateClient(
            @PathVariable("clientId") Long clientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Double balance
    ) throws ClientException {
        Client updatedClient = new Client(name, password, balance);
        clientService.updateClient(updatedClient);
        return new ResponseEntity<> (updatedClient, HttpStatus.ACCEPTED);
    }


  /*  @PostMapping(path="/client")
    public void sendOrder(@RequestBody Order orderjson) throws JsonProcessingException {
        System.out.println(orderjson);
        trade_engine.order_validation_service.Order neworder = new trade_engine.order_validation_service.Order();

        neworder.setClientId(1);
        neworder.setProduct(orderjson.getProduct());
        neworder.setSide(orderjson.getSide());
        neworder.setQuantity(orderjson.getQuantity());
        neworder.setClientId(2);
        neworder.setPortfolioId(orderjson.getPortfolioId());

        GetOrderRequest orderRequest = new GetOrderRequest();
        orderRequest.setOrder(neworder);
        RestTemplate restTemplate = new RestTemplate();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(orderRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://trade-engine/order-validation-service\"><soapenv:Header/><soapenv:Body>"+xml+"</soapenv:Body></soapenv:Envelope>";
        HttpEntity<String> entity = new HttpEntity<String>(xml,headers);
        ResponseEntity<String> answer = restTemplate.postForEntity("https://order-validation-service.herokuapp.com/ws", entity, String.class);
    }*/
}
