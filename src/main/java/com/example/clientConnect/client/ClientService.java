package com.example.clientConnect.client;


import com.example.clientConnect.order.Order;
import com.example.clientConnect.portfolio.PortfolioException;
import com.sun.istack.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {


    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }


    //get all clients
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    //Register new client
    public Client addNewClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
        if (clientOptional.isPresent()) {
            client = clientRepository.save(client);
        }
        return client;
    }

    //Login Client
    public Client loginClient(@NotNull Client client) throws ClientException {

        return clientRepository.findClientByEmailAndPassword(client.getEmail(), client.getPassword()).orElseThrow(
                () -> new ClientException("Invalid credentials")
        );
    }

    public Client getClient(Client client) throws ClientException {

        return clientRepository.findById(client.getId()).orElseThrow(
                () -> new ClientException("Client id " + client.getId() + " does not exist")
        );
    }

    public Client getClientById(Long id) throws ClientException {

        return clientRepository.findById(id).orElseThrow(
                () -> new ClientException("Client with " + id + "does not exist")
        );
    }

    //deleting a client
    public void deleteClient(Long clientid) {
        boolean exists = clientRepository.existsById(clientid);
        if (!exists) {
            throw new IllegalStateException("Student with id: " + clientid + " does not exist.");
        }
        clientRepository.deleteById(clientid);
    }

    @Transactional
    public void updateClient(Long clientId, String name, String password, Double balance) {
        Client client = clientRepository.findClientById(clientId).orElseThrow(() -> new IllegalStateException("Student with id " + clientId + " does not exist."));

        if (name != null && name.length() > 0 && !Objects.equals(client.getName(), name)) {
            client.setName(name);
        }

        if (password != null && password.length() > 0 && !Objects.equals(client.getPassword(), password)) {
            Optional<Client> clientOptional = clientRepository.findClientById(clientId);
            client.setPassword(password);
        }

        if (balance != null && !Objects.equals(client.getBalance(), balance)) {
            client.setBalance(balance);
        }

    }
}
