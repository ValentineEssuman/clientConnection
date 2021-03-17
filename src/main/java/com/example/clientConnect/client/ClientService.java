package com.example.clientConnect.client;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    private final ClientRepository clientRepository;


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
    public Client loginClient(@NotNull Client client) throws ClientException {

        return clientRepository.findClientByEmailAndPassword(client.getEmail(),client.getPassword()).orElseThrow(
                ()-> new ClientException("Invalid credentials")
        );

    }

    public Client getClientById(@NotNull Long id) throws ClientException {

        return clientRepository.findById(id).orElseThrow(
                () -> new ClientException("Client with "+id+"does not exist")
        );
    }


}
