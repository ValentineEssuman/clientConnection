package com.example.clientConnect.client;



import com.example.clientConnect.order.Order;
import com.example.clientConnect.portfolio.Portfolio;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Transactional
public class Client {

/*    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private double balance = 0;
    @Column(nullable = false)
    private LocalDate created_at = LocalDate.now();*/


    @Column(name = "client_id")
    private long clientId;
    private String name;
    private String email;
    private String password;
    private double balance;


    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(targetEntity = Portfolio.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Portfolio> portfolios =  new ArrayList<>();

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();



/*    @OneToMany(mappedBy="client",cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy="client",cascade = CascadeType.ALL)
    private List<Order> orders;*/







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

    public Client(String name, String email, String password, double balance, List<Portfolio> portfolios) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.portfolios = portfolios;
    }



/*    public Client(String nunana, String s, String what_is_app, double v, LocalDate of) {
    }

    public Client( String name, String password, double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }


    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }*/


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

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Portfolio portfolio) {
        portfolios.add(portfolio);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", portfolios=" + portfolios +
                ", orders=" + orders +
                '}';
    }


    /*
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = ide;
//    }


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

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }*/

/*    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", created_at=" + created_at +
                ", portfolios=" + portfolios +
                ", orders=" + orders +
                '}';
    }*/
}












