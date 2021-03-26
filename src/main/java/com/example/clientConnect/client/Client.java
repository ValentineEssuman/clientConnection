package com.example.clientConnect.client;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Client {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long clientId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    private String email;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double balance;

    public Client() {
    }

    public Client(String name, String password, Double balance) {
    }
    public Client(long clientId) {
        this.clientId = clientId;
    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Client(String name, String email, String password, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Client(long clientId, String name, String email, String password, double balance) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}












