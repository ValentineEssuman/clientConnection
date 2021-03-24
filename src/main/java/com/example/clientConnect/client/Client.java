package com.example.clientConnect.client;



import com.example.clientConnect.order.Order;
import com.example.clientConnect.portfolio.Portfolio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
//@Transactional
public class Client {
    @Id
/*    @SequenceGenerator(
            name="client_sequence", sequenceName = "client_sequence",
            allocationSize = 1
    )*/
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )


    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private double balance = 0;
    @Column(nullable = false)
    private LocalDate created_at = LocalDate.now();

/*
    @OneToMany
    @joinColumn(name="id")
    private Order order;
*/

    @OneToMany(mappedBy="client",cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy="client",cascade = CascadeType.ALL)
    private List<Order> orders;

    public Client(String nunana, String s, String what_is_app, double v, LocalDate of) {
    }

    public Client( String name, String email, String password, Integer balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }


    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
    }

    @Override
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
    }
}












