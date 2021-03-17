package com.example.clientConnect.order;


import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientRepository;
import com.example.clientConnect.client.ClientService;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    public OrderService(OrderRepository orderRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    };

    //client create order is an apikey
    public Order createOrder(Order order){
        order = orderRepository.save(order);
        // Alert: Report new Order to Reporting Section
        return order;

    }

    //find filled/successful orders
    public List<Order> getSuccessOrder(String status) {
        List<Order> statusOrders = orderRepository.findOrdersByStatus(status);
        return statusOrders;
    }

    //findList of orders by clients
    public List<Order> clientOrders(Long clientId) throws ClientException {
        Client client = clientService.getClientById(clientId);
        List<Order> orders = orderRepository.findAllByClient(client);
        return orders;
    }




}
