package com.example.clientConnect.order;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import com.example.clientConnect.portfolio.PortfolioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/order")
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;

    public OrderController(OrderService orderService, ClientService clientService) {

        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getPortfolios(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping("/all/{clientid}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("clientid") Long clientid) throws ClientException {

        Client client = clientService.getClientById(clientid);
        List<Order> clientOrders = orderService.getAllOrders(client);

        return  new ResponseEntity<>(clientOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@RequestParam Long id) throws OrderException {

        Order orders = orderService.getOrder(id);
        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }



    @PostMapping("/ordersubmission")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws OrderException {
        order = orderService.createOrder(order);
        return  new ResponseEntity<>(order, HttpStatus.OK);
    }

/*    @PostMapping("/add/{client_id}")
    public ResponseEntity<Portfolio> addPortfolio(@PathVariable("client_id") Long client_id,@RequestBody Portfolio portfolio) throws ClientException {

        Client client = clientService.getClientById(client_id);

        *//*portfolio.setClient(client);*//*

        portfolio =  portfolioService.addPortfolio(portfolio);

        return new ResponseEntity<>(portfolio,HttpStatus.ACCEPTED);
    }*/


}

