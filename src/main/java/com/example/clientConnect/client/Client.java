package com.example.clientConnect.client;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Transactional
public class Client {
    @Id
    @SequenceGenerator(
            name="client_sequence", sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )

    @Column(nullable = false,updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer balance = 0;
    private LocalDate created_at = LocalDate.now();

    public Client() {
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }
}
