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
    @Column(nullable = false,updatable = false)
    private Long client_id;
    @Column(nullable = false,updatable = false)
    private double price;
    @Column(nullable = false)
    private double quantity;
    private LocalDate created_at = LocalDate.now();

    public Portfolio(String ticker,
                     Long client_id,
                     double price,
                     double quantity) {
        this.product = product;
        this.client_id = client_id;
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

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
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
