package com.example.clientConnect.product;


import com.fasterxml.jackson.annotation.JsonInclude;

public class Product {
    private long productId;
    private String ticker;
    private int quantity;
    private double lastTradedPrice;
    private String lastTradedSide;


    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long portfolioId;


    public Product() {
    }

    public Product(Long productId, Integer quantity, Double lastTradedPrice, String lastTradedSide) {
    }

    public Product(int quantity, double lastTradedPrice, String lastTradedSide) {
        this.quantity = quantity;
        this.lastTradedPrice = lastTradedPrice;
        this.lastTradedSide = lastTradedSide;
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

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", lastTradedPrice=" + lastTradedPrice +
                ", lastTradedSide='" + lastTradedSide + '\'' +
                ", portfolioId=" + portfolioId +
                '}';
    }

}

