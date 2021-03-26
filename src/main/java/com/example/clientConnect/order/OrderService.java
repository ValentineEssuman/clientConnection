package com.example.clientConnect.order;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    //get all portfolios
    public Order[] getAllOrders(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/clientorder/all", Order[].class);
        return responseEntity.getBody();
    }

    //get all Orders for client base  by id
    public Order[] getAllClientOrders(Long clientId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order[]> clientOrders = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/clientorder/clientid/"+clientId, Order[].class);
        return clientOrders.getBody();
    }

    //get Order by Orderid
    public ResponseEntity<Order> getOrder(Long orderId) throws OrderException, JsonProcessingException {
        Order order = new Order();
        order.setClientOrderId(orderId);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(order);
        System.out.println(requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<Order>  gottenOrder =  restTemplate.postForEntity("https://tradeenginedb.herokuapp.com/api/v1/clientorder/get",entity, Order.class);
        return new ResponseEntity<Order>(gottenOrder.getBody() , HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> deleteByClientId(long clientId) throws OrderException{
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://tradeenginedb.herokuapp.com/api/v1/portfolio/delete/"+ clientId); // change
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }

    //get all Orders for by status /
    public ResponseEntity<Order[]> getAllOrdersByStatus(String status) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order[]>  statusOrders = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/clientorder/status/" + status, Order[].class);
        return new ResponseEntity<Order[]>(statusOrders.getBody() , HttpStatus.ACCEPTED);
    }

    //get all Orders for by status /
    public ResponseEntity<Order> getAllStatusOrderByClient(Long client_id,String status) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order>  statusOrders = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/clientorder/"+client_id+"/" + status, Order.class);
        return new ResponseEntity<Order>(statusOrders.getBody() , HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> cancelTradeByClientOrderId(Long clientOrder_id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String>  cancaledOrder = restTemplate.getForEntity("https://tradeenginedb.herokuapp.com/api/v1/clientorder/cancel/"+clientOrder_id,String.class);
        return new ResponseEntity<String>(cancaledOrder.getBody(), HttpStatus.ACCEPTED);
    }
}
