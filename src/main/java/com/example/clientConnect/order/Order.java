package com.example.clientConnect.order;

import com.example.clientConnect.client.Client;
import com.example.clientConnect.portfolio.Portfolio;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

public class Order {

/*   private Long id;
    @Column(nullable = false)
   private String product;
    @Column(nullable = false)
   private String side;
    @Column(nullable = false, updatable = false)
   private Double price;
    @Column(nullable = false, updatable = false)
   private Integer quantity;
   private Integer validStatus;
    @Column(nullable = false, updatable = false)
   private Integer portfolioid;
   public  LocalDate created = LocalDate.now();

       //@ManyToOne
    //@Column(nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client client;*/

    @Column(name = "client_Order_Id")
    private long clientOrderId;
    private String product;
    private double price;
    private int quantity;
    private String side;
    private String validationStatus;
    private String status;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long clientId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long portfolioId;


    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    @JsonIdentityReference(alwaysAsId = true)
    private Portfolio portfolio;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    @JsonIdentityReference(alwaysAsId = true)
    private Client client;


/*    public Order(Long id, String product, String side, Double price, Integer quantity, Integer portfolioid) {
        this.id = id;
        this.product = product;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.portfolioid = portfolioid;
    }*/


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

/*    public String getProduct() {
        return product;
    }

    public Client getClient() {
        return client;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
    }

    public Integer getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(Integer portfolioid) {
        this.portfolioid = portfolioid;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }*/




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

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
                ", portfolio=" + portfolio +
                ", client=" + client +
                '}';
    }



/*    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", validStatus=" + validStatus +
                ", portfolioid=" + portfolioid +
                ", created=" + created +
                ", client=" + client +
                '}';
    }*/
}
