package com.example.clientConnect.admin;

import java.time.LocalDate;
//main idea Abstract class appuser with subclasses client and Admin

//however in following your suggestion we get the code below



public class Admin {

    private Long id;

    private String name;

    private  String email;

    private String password;
    private LocalDate created_at = LocalDate.now();

    public Admin(String name, String email, String password, LocalDate created_at) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
