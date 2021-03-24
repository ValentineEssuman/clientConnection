package com.example.clientConnect.client;


import com.example.clientConnect.order.OrderService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path="api/client")
public class ClientController {


    private  final ClientService clientService;
    private final OrderService orderService;
    private final PortfolioService portfolioService;

    @Autowired
    public ClientController(ClientService clientService, OrderService orderService, PortfolioService portfolioService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.portfolioService = portfolioService;
    }

    //getting all clients
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getClients(){
        List<Client> clients = clientService.getClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // login a client
    @PostMapping("/login")
    public ResponseEntity<Client> loginClient(@RequestBody Client client) throws ClientException {
        client = clientService.loginClient(client);
        return  new ResponseEntity<>(client, HttpStatus.OK);
    }

    //creating a new client
    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client){

        client = clientService.addNewClient(client);

        return  new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/unregister/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId) throws ClientException {
        clientService.deleteClient(clientId);
    }

    //getting client by ID
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable("clientId") Long clientId,@RequestBody Client client) throws ClientException{
        Client clients = clientService.getClientById(clientId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/add/{client_id}")
    public ResponseEntity<Portfolio> addPortfolio(@PathVariable("client_id") Long client_id, @RequestBody Portfolio portfolio) throws ClientException {

        Client client = clientService.getClientById(client_id);

        //portfolio.setClient(client);

        portfolio =  portfolioService.addPortfolio(portfolio);

        return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{clientId}")
    public void updateClient(
            @PathVariable("clientId") Long clientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Double balance
    ) throws ClientException {
        clientService.updateClient(clientId, name, password, balance);
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
