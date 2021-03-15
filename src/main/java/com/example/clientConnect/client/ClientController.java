package com.example.clientConnect.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import java.util.ArrayList;
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


    @PostMapping("/validation")
    public Object registerNewStudent(@RequestBody Object message){
        String url = "http://localhost:8082/validation";
        RestTemplate restTemplate = new RestTemplate();
        Object messObject = restTemplate.postForObject(url, message, Object.class);
        return new ResponseEntity<Object>(messObject, HttpStatus.MULTI_STATUS.OK);
    }



}
