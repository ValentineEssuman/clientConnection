package com.example.clientConnect.client;


import com.example.clientConnect.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.DataInput;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path="api/client")
public class ClientController {

    private  final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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

    @PostMapping("/submit-order")
    public ResponseEntity<Object> submitOrder(@RequestBody JSONParser orderData) throws IOException {
        final String validationService_url = "http://trade-engine/order-validation-service";
        ObjectMapper mapper = new ObjectMapper();
        //Reader readData = new StringReader(orderData);
        //Order clientOrder = mapper.readValue(readData, Order.class);
        RestTemplate restTemplate = new RestTemplate();
        Order clientOrder = mapper.readValue((DataInput) orderData, Order.class);
        Object messObject = restTemplate.postForObject(validationService_url, clientOrder, Object.class);
        return new ResponseEntity<Object>(messObject, HttpStatus.MULTI_STATUS.OK);
    }



}
