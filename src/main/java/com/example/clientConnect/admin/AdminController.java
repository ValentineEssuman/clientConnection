package com.example.clientConnect.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/login")
    public ResponseEntity<Admin> loginClient(@RequestBody Admin admin) {

        admin = adminService.loginAdmin(admin);
        return  new ResponseEntity<>(admin, HttpStatus.OK);
    }


}
