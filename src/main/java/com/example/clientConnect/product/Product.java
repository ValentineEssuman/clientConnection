package com.example.clientConnect.product;

import com.example.clientConnect.portfolio.Portfolio;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import javax.persistence.*;

public class Product {

    @Column(name = "product_Id")
    private long productId;
    private String ticker;
    private int quantity;
    private double lastTradedPrice;
    private String lastTradedSide;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long portfolioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_Id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    @JsonIdentityReference(alwaysAsId = true)
    private Portfolio portfolio;

    //private LocalDateTime createdAt = LocalDateTime.now();

/*    private Long id;

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
    private Portfolio portfolio;*/

    public Product(Long productId, Integer quantity, Double lastTradedPrice, String lastTradedSide) {
    }

    public Product(long productId) {
        this.productId = productId;
    }

    public Product(String ticker, int quantity, double lastTradedPrice, String lastTradedSide) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.lastTradedPrice = lastTradedPrice;
        this.lastTradedSide = lastTradedSide;
    }

/*    public Product(String ticker, Portfolio portfolio, double quantity) {
        this.ticker = ticker;
        this.portfolio = portfolio;
        this.quantity = quantity;
    }

    public Product() {

    }*/

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }



/*
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
*/

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", lastTradedPrice=" + lastTradedPrice +
                ", lastTradedSide='" + lastTradedSide + '\'' +
                ", portfolioId=" + portfolioId +
                ", portfolio=" + portfolio +
                '}';
    }

    /*    @Override
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
    }*/
}

