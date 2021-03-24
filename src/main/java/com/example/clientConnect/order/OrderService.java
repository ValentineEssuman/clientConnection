package com.example.clientConnect.order;


import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Order> getAllClientOrders(Client client) {
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

    public void deleteOrder(long id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Order with id: " + id + " does not exist.");
        }
        orderRepository.deleteById(id);
    }

/*    //find filled/successful orders
    @GetMapping("/all/{status}")
    public List<Order> getSuccessOrder(@PathVariable("status") Order order) {
        List<Order> statusOrders = orderRepository.findOrdersById(order.getValidStatus() == );
        return statusOrders;
    }*/


    //findList of orders by clients
    public List<Order> clientOrdersbyId(Client client) throws OrderException, ClientException {
        Client newclient = clientService.getClientById(client.getId());
        List<Order> orders = orderRepository.findAllByClient(newclient);
        return orders;
    }




}
