package com.example.clientConnect.order;


import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    };

    //client created and orders
    public Order creatOrder(Order order){

        order = orderRepository.save(order);

        return order;

    }

 /*   //add Order
    //find successful orders
    public Order add(@NotNull Order client) throws ClientException {

        return clientRepository.findClientByEmailAndPassword(client.getEmail(),client.getPassword()).orElseThrow(
                ()-> new ClientException("Invalid credentials")
        );

    }*/
}
