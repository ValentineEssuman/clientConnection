package com.example.clientConnect.client;


import com.example.clientConnect.order.Order;
import com.example.clientConnect.order.OrderException;
import com.sun.istack.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {


    //get all clients
    public Client[] getClients() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client[]> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/all", Client[].class);

        return responseEntity.getBody();
    }

    //Register new client
    public void addNewClient(Client client) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> responseEntity = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/register", client, Client.class);

    }

    //Login Client
    public ResponseEntity<Client> loginClient(@NotNull Client client) throws ClientException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> responseEntity = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/login", client, Client.class);
        return new ResponseEntity<Client>(client, HttpStatus.ACCEPTED);
    }

    //deleting a client
    public ResponseEntity<String> deleteClient(Long clientid) {
        //boolean exists = clientRepository.existsById(clientid);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginetestdb.herokuapp.com/api/v1/client/uregister/" + clientid);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }


    public ResponseEntity<Client> getClientById(Long clientid) throws ClientException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> gottenClient = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/id", clientid, Client.class);
        return new ResponseEntity<Client>(gottenClient.getBody(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updateClient(Client updatedclient) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("https://tradeenginetestdb.herokuapp.com/api/v1/client/update", updatedclient, Client.class);
        return new ResponseEntity<String>("Updated", HttpStatus.ACCEPTED);
    }

}

/*    @GetMapping("/{id}")
    public Client getClient(Client client) throws ClientException {

    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@RequestParam Long id) throws OrderException {

        Order orders = orderService.getOrder(id);
        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }*/

