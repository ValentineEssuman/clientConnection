package com.example.clientConnect.order;


import com.example.clientConnect.client.ClientService;
import com.example.clientConnect.portfolio.Portfolio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    public OrderService(OrderRepository orderRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
    }
    //get all portfolios
    public Order[] getAllOrders(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/clientorder/all", Order[].class);
        return responseEntity.getBody();
    }

    //get all Orders for client base  by id
    public ResponseEntity<Order> getAllClientOrders(Long clientId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order> clientOrders = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/clientorders/clientId/all", Order.class);
        return new ResponseEntity<Order>(clientOrders.getBody() , HttpStatus.ACCEPTED);
    }

    //get Order by Orderid
    public ResponseEntity<Order> getOrder(Long orderId) throws OrderException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order>  gottenOrder =  restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/clientorders/clientId/all", Order.class);
        return new ResponseEntity<Order>(gottenOrder.getBody() , HttpStatus.ACCEPTED);
    }

    // adding a portfolio
    public ResponseEntity<Portfolio> addPortfolio(Portfolio portfolioname){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Portfolio>  sentPortfolio = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/id", portfolioname, Portfolio.class);
        return new ResponseEntity<Portfolio>(sentPortfolio.getBody(), HttpStatus.ACCEPTED);

    }
/*    //client create order is an apikey
    public Order createOrder(Order order){
        order = orderRepository.save(order);

        // Alert: Report new Order to Reporting Section
        return order;

    }*/

/*    public ResponseEntity<String> deleteByOrderId(Long orderId){
        //boolean exists = clientRepository.existsById(portfolioId);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginetestdb.herokuapp.com/api/v1/portfolio/delete/"+ orderId);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);

    }*/

    public ResponseEntity<String> deleteByClientId(long clientId) throws OrderException{
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginetestdb.herokuapp.com/api/v1/portfolio/delete/"+ clientId); // change
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }

/*    //find filled/successful orders
    @GetMapping("/all/{status}")
    public List<Order> getSuccessOrder(@PathVariable("status") Order order) {
        List<Order> statusOrders = orderRepository.findOrdersById(order.getValidStatus() == );
        return statusOrders;
    }*/


/*    //findList of orders by clients
    public List<Order> clientOrdersbyId(Client client) throws OrderException, ClientException {
        Client newclient = clientService.getClientById(client.getId());
        List<Order> orders = orderRepository.findAllByClient(newclient);
        return orders;
    }*/




}
