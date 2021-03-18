package com.example.clientConnect.admin;

import com.example.clientConnect.client.AdminException;
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


    @PostMapping("/login")
    public ResponseEntity<Admin> loginClient(@RequestBody Admin admin) throws AdminException {

        admin = adminService.loginAdmin(admin);
        return  new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/clientOrder")
    public ResponseEntity<String> getClientsOrders(){
        String orders = adminService.getClientOrder();
        return new ResponseEntity<String>(orders, HttpStatus.OK);
    }

    //Which trades are open/cancelled/failed/ successful and filled?

    @GetMapping("/clientOrder/success")
    public ResponseEntity<String> getSuccessfulOrders(){
        String successOrders = adminService.getSuccessOrders();
        return new ResponseEntity<String>(successOrders, HttpStatus.OK);
    }

    @GetMapping("/clientOrder/failed")
    public ResponseEntity<String> getFailedOrders(){
        String failedOrders = adminService.getFailedOrders();
        return new ResponseEntity<String>(failedOrders, HttpStatus.OK);
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
