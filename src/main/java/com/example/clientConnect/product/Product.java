package com.example.clientConnect.product;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.portfolio.Portfolio;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
@Transactional
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(
            name="product_sequence", sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolioID")
    private Portfolio portfolio;

    @Column(nullable = false)
    private double quantity;

    private LocalDate created_at = LocalDate.now();

    public Product(String name, Portfolio portfolio, double quantity) {
        this.name = name;
        this.portfolio = portfolio;
        this.quantity = quantity;
    }

    public Product() {

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

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
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
