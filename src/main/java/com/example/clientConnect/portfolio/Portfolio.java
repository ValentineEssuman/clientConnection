package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.order.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Transactional
public class Portfolio {

    @Id
    @SequenceGenerator(
            name="portfolio_sequence", sequenceName = "portfolio_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "portfolio_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

 /*   @OneToOne
    private Client client;

    // client can place many orders
    @OneToMany
    private Order order;*/

    private LocalDate created_at = LocalDate.now();

//    public Portfolio(String name,
//                     Client client) {
//        this.name = name;
//        this.client = client;
//
//    }

    public Portfolio(String name) {
        this.name = name;
    }

    public Portfolio() {

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

/*    public Client getClient() {
        return client;
    }*/

/*    public void setClient(Client client) {
        this.client = client;
    }*/

    public LocalDate getCreated_at() {
        return created_at;
    }

}
