package com.example.clientConnect.client;



import com.example.clientConnect.portfolio.Portfolio;
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
        ResponseEntity<Client[]> responseEntity = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/client/all", Client[].class);
        return responseEntity.getBody();
    }

    //Register new client
    public ResponseEntity<Client>addNewClient(Client client) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(client);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<Client> responseEntity = restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/client/register", entity, Client.class);
        return new ResponseEntity<Client>(client, HttpStatus.ACCEPTED);
    }

    //Login Client
    public ResponseEntity<Client> loginClient(@NotNull Client client) throws ClientException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(client);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<Client> responseEntity = restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/client/login", entity, Client.class);
        System.out.println(responseEntity.getBody());
        return new ResponseEntity<Client>(responseEntity.getBody(), HttpStatus.ACCEPTED);
    }

    //deleting a client
    public ResponseEntity<String> deleteClient(Long clientid) {
        //boolean exists = clientRepository.existsById(clientid);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginedb.herokuapp.com/api/v1/client/unregister/" + clientid);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }



    public ResponseEntity<Client> getClientById(Long clientid) throws ClientException, JsonProcessingException {
        Client client = new Client(clientid);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(client);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<Client> gottenClient = restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/client/id", entity, Client.class);

        return new ResponseEntity<Client>(gottenClient.getBody(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updateClient(Client updatedclient) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("https://tradeenginedb.herokuapp.com/api/v1/client/update", updatedclient, Client.class);
        return new ResponseEntity<String>("Updated", HttpStatus.ACCEPTED);
    }

}


