package com.example.clientConnect.order;


import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    public OrderService(OrderRepository orderRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    };

    public List<Order> getAllOrders(Client client) {
        return orderRepository.findAllByClient(client);
    }


    //client create order is an apikey
    public Order createOrder(Order order){
        order = orderRepository.save(order);
        // Alert: Report new Order to Reporting Section
        return order;

    }

    public Order getOrder(Long id) throws OrderException {
        return orderRepository.findById(id).orElseThrow(
                ()-> new OrderException("Order "+id+" does not exist")
        );
    }

/*    //find filled/successful orders
    public List<Order> getSuccessOrder( status) {
        List<Order> statusOrders = orderRepository.findOrdersByStatus(status);
        return statusOrders;
    }*/

 /*   //findList of orders by clients
    public List<Order> clientOrdersbyId(Long clientId) throws OrderException{
        Client client = clientService.getClientById(clientId);
        List<Order> orders = orderRepository.findAllByClient(client);
        return orders;
    }*/




}
