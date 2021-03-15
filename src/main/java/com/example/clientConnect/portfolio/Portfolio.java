package com.example.clientConnect.portfolio;

import com.example.clientConnect.client.Client;
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
    private String product;

//    @Column(nullable = false,updatable = false)
    @OneToOne
    private Client client;

    @Column(nullable = false,updatable = false)
    private double price;

    @Column(nullable = false)
    private double quantity;

    private LocalDate created_at = LocalDate.now();

    public Portfolio(String product,
                     Client client,
                     double price,
                     double quantity) {
        this.product = product;
        this.client = client;
        this.price = price;
        this.quantity = quantity;
    }

    public Portfolio(String product,
                     double price,
                     double quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public Portfolio() {

    }

    public Long getId() {
        return id;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

}
