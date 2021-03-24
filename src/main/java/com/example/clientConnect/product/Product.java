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
/*    @SequenceGenerator(
            name="product_sequence", sequenceName = "product_sequence",
            allocationSize = 1
    )*/
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            //generator = "product_sequence"
    )
    private Long id;

    @NotNull
    private String ticker;
    @Column(nullable = false)
    private double quantity;
    @Column()
    private double lastTradedPrice;
    @Column()
    private String lastTradedSide;
    @Column()
    private LocalDate created_at = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolioID")
    private Portfolio portfolio;

    public Product(String ticker, Portfolio portfolio, double quantity) {
        this.ticker = ticker;
        this.portfolio = portfolio;
        this.quantity = quantity;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
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

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public String getLastTradedSide() {
        return lastTradedSide;
    }

    public void setLastTradedSide(String lastTradedSide) {
        this.lastTradedSide = lastTradedSide;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", lastTradedPrice=" + lastTradedPrice +
                ", lastTradedSide='" + lastTradedSide + '\'' +
                ", created_at=" + created_at +
                ", portfolio=" + portfolio +
                '}';
    }
}

