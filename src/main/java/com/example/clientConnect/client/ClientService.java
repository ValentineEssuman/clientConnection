package com.example.clientConnect.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {


    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    //Register new client
    public Client registerClient(Client client){

        client = clientRepository.save(client);

        return client;

    }

    //Login Client
    public Client loginClient(Client client) throws IllegalAccessException {


        return clientRepository.findClientByEmailAAndPassword(client.getEmail(),client.getPassword()).orElseThrow(
                ()-> new IllegalAccessException("Invalid credentials")
        );

    }
}
