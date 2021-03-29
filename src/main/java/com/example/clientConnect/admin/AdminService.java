package com.example.clientConnect.admin;
import com.example.clientConnect.order.Order;
import com.example.clientConnect.order.OrderService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    private  final OrderService orderService;

    public AdminService(OrderService orderService) {
        this.orderService = orderService;
    }

    //get all clients
    public Admin[] findAdmins() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin[]> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/all", Admin[].class);
        return responseEntity.getBody();
    }

    //Register new admin
    public void addNewAdmin(Admin admin) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/register", admin, Admin.class);

    }

    //Login Client
    public ResponseEntity<Admin> loginAdmin(@NotNull Admin admin) throws AdminException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/opentrades", admin, Admin.class);
        return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
    }





    // not sure of the use of these but these since the query will not interact with JPA
    public String getClientOrder() {
        return "all orders";
    }

    // get successful order for a client
    public ResponseEntity<Order> getStatusOrdersByClient(Long id, String status) {
        return orderService.getAllStatusOrderByClient(id,status);
    }

    // get from JPA or from
    public Admin getOpenTrades() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/trades/open", Admin.class);
        return responseEntity.getBody();
    }

    public Admin getCanceledTrades() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/trades/pending", Admin.class);
        return responseEntity.getBody();
    }


    public Admin setExchangeToTrade(Long exchange_id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/exchangetotrade/pending",exchange_id, Admin.class);
        return responseEntity.getBody();

    }

    public Admin getPendingTrades() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Admin> responseEntity = restTemplate.getForEntity("https://tradeenginetestdb.herokuapp.com/api/v1/admin/trades/open", Admin.class);
        return responseEntity.getBody();
    }
}

