package com.example.clientConnect.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {


    //get all clients
    public Client[] getClients() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client[]> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/all", Client[].class);
        return responseEntity.getBody();
    }

    //Register new client
    public ResponseEntity<String>addNewClient(Client client) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(client);
        //System.out.println(requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/register", entity, String.class);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
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


