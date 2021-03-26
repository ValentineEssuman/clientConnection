package com.example.clientConnect.admin;

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
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) throws AdminException {
        return adminService.loginAdmin(admin);
        //return new ResponseEntity<Admin> (oldAdmin, HttpStatus.ACCEPTED);
    }

    @GetMapping("/clientOrder")
    public ResponseEntity<String> getClientsOrders(){
        String orders = adminService.getClientOrder();
        return new ResponseEntity<String>(orders, HttpStatus.OK);
    }

    //Which trades are open/cancelled/failed/ successful and filled?


    //find filled/success or failed orders
    @GetMapping("/clientOrder/{client_id}/{status}")
    public ResponseEntity<Order> getStatusOrders(@PathVariable("client_id") Long  clientId, @PathVariable("status") String  status ) {
        return adminService.getStatusOrdersByClient(clientId, status);
    }


    @GetMapping("/trades/open")
    public ResponseEntity<String> getOpenTrades(){
        String openTrades = adminService.getOpenTrades();
        return new ResponseEntity<String>(openTrades, HttpStatus.OK);
    }

    //checking filled/pending client trades
    @GetMapping("/trades/pending")
    public ResponseEntity<String> getPendingTrades(){
        String pendingTrades = adminService.getPendingTrades();
        return new ResponseEntity<String>(pendingTrades, HttpStatus.OK);
    }


}
