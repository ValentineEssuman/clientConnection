package com.example.clientConnect.admin;

import com.example.clientConnect.client.AdminException;
import com.example.clientConnect.client.ClientException;
import com.example.clientConnect.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //getting all clients
    @GetMapping("/all")
    public Admin[] getAdmins(){
        return adminService.findAdmins();
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin (@RequestBody Admin admin) throws AdminException {
        return adminService.loginAdmin(admin);
    }

    @GetMapping("/clientOrder")
    public ResponseEntity<String> getClientsOrders(){
        String orders = adminService.getClientOrder();
        return new ResponseEntity<String>(orders, HttpStatus.OK);
    }

    //find filled/success or failed orders
    @GetMapping("/clientOrder/{client_id}/{status}")
    public ResponseEntity<Order> getStatusOrders(@PathVariable("client_id") Long  clientId, @PathVariable("status") String  status )
            throws AdminException {
        return adminService.getStatusOrdersByClient(clientId, status);
    }


    @GetMapping("/trades/open/{exchange_Id}")
    public ResponseEntity<String> getOpenTrades(@PathVariable("exchange_Id") Long  exchange_Id) throws AdminException {
        return adminService.getOpenTradesId(exchange_Id);
    }



}
