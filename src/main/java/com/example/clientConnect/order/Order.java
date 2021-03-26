package com.example.clientConnect.order;


import com.fasterxml.jackson.annotation.JsonInclude;

public class Order {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long clientOrderId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String product;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double price;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String side;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String validationStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long clientId;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long portfolioId;


    public Order() {
    }

    public Order(long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public Order(String product, double price, int quantity, String side, String validationStatus, String status) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.validationStatus = validationStatus;
        this.status = status;
    }

    public Order(String product, double price, int quantity, String side, String validationStatus, String status, long clientId, long portfolioId) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.validationStatus = validationStatus;
        this.status = status;
        this.clientId = clientId;
        this.portfolioId = portfolioId;
    }

    public Order(long clientOrderId, String product, double price, int quantity, String side, String validationStatus, String status, long clientId, long portfolioId) {
        this.clientOrderId = clientOrderId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.validationStatus = validationStatus;
        this.status = status;
        this.clientId = clientId;
        this.portfolioId = portfolioId;
    }

    public long getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "clientOrderId=" + clientOrderId +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", side='" + side + '\'' +
                ", validationStatus='" + validationStatus + '\'' +
                ", status='" + status + '\'' +
                ", clientId=" + clientId +
                ", portfolioId=" + portfolioId +
                '}';
    }
}
