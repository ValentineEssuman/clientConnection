package com.example.clientConnect.order;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

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

    //get all Orders for by status /
    public ResponseEntity<Order> getAllOrdersByStatus(String status) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order>  statusOrders = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/clientorders/clientId/all" + status, Order.class);
        return new ResponseEntity<Order>(statusOrders.getBody() , HttpStatus.ACCEPTED);
    }


    public ResponseEntity<Order> cancelTradeByClientId(Long client_id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order>  cancaledOrder = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/client/id", client_id, Order.class);
        return new ResponseEntity<Order>(cancaledOrder.getBody(), HttpStatus.ACCEPTED);
    }


/*    //findList of orders by clients
    public List<Order> clientOrdersbyId(Client client) throws OrderException, ClientException {
        Client newclient = clientService.getClientById(client.getId());
        List<Order> orders = orderRepository.findAllByClient(newclient);
        return orders;
    }*/




}
